package tim.projekat.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import tim.projekat.model.Voznja;

public interface VoznjaRepo extends JpaRepository<Voznja, Long> {
}
