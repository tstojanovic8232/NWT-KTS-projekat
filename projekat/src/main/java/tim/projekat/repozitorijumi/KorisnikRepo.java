package tim.projekat.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import tim.projekat.model.Korisnik;

public interface KorisnikRepo extends JpaRepository<Korisnik, Long> {
}
