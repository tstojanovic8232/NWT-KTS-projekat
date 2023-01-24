package tim.projekat.servisi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tim.projekat.model.Korisnik;
import tim.projekat.repozitorijumi.KorisnikRepo;

@Service
public class KorisnikServis {
    @Autowired
    KorisnikRepo korisnikRepo;

    public Korisnik getKorisnikByEmail(String email) {
        return this.korisnikRepo.getKorisnikByEmail(email);
    }
    public void save(Korisnik k){
         this.korisnikRepo.save(k);
    }
}
