package tim.projekat.kontroleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tim.projekat.model.Klijent;
import tim.projekat.model.Korisnik;
import tim.projekat.model.Vozac;
import tim.projekat.model.enums.NacinPlacanja;
import tim.projekat.requestDTO.*;
import tim.projekat.responseDTO.KVProfilDTO;
import tim.projekat.servisi.KorisnikServis;

import java.util.Map;


@RestController
@CrossOrigin(origins = "https://localhost:4200", originPatterns = "*://localhost:4200")
@RequestMapping("/users")
public class KorisnikKontroler {

    @Autowired
    KorisnikServis korisnikServis;

    @PostMapping("/data")
    public ResponseEntity<?> getUser(@RequestBody KorisnikEmailDTO keDTO) {
        Korisnik k;
        KVProfilDTO p = new KVProfilDTO();
        if (keDTO.getRole().equals(Klijent.class.getSimpleName())) {
            k = korisnikServis.getKorisnikByEmail(keDTO.getEmail());
            System.out.println(k);
            p = new KVProfilDTO((Klijent) k);
        } else if (keDTO.getRole().equals(Vozac.class.getSimpleName())) {
            k = korisnikServis.getKorisnikByEmail(keDTO.getEmail());
            System.out.println(k);
            p = new KVProfilDTO((Vozac) k);
        }
        System.out.println(p);
        return ResponseEntity.ok(p);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UpdateDTO uDto) {
        System.out.println("azuriramo pod");
        Korisnik k = this.korisnikServis.getKorisnikByEmail(uDto.getEmail());
        if (k.equals(null)) {

            return (ResponseEntity<?>) ResponseEntity.internalServerError();
        }

        if (uDto.getName() != null) {
            k.setIme(uDto.getName());
        }
        if (uDto.getLastname() != null) {
            k.setPrezime(uDto.getLastname());
        }
        if (uDto.getCity() != null) {
            k.setGrad(uDto.getCity());
        }
        if (uDto.getPhone() != null) {
            k.setBrojTel(uDto.getPhone());
        }
        this.korisnikServis.save(k);
        return ResponseEntity.ok(uDto);
    }

    @PostMapping("/updatePass")
    public ResponseEntity<?> updatePass(@RequestBody LoginDTO uDto) {
        Korisnik k = this.korisnikServis.getKorisnikByEmail(uDto.getEmail());
        if (k.equals(null)) {
            return (ResponseEntity<?>) ResponseEntity.internalServerError();
        }
        k.setLozinka(uDto.getPassword());
        this.korisnikServis.save(k);
        return ResponseEntity.ok(k);
    }

    @PostMapping("/updateEmail")
    public ResponseEntity<?> updateEmail(@RequestBody LoginDTO email) {
        Korisnik k = this.korisnikServis.getKorisnikByEmail(email.getEmail());
        System.out.println(k.toString());
        if (k == null) {
            return (ResponseEntity<?>) ResponseEntity.internalServerError();
        }
        k.setEmail(email.getPassword());
        this.korisnikServis.save(k);
        return ResponseEntity.ok(k);
    }

//    @PostMapping("/block")
//    public ResponseEntity<?> blockUser(@RequestBody String email) {
//        System.out.println(email);
//        Korisnik k = this.korisnikServis.getKorisnikByEmail(email);
//        System.out.println(k);
//        if (k == null) {
//            return ResponseEntity.internalServerError().build();
//        }
//
//        this.korisnikServis.SetBlokiran(k);
//        return ResponseEntity.ok(k);
//    }
    @PostMapping("/block")
    public ResponseEntity<?> blockUser(@RequestBody Map<String, Object> requestData) {
        String email = (String) requestData.get("email");
        boolean blocked = (boolean) requestData.get("blocked");
        System.out.println(email);
        Korisnik k = this.korisnikServis.getKorisnikByEmail(email);
        System.out.println(k);
        if (k == null) {
            return ResponseEntity.internalServerError().build();
        }

        if (blocked) {
            this.korisnikServis.SetBlokiran(k);
        } else {
            this.korisnikServis.setNeblokiran(k);
        }

        return ResponseEntity.ok(k);
    }

    @PostMapping("/updateBilling")
    public ResponseEntity<?> updateBilling(@RequestBody BillingDTO uDto) {
        Korisnik k = this.korisnikServis.getKorisnikByEmail(uDto.getEmail());
        if (k.equals(null)) {
            return (ResponseEntity<?>) ResponseEntity.internalServerError();
        }
        if (uDto.getRole().equals(Klijent.class.getSimpleName())) {

            ((Klijent) k).setNacinPlacanja(NacinPlacanja.valueOf(uDto.getBillingType()));
            if (uDto.getBillingData() != null) {
                ((Klijent) k).setPodaciPlacanja(uDto.getBillingData());
            }

        } else if (uDto.getRole().equals(Vozac.class.getSimpleName())) {
            ((Vozac) k).setNacinPlacanja(NacinPlacanja.valueOf(uDto.getBillingType()));
            if (uDto.getBillingData() != null) {
                ((Vozac) k).setPodaciPlacanja(uDto.getBillingData());
            }
        }

        this.korisnikServis.save(k);
        return ResponseEntity.ok(uDto);
    }

    @PostMapping("/switch")
    public void updateSwitchState(@RequestBody SwitchStateDTO ssDTO) {
        Vozac v = (Vozac) this.korisnikServis.getKorisnikByEmail(ssDTO.getEmail());
        boolean state = ssDTO.isState();
        this.korisnikServis.switchStatus(v, state);
        System.out.println(this.korisnikServis.getKorisnikByEmail(ssDTO.getEmail()));
    }

    @PostMapping("/status")
    public ResponseEntity<Boolean> getStatus(@RequestBody KorisnikEmailDTO keDTO) {
        Vozac v = (Vozac) this.korisnikServis.getKorisnikByEmail(keDTO.getEmail());
        if (v == null) return (ResponseEntity<Boolean>) ResponseEntity.internalServerError();
        return ResponseEntity.ok(v.getStatus());
    }

    @PostMapping("/blockstatus")
    public ResponseEntity<Boolean> getblockStatus(@RequestBody KorisnikEmailDTO keDTO) {
        Korisnik k = this.korisnikServis.getKorisnikByEmail(keDTO.getEmail());
        if (k == null) return (ResponseEntity<Boolean>) ResponseEntity.internalServerError();
        if (keDTO.getRole().equals(Vozac.class.getSimpleName())) {

            return ResponseEntity.ok(((Vozac) k).getBlokiran());
        } else if (keDTO.getRole().equals(Klijent.class.getSimpleName())) {

            return ResponseEntity.ok(((Klijent) k).getBlokiran());

        }else{
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/clients")
    public ResponseEntity<?> getClients() {
        try {
            return ResponseEntity.ok(this.korisnikServis.getClients());
        } catch (Exception e) {
            e.getStackTrace();
            return (ResponseEntity<?>) ResponseEntity.internalServerError();
        }
    }

    @GetMapping("/drivers")
    public ResponseEntity<?> getDrivers() {
        try {
            return ResponseEntity.ok(this.korisnikServis.getDrivers());
        } catch (Exception e) {
            e.getStackTrace();
            return (ResponseEntity<?>) ResponseEntity.internalServerError();
        }
    }

    @PostMapping("/setPass")
    public ResponseEntity<?> setPass(@RequestBody LoginDTO uDto) {
        try {
            Korisnik k = this.korisnikServis.getKorisnikByEmail(uDto.getEmail());
            if (k.equals(null)) {
                return (ResponseEntity<?>) ResponseEntity.internalServerError();
            }
            k.setLozinka(uDto.getPassword());
            this.korisnikServis.save(k);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.getStackTrace();
            return (ResponseEntity<?>) ResponseEntity.internalServerError();
        }
    }
}
