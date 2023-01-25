package tim.projekat.kontroleri;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tim.projekat.dto.LoginDTO;
import tim.projekat.dto.RegisterDTO;
import tim.projekat.model.Klijent;
import tim.projekat.model.Korisnik;
import tim.projekat.servisi.KorisnikServis;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthKontroler {

    @Autowired
    KorisnikServis korisnikServis;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        System.out.println(loginDTO.toString());
        Korisnik korisnik = this.korisnikServis.getKorisnikByEmail(loginDTO.getEmail());
        if(korisnik.getLozinka().equals(loginDTO.getPassword()) && korisnik.getAktivan().equals(true))
            return ResponseEntity.ok(korisnik);
        return (ResponseEntity<?>) ResponseEntity.internalServerError();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO) {
        System.out.println(registerDTO.toString());
        Korisnik k=new Klijent(registerDTO);
        this.korisnikServis.save(k);
        return ResponseEntity.ok(registerDTO);
    }
}
