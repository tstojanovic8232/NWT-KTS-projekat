package tim.projekat.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import tim.projekat.model.Vozilo;

public interface VoziloRepo extends JpaRepository<Vozilo, Long> {
}
