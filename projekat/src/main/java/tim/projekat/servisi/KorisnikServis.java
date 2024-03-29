package tim.projekat.servisi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tim.projekat.model.*;
import tim.projekat.repozitorijumi.KorisnikRepo;
import tim.projekat.repozitorijumi.TokenRepo;

import java.util.ArrayList;
import java.util.List;

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

    public void SetBlokiran(Korisnik user) {
        if (user.getClass().equals(Vozac.class)) {
            Vozac v = (Vozac) user;
            // dalje
            v.setBlokiran(true);
            this.korisnikRepo.save(v);
        }
        if (user.getClass().equals(Klijent.class)) {
            Klijent k = (Klijent) user;
            // dalje
            k.setBlokiran(true);
            this.korisnikRepo.save(k);
        }
    }

    public void setNeblokiran(Korisnik user) {
        if (user.getClass().equals(Vozac.class)) {
            Vozac v = (Vozac) user;
            v.setBlokiran(false);
            this.korisnikRepo.save(v);
        }
        if (user.getClass().equals(Klijent.class)) {
            Klijent k = (Klijent) user;
            k.setBlokiran(false);
            this.korisnikRepo.save(k);
        }
    }

    public Korisnik getKorisnikByEmail(String email) {
//        System.out.println(email);
        return this.korisnikRepo.getKorisnikByEmail(email);
    }

    public void save(Korisnik k) {
        this.korisnikRepo.save(k);
    }

    public List<Korisnik> findAllByType(String userType) {
        List<Korisnik> res = new ArrayList<>();
        List<Korisnik> li = this.korisnikRepo.findAll();
        for (Korisnik k : li) {
            if (k.getClass().getSimpleName().equals(userType)) res.add(k);
        }
        return res;
    }

    public Klijent getKlijent(Voznja v) {
        return (Klijent) this.korisnikRepo.getKlijentByVoznjeContains(v.getId());
    }

    public Vozac getVozac(Voznja v) {
        return (Vozac) this.korisnikRepo.getVozacByVoznjeContains(v.getId());
    }

    public void switchStatus(Vozac v, boolean status) {
        v.setStatus(status);
        this.korisnikRepo.save(v);
    }

    public List<Klijent> getClients() {
        return this.korisnikRepo.getAllClients();
    }

    public List<Vozac> getDrivers() {
        return this.korisnikRepo.getAllDrivers();
    }


}
