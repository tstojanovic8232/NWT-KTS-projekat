package tim.projekat.kontroleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tim.projekat.requestDTO.BillingDTO;
import tim.projekat.requestDTO.KorisnikEmailDTO;
import tim.projekat.requestDTO.LoginDTO;
import tim.projekat.requestDTO.UpdateDTO;
import tim.projekat.model.Klijent;
import tim.projekat.model.Korisnik;
import tim.projekat.model.Vozac;
import tim.projekat.model.enums.NacinPlacanja;
import tim.projekat.responseDTO.KVProfilDTO;
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
        KVProfilDTO p = new KVProfilDTO();
        if(keDTO.getRole().equals(Klijent.class.getSimpleName())) {
            k = korisnikServis.getKorisnikByEmail(keDTO.getEmail());
            System.out.println(k);
            p = new KVProfilDTO((Klijent) k);
        }
        else if(keDTO.getRole().equals(Vozac.class.getSimpleName())) {
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
        Korisnik k=this.korisnikServis.getKorisnikByEmail(uDto.getEmail());
        if(k.equals(null)){

            return (ResponseEntity<?>)ResponseEntity.internalServerError();
        }

        if (uDto.getName()!=null){
               k.setIme(uDto.getName());
        }
        if (uDto.getLastname()!=null){
            k.setPrezime(uDto.getLastname());
        }
        if ( uDto.getCity()!=null){
            k.setGrad(uDto.getCity());
        }
        if (uDto.getPhone()!=null){
            k.setBrojTel(uDto.getPhone());
        }
        this.korisnikServis.save(k);
        return ResponseEntity.ok(uDto);
    }
    @PostMapping("/updatePass")
    public ResponseEntity<?> updatePass(@RequestBody LoginDTO uDto) {
        Korisnik k=this.korisnikServis.getKorisnikByEmail(uDto.getEmail());
        if(k.equals(null)){
            return (ResponseEntity<?>)ResponseEntity.internalServerError();
        }
        k.setLozinka(uDto.getPassword());
        this.korisnikServis.save(k);
        return ResponseEntity.ok(k);
    }
    @PostMapping("/updateEmail")
    public ResponseEntity<?> updateEmail(@RequestBody LoginDTO email) {
        Korisnik k=this.korisnikServis.getKorisnikByEmail(email.getEmail());
        if(k.equals(null)){
            return (ResponseEntity<?>)ResponseEntity.internalServerError();
        }
        k.setEmail(email.getPassword());
        this.korisnikServis.save(k);
        return ResponseEntity.ok(k);
    }
    @PostMapping("/updateBilling")
    public ResponseEntity<?> updateBilling(@RequestBody BillingDTO uDto) {
        Korisnik k=this.korisnikServis.getKorisnikByEmail(uDto.getEmail());
        if(k.equals(null)){
            return (ResponseEntity<?>)ResponseEntity.internalServerError();
        }
        if(uDto.getRole().equals(Klijent.class.getSimpleName())) {

              ((Klijent) k).setNacinPlacanja(NacinPlacanja.valueOf(uDto.getBillingType()));
            if(uDto.getBillingData()!=null){
                 ((Klijent) k).setPodaciPlacanja(uDto.getBillingData());
              }

        }
        else if(uDto.getRole().equals(Vozac.class.getSimpleName())) {
            ((Vozac) k).setNacinPlacanja(NacinPlacanja.valueOf(uDto.getBillingType()));
            if(uDto.getBillingData()!=null){
                ((Vozac) k).setPodaciPlacanja(uDto.getBillingData());
            }
        }

        this.korisnikServis.save(k);
        return ResponseEntity.ok(uDto);
    }
}
