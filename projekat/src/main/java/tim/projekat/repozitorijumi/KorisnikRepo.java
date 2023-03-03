package tim.projekat.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tim.projekat.model.Klijent;
import tim.projekat.model.Korisnik;
import tim.projekat.model.Voznja;

public interface KorisnikRepo extends JpaRepository<Korisnik, Long> {
    Korisnik getKorisnikByEmail(String email);
    @Query("SELECT k FROM Klijent k JOIN k.voznje v WHERE v.id = :voznja_id AND TYPE(k) = Klijent")
    Korisnik getKlijentByVoznjeContains(@Param("voznja_id") Long id);

    @Query("SELECT k FROM Vozac k JOIN k.voznje v WHERE v.id = :voznja_id AND TYPE(k) = Vozac")
    Korisnik getVozacByVoznjeContains(@Param("voznja_id") Long id);
}
