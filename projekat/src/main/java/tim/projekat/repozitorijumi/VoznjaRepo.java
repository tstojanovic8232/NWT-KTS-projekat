package tim.projekat.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import tim.projekat.model.Klijent;
import tim.projekat.model.Vozac;
import tim.projekat.model.Voznja;

import java.util.List;

public interface VoznjaRepo extends JpaRepository<Voznja, Long> {
    List<Voznja> getVoznjasByVozacAndGotovaTrue(Vozac v);
    List<Voznja> getVoznjasByVozacAndGotovaFalse(Vozac v);
    List<Voznja> getVoznjasByKlijentiContainsAndGotovaTrue(Klijent k);
    List<Voznja> getVoznjasByKlijentiContainsAndGotovaFalse(Klijent k);
}
