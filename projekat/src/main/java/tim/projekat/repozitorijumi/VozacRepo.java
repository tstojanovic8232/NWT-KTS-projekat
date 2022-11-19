package tim.projekat.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import tim.projekat.model.Vozac;

public interface VozacRepo extends JpaRepository<Vozac, Long> {
}
