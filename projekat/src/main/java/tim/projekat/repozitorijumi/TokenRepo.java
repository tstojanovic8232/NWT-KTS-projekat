package tim.projekat.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import tim.projekat.model.Korisnik;
import tim.projekat.model.VerificationToken;

public interface TokenRepo extends JpaRepository<VerificationToken,Long>{
    VerificationToken findByToken(String token);
    VerificationToken findByUser(Korisnik user);

}
