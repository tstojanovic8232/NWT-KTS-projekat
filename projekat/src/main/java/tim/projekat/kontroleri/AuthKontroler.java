package tim.projekat.kontroleri;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tim.projekat.model.*;
import tim.projekat.repozitorijumi.VoziloRepo;
import tim.projekat.requestDTO.LoginDTO;
import tim.projekat.requestDTO.RegisterDTO;
import tim.projekat.requestDTO.RegisterVozacDTO;
import tim.projekat.responseDTO.LoginResponseDTO;
import tim.projekat.servisi.EmailServis;
import tim.projekat.servisi.FacebookServis;
import tim.projekat.servisi.KorisnikServis;

import java.util.Collections;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "https://localhost:4200", originPatterns = "*://localhost:4200")
public class AuthKontroler {
    @Autowired
    EmailServis emailServis;
    @Autowired
    KorisnikServis korisnikServis;

    @Autowired
    VoziloRepo voziloRepo;
    private static final String CLIENT_ID = "622369180841-fbatp9ei09717bm0hu30qkb6u5mhvn73.apps.googleusercontent.com";

    private static final String APP_ID="269068999131322";

   @Autowired
    FacebookServis facebookServis;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginDTO loginDTO) {
        System.out.println(loginDTO.toString());
        Korisnik korisnik = this.korisnikServis.getKorisnikByEmail(loginDTO.getEmail());
        if(korisnik == null) {
            return ResponseEntity.internalServerError().build();
        }
        LoginResponseDTO response = new LoginResponseDTO(korisnik.getEmail(), korisnik.getClass().getSimpleName());
        if (korisnik.getLozinka().equals(loginDTO.getPassword()) && korisnik.getAktivan().equals(true))
        {
            if(korisnik.getClass().equals(Vozac.class)) {
                Vozac v = (Vozac) korisnik;
                this.korisnikServis.switchStatus(v, true);
            }
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.internalServerError().build();
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO) {
        System.out.println(registerDTO.toString());
        Korisnik k = new Klijent(registerDTO);
        this.korisnikServis.save(k);
        String token = UUID.randomUUID().toString();

        // Save the token, email, and timestamp in the database
        korisnikServis.createVerificationToken(k, token);

        // Send the email with the link

        emailServis.sendConfirmationEmail(k.getEmail(), token);
        return ResponseEntity.ok(registerDTO);
    }

    @PostMapping(value = "/registerDriver", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerVozac(@RequestBody RegisterVozacDTO registerDTO) {
        System.out.println(registerDTO.toString());
        Vozilo v=new Vozilo(registerDTO.getRegistration(),registerDTO.getPrice());
        this.voziloRepo.save(v);
        Korisnik k = new Vozac(registerDTO,v);
        this.korisnikServis.save(k);


        // Save the token, email, and timestamp in the database

        return ResponseEntity.ok(registerDTO);
    }


    @GetMapping(value = "/confirm", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> confirmEmail(@RequestParam("token") String token) {

        // Retrieve the token from the database
        VerificationToken verificationToken = korisnikServis.getVerificationToken(token);
        System.out.println(verificationToken.getUser());
        // If the token is valid, update the user's account
        if (verificationToken != null && !verificationToken.isExpired()) {
            korisnikServis.enableUser(verificationToken.getUser());

            return ResponseEntity.ok(verificationToken);
        } else {
            return (ResponseEntity<?>) ResponseEntity.internalServerError();
        }
    }

    @PostMapping(value = "/login/google", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponseDTO> loginUserWithGoogle(@RequestBody String credential) {
        try {
            // Validate with clientID via Google OAuth library
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .build();

            GoogleIdToken idToken = verifier.verify(credential);

            if (idToken != null) {
                // Collect User data/email with credential
                String email = idToken.getPayload().getEmail();

                // Find Korisnik with email
                Korisnik korisnik = korisnikServis.getKorisnikByEmail(email);

                if (korisnik != null) {
                    // If Korisnik != null, then return LoginResponseDTO with email and role
                    LoginResponseDTO responseDTO = new LoginResponseDTO(korisnik.getEmail(), korisnik.getClass().getSimpleName());
                    return ResponseEntity.ok(responseDTO);
                } else {
                    korisnik = new Klijent();
                    korisnik.setEmail(email);
                    korisnikServis.save(korisnik);
                    String token = UUID.randomUUID().toString();

                    // Save the token, email, and timestamp in the database
                    korisnikServis.createVerificationToken(korisnik, token);

                    // Send the email with the link

                    emailServis.sendConfirmationEmail(korisnik.getEmail(), token);
                    LoginResponseDTO responseDTO = new LoginResponseDTO(korisnik.getEmail(), "None");

                    return ResponseEntity.ok(responseDTO);
                }
            } else {
                System.out.println(idToken);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.internalServerError().build();
    }

    @PostMapping(value = "/login/facebook", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginUserWithFacebook(@RequestBody String credential) {
        // Validate with client ID via RestTemplate and URL
        // Replace "YOUR_CLIENT_ID" with the actual client ID provided by Facebook
        boolean isValidClientId = facebookServis.validateClientId(credential, APP_ID);

        if (!isValidClientId) {
            // Return an appropriate error response
            return ResponseEntity.badRequest().build();
        }

        // Collect User data/email via RestTemplate and URL
        String email = facebookServis.getUserEmail(credential);

        // Find Korisnik with email
        Korisnik korisnik = korisnikServis.getKorisnikByEmail(email);

        if (korisnik == null) {
            // If Korisnik == null, then return an appropriate error response
            return ResponseEntity.notFound().build();
        } else {
            // If Korisnik != null, then return LoginResponseDTO with email and role
            LoginResponseDTO responseDTO = new LoginResponseDTO(email, korisnik.getClass().getSimpleName());
            return ResponseEntity.ok(responseDTO);
        }
    }
}
