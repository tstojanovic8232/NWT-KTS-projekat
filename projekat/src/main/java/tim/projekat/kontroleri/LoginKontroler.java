package tim.projekat.kontroleri;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tim.projekat.dto.LoginDTO;
import tim.projekat.model.Korisnik;
import tim.projekat.servisi.KorisnikServis;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginKontroler {

    @Autowired
    KorisnikServis korisnikServis;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        Korisnik korisnik = this.korisnikServis.getUserByEmail(loginDTO.getEmail());
        if(korisnik.getLozinka().equals(loginDTO.getPassword()))
            return ResponseEntity.ok(korisnik);
        return (ResponseEntity<?>) ResponseEntity.internalServerError();
    }
}
