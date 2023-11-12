package tim.projekat.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import tim.projekat.model.Voznja;

import java.util.List;

public interface VoznjaRepo extends JpaRepository<Voznja, Long> {
    List<Voznja> getVoznjasByGotovaTrue();
    List<Voznja> getVoznjasByGotovaFalse(); // SELECT v FROM Voznja v WHERE v.gotova = false
}
