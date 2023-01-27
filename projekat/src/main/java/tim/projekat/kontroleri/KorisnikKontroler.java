package tim.projekat.kontroleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tim.projekat.dto.KorisnikEmailDTO;
import tim.projekat.model.Klijent;
import tim.projekat.model.Korisnik;
import tim.projekat.model.Vozac;
import tim.projekat.pojo.KVProfilPOJO;
import tim.projekat.servisi.KorisnikServis;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
public class KorisnikKontroler {

    @Autowired
    KorisnikServis korisnikServis;

    @PostMapping("/data")
    public ResponseEntity<?> getUser(@RequestBody KorisnikEmailDTO keDTO) {
        Korisnik k;
        KVProfilPOJO p = new KVProfilPOJO();
        if(keDTO.getRole().equals(Klijent.class.getSimpleName())) {
            k = korisnikServis.getKorisnikByEmail(keDTO.getEmail());
            p = new KVProfilPOJO((Klijent) k);
        }
        else if(keDTO.getRole().equals(Vozac.class.getSimpleName())) {
            k = korisnikServis.getKorisnikByEmail(keDTO.getEmail());
            p = new KVProfilPOJO((Vozac) k);
        }
        return ResponseEntity.ok(p);
    }

}
