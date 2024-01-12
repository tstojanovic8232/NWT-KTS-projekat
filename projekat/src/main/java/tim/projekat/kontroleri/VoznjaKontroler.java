package tim.projekat.kontroleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tim.projekat.model.*;
import tim.projekat.requestDTO.KorisnikEmailDTO;
import tim.projekat.requestDTO.VoznjaDTO;
import tim.projekat.responseDTO.DrivingEntryDTO;
import tim.projekat.servisi.GeocodingService;
import tim.projekat.servisi.KorisnikServis;
import tim.projekat.servisi.VoznjaServis;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "https://localhost:4200", originPatterns = "*://localhost:4200")
@RequestMapping("/drives")
public class VoznjaKontroler {

    //    public final static List<String> lokacije = new ArrayList<String>();
    @Autowired
    KorisnikServis korisnikServis;
    @Autowired
    VoznjaServis voznjaServis;

    @Autowired
    private GeocodingService geocodingService;


//    @GetMapping
//    public void init() {
//        lokacije.add("45.253893, 19.847793");
//        lokacije.add("45.235225, 19.810289");
//        lokacije.add("45.240383, 19.837863");
//        lokacije.add("45.259111, 19.822304");
//        lokacije.add("45.268964, 19.811065");
//        lokacije.add("45.276600, 19.828007");
//        lokacije.add("45.265099, 19.830670");
//        lokacije.add("45.298069, 19.822174");
//        lokacije.add("45.219459, 19.853156");
//        lokacije.add("45.252390, 19.870099");
//    }

    @PostMapping("/add")
    public ResponseEntity<?> newDrive(@RequestBody VoznjaDTO vDTO) {
        Klijent k = (Klijent) this.korisnikServis.getKorisnikByEmail(vDTO.getClient());
        if (k.equals(null))
            return ResponseEntity.internalServerError().build();
        // TODO: dto u voznju
        Voznja v = new Voznja(vDTO);

        // preuzmi sve vozace i proveri parametre
        List<Korisnik> vozacList = this.korisnikServis.findAllByType("Vozac");
        List<Vozac> vozaci = new ArrayList<>();
        for (Korisnik korisnik : vozacList) {
            vozaci.add((Vozac) korisnik);
        }

        // da li ima prijavljenih vozaca? => izbaci neaktivne
        // da li ima slobodnih vozaca?
        // ako ne, da li ima vozaca koji zavrsavaju trenutnu voznju i nemaju sledecu
        // ako ima, proveri koji je pri zavrsetku voznje => izbaci one u voznji sa voznjom koja sledi
        vozaci.removeIf(vozac -> !vozac.getStatus());
        vozaci.removeIf(vozac -> vozac.getBlokiran());
        vozaci.removeIf(vozac -> vozac.getuVoznji() &&
                this.voznjaServis.getDriverUpcoming(vozac)
                        .stream()
                        .anyMatch(voznja -> voznja.getDatumVreme().getHour() == LocalDateTime.now().getHour()));
        // ako nema dostupnih vozaca, odbij voznju
        // vrati obavestenje da nema slobodnih vozaca
        if (vozaci.isEmpty()) return ResponseEntity.ok().build();

            // ako ima dostupnih vozaca u listi, proveri rastojanje od polazista
            // dekartovo rastojanje
        else {
            Map<Vozac, Double> rastojanja = new HashMap<>();
            String[] voznjaKoord = v.getPolaziste().split(",");
            for (Vozac vozac : vozaci) {
                String[] vozacKoord = vozac.getTrenutnaLokacija().split(",");
                double d = Math.sqrt(Math.pow(Double.parseDouble(vozacKoord[0]) - Double.parseDouble(voznjaKoord[0]), 2) + Math.pow(Double.parseDouble(vozacKoord[1]) - Double.parseDouble(voznjaKoord[1]), 2));
                rastojanja.put(vozac, d);
            }
            Map.Entry<Vozac, Double> min = null;
            for (Map.Entry<Vozac, Double> entry : rastojanja.entrySet()) {
                if (min == null || min.getValue() > entry.getValue()) {
                    min = entry;
                }
            }
            min.getKey().addVoznja(v);
            k.addVoznja(v);
            this.voznjaServis.save(v);
            this.korisnikServis.save(k);
            this.korisnikServis.save(min.getKey());


        }

        return ResponseEntity.ok(vDTO);
        // TODO:
        //  ako je placanje uspesno, posalji obavestenje
        //  ako nije uspesno, obrisi voznju
    }

    @PostMapping("/getUpcoming")
    public ResponseEntity<?> listUpcomingDrives(@RequestBody KorisnikEmailDTO keDTO) {
        Korisnik k = korisnikServis.getKorisnikByEmail(keDTO.getEmail());
        List<Voznja> voznje = voznjaServis.getDriverUpcoming((Vozac) k);
        List<DrivingEntryDTO> voznjeFront = new ArrayList<>();
        for (Voznja v : voznje) {
            Klijent klijent = this.korisnikServis.getKlijent(v);
            String[] vKoord = v.getPolaziste().split(", ");
            double lat = Double.parseDouble(vKoord[0]);
            double lon = Double.parseDouble(vKoord[1]);
            APIResponse apiResponse = geocodingService.geocode(lat, lon);
            String pol = apiResponse.getAddress().toString();
            vKoord = v.getDestinacija().split(", ");
            lat = Double.parseDouble(vKoord[0]);
            lon = Double.parseDouble(vKoord[1]);
            apiResponse = geocodingService.geocode(lat, lon);
            String odr = apiResponse.getAddress().toString();
            voznjeFront.add(new DrivingEntryDTO(v, klijent, pol, odr));
        }
        System.out.println(voznjeFront);
        return ResponseEntity.ok(voznjeFront);
    }

    @PostMapping("/getHistory")
    public ResponseEntity<?> listHistoryDrives(@RequestBody KorisnikEmailDTO keDTO) {
        System.out.println(keDTO);
        Korisnik k= korisnikServis.getKorisnikByEmail(keDTO.getEmail());
        if (k==null) {
            return ResponseEntity.internalServerError().build();
        }
        System.out.println(k);
        List<Voznja> voznje;
        List<DrivingEntryDTO> voznjeFront = new ArrayList<>();
        if (k.getClass().equals(Klijent.class)) {
            voznje = voznjaServis.getClientHistory((Klijent) k);

            for (Voznja v : voznje) {
                Vozac vozac = this.korisnikServis.getVozac(v);
                String[] vKoord = v.getPolaziste().split(", ");
                double lat = Double.parseDouble(vKoord[0]);
                double lon = Double.parseDouble(vKoord[1]);
                APIResponse apiResponse = geocodingService.geocode(lat, lon);
                String pol = apiResponse.getAddress().toString();
                vKoord = v.getDestinacija().split(", ");
                lat = Double.parseDouble(vKoord[0]);
                lon = Double.parseDouble(vKoord[1]);
                apiResponse = geocodingService.geocode(lat, lon);
                String odr = apiResponse.getAddress().toString();
                System.out.println(vozac);
                voznjeFront.add(new DrivingEntryDTO(v, vozac, pol, odr));
            }
        } else if (k.getClass().equals(Vozac.class)) {
            voznje = voznjaServis.getDriverHistory((Vozac) k);

            for (Voznja v : voznje) {
                Klijent klijent = this.korisnikServis.getKlijent(v);
                String[] vKoord = v.getPolaziste().split(", ");
                double lat = Double.parseDouble(vKoord[0]);
                double lon = Double.parseDouble(vKoord[1]);
                APIResponse apiResponse = geocodingService.geocode(lat, lon);
                String pol = apiResponse.getAddress().toString();
                vKoord = v.getDestinacija().split(", ");
                lat = Double.parseDouble(vKoord[0]);
                lon = Double.parseDouble(vKoord[1]);
                apiResponse = geocodingService.geocode(lat, lon);
                String odr = apiResponse.getAddress().toString();
                voznjeFront.add(new DrivingEntryDTO(v, klijent, pol, odr));
            }
        }
        System.out.println(voznjeFront);
        return ResponseEntity.ok(voznjeFront);
    }

    @PostMapping("/start")
    public ResponseEntity<?> startDrive(@RequestBody KorisnikEmailDTO keDTO) {
        Vozac v = (Vozac) this.korisnikServis.getKorisnikByEmail(keDTO.getEmail());

        if (v == null) {
            return ResponseEntity.internalServerError().build();
        }

        List<Voznja> upcomingDrives = this.voznjaServis.getDriverUpcoming(v);

        if (upcomingDrives.isEmpty()) {
            // Handle the case when there are no upcoming drives for the driver
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No upcoming drives for the driver.");
        }

        Voznja voznja = upcomingDrives.get(0);
        Klijent k = this.korisnikServis.getKlijent(voznja);

        v.setuVoznji(true);
        k.setuVoznji(true);

        this.korisnikServis.save(k);
        this.korisnikServis.save(v);

        return ResponseEntity.ok(voznja);
    }


    @PostMapping("/stop")
    public ResponseEntity<?> EndDrive(@RequestBody KorisnikEmailDTO keDTO) {

        Vozac v=(Vozac)this.korisnikServis.getKorisnikByEmail(keDTO.getEmail());
        if(v==null){
            return ResponseEntity.internalServerError().build();
        }
        List<Voznja>li=this.voznjaServis.getDriverUpcoming(v);
        if (li.isEmpty()) {
            // Handle the case when there are no upcoming drives for the driver
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No upcoming drives for the driver.");
        }
        Voznja voznja=li.get(0);
        Klijent k=this.korisnikServis.getKlijent(voznja);
        v.setuVoznji(false);
        k.setuVoznji(false);
        voznja.setGotova(true);
        this.korisnikServis.save(k);
        this.korisnikServis.save(v);
        this.voznjaServis.save(voznja);
        return ResponseEntity.ok(voznja);
    }




}
