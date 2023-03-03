package tim.projekat.kontroleri;

import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin(origins = "http://localhost:4200")
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
            return (ResponseEntity<?>) ResponseEntity.internalServerError();
        // TODO: dto u voznju
        Voznja v = new Voznja(vDTO);

        // preuzmi sve vozace i proveri parametre
        List<Korisnik> vozacList = this.korisnikServis.findAllByType("Vozac");
        List<Vozac> vozaci = new ArrayList<>();
        for (Korisnik korisnik : vozacList) {
            vozaci.add((Vozac) korisnik);
        }
        List<Vozac> vozaciCopy = new ArrayList<>(vozaci);

        // da li ima prijavljenih vozaca? => izbaci neaktivne
        // da li ima slobodnih vozaca?
        // ako ne, da li ima vozaca koji zavrsavaju trenutnu voznju i nemaju sledecu
        // ako ima, proveri koji je pri zavrsetku voznje => izbaci one u voznji sa voznjom koja sledi
        for (Vozac vozac : vozaciCopy) {
            if (vozac.getStatus().equals(false)) vozaci.remove(vozaciCopy.indexOf(vozac));
            if (vozac.getuVoznji().equals(true)) {
                List<Voznja> sledece = this.voznjaServis.getDriverUpcoming(vozac);
                if (sledece.size() > 0) {
                    for (Voznja voznja : sledece) {
                        if (voznja.getDatumVreme().getHour() == LocalDateTime.now().getHour()) {
                            vozaci.remove(vozaciCopy.indexOf(vozac));
                            break;
                        }
                    }
                }
            }
        }
        // ako nema dostupnih vozaca, odbij voznju
        // vrati obavestenje da nema slobodnih vozaca
        if (vozaci.isEmpty()) return ResponseEntity.ok(vDTO);

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
            this.korisnikServis.save(k);
            this.korisnikServis.save(min.getKey());
            this.voznjaServis.save(v);

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
            String[] vKoord = v.getPolaziste().split(",");
            Address address = geocodingService.geocode(Double.parseDouble(vKoord[0]), Double.parseDouble(vKoord[1]));
            String pol = address.toString();
            System.out.println(address.getDisplayName());
            vKoord = v.getDestinacija().split(",");
            address = geocodingService.geocode(Double.parseDouble(vKoord[0]), Double.parseDouble(vKoord[1]));
            String odr = address.toString();
            voznjeFront.add(new DrivingEntryDTO(v, klijent, pol, odr));
        }
        System.out.println(voznjeFront);
        return ResponseEntity.ok(voznjeFront);
    }

    @PostMapping("/getHistory")
    public ResponseEntity<?> listHistoryDrives(@RequestBody KorisnikEmailDTO keDTO) {
        Korisnik k;
        List<Voznja> voznje = new ArrayList<Voznja>();
        List<DrivingEntryDTO> voznjeFront = new ArrayList<>();
        if (keDTO.getRole().equals(Klijent.class.getSimpleName())) {
            k = korisnikServis.getKorisnikByEmail(keDTO.getEmail());
            System.out.println(k);
            voznje = voznjaServis.getClientHistory((Klijent) k);

            for (Voznja v : voznje) {
                Vozac vozac = this.korisnikServis.getVozac(v);
                String[] vKoord = v.getPolaziste().split(",");
                Address address = geocodingService.geocode(Double.parseDouble(vKoord[0]), Double.parseDouble(vKoord[1]));
                String pol = address.toString();
                vKoord = v.getDestinacija().split(",");
                address = geocodingService.geocode(Double.parseDouble(vKoord[0]), Double.parseDouble(vKoord[1]));
                String odr = address.toString();
                voznjeFront.add(new DrivingEntryDTO(v, vozac, pol, odr));
            }
        } else if (keDTO.getRole().equals(Vozac.class.getSimpleName())) {
            k = korisnikServis.getKorisnikByEmail(keDTO.getEmail());
            System.out.println(k);
            voznje = voznjaServis.getDriverHistory((Vozac) k);

            for (Voznja v : voznje) {
                Klijent klijent = this.korisnikServis.getKlijent(v);
                String[] vKoord = v.getPolaziste().split(",");
                Address address = geocodingService.geocode(Double.parseDouble(vKoord[0]), Double.parseDouble(vKoord[1]));
                String pol = address.toString();
                vKoord = v.getDestinacija().split(",");
                address = geocodingService.geocode(Double.parseDouble(vKoord[0]), Double.parseDouble(vKoord[1]));
                String odr = address.toString();
                voznjeFront.add(new DrivingEntryDTO(v, klijent, pol, odr));
            }
        }
        System.out.println(voznjeFront);
        return ResponseEntity.ok(voznjeFront);
    }
}
