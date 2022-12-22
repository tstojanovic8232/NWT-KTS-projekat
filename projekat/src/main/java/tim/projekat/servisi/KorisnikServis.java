package tim.projekat.servisi;

import org.springframework.stereotype.Service;
import tim.projekat.model.Korisnik;
import tim.projekat.repozitorijumi.KorisnikRepo;

@Service
public class KorisnikServis {
    KorisnikRepo korisnikRepo;

    public Korisnik getUserByEmail(String email) {
        return this.korisnikRepo.getKorisnikByEmail(email);
    }
}
