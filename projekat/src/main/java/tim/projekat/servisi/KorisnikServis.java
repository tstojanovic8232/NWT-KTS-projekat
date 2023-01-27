package tim.projekat.servisi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tim.projekat.model.Korisnik;
import tim.projekat.model.VerificationToken;
import tim.projekat.repozitorijumi.KorisnikRepo;
import tim.projekat.repozitorijumi.TokenRepo;

@Service
public class KorisnikServis {
    @Autowired
    KorisnikRepo korisnikRepo;

    @Autowired
    private TokenRepo tokenRepo;
    public void createVerificationToken(Korisnik user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepo.save(myToken);
    }

    public VerificationToken getVerificationToken(String token) {
        return tokenRepo.findByToken(token);
    }

    public void enableUser(Korisnik user) {
        user.setAktivan(true);
        this.korisnikRepo.save(user);
    }

    public Korisnik getKorisnikByEmail(String email) {
        System.out.println(email);
        return this.korisnikRepo.getKorisnikByEmail(email);
    }
    public void save(Korisnik k){
         this.korisnikRepo.save(k);
    }

}
