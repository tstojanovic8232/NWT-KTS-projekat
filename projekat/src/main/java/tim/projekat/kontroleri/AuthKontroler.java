package tim.projekat.kontroleri;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tim.projekat.dto.LoginDTO;
import tim.projekat.dto.RegisterDTO;
import tim.projekat.model.Klijent;
import tim.projekat.model.Korisnik;
import tim.projekat.model.VerificationToken;
import tim.projekat.servisi.EmailServis;
import tim.projekat.servisi.KorisnikServis;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthKontroler {
    @Autowired
    EmailServis emailServis;
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
        String token = UUID.randomUUID().toString();

        // Save the token, email, and timestamp in the database
        korisnikServis.createVerificationToken(k, token);

        // Send the email with the link

        emailServis.sendConfirmationEmail(k.getEmail(), token);
        return ResponseEntity.ok(registerDTO);
    }
    @GetMapping("/confirm")
    public ResponseEntity<?> confirmEmail(@RequestParam("token") String token) {

        // Retrieve the token from the database
        VerificationToken verificationToken = korisnikServis.getVerificationToken(token);
        System.out.println(verificationToken.getUser());
        // If the token is valid, update the user's account
        if (verificationToken != null && !verificationToken.isExpired()) {
            korisnikServis.enableUser(verificationToken.getUser());

            return ResponseEntity.ok(verificationToken);
        } else {
            return (ResponseEntity<?>)ResponseEntity.internalServerError();
        }
    }
}
