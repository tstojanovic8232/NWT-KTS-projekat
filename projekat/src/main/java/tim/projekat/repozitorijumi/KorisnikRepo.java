package tim.projekat.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tim.projekat.model.Klijent;
import tim.projekat.model.Korisnik;
import tim.projekat.model.Vozac;
import tim.projekat.model.Voznja;

import java.util.List;

public interface KorisnikRepo extends JpaRepository<Korisnik, Long> {
    Korisnik getKorisnikByEmail(String email); // SELECT k FROM Korisnik WHERE k.email = :email
    @Query("SELECT k FROM Klijent k JOIN k.voznje v WHERE v.id = :voznja_id AND TYPE(k) = Klijent")
    Korisnik getKlijentByVoznjeContains(@Param("voznja_id") Long id);

    @Query("SELECT k FROM Vozac k JOIN k.voznje v WHERE v.id = :voznja_id AND TYPE(k) = Vozac")
    Korisnik getVozacByVoznjeContains(@Param("voznja_id") Long id);

    @Query("SELECT k FROM Klijent k WHERE TYPE(k) = Klijent")
    List<Klijent> getAllClients();

    @Query("SELECT k FROM Vozac k WHERE TYPE(k) = Vozac")
    List<Vozac> getAllDrivers();
}
