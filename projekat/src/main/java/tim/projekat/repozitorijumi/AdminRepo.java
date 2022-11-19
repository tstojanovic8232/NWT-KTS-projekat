package tim.projekat.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import tim.projekat.model.Administrator;

public interface AdminRepo extends JpaRepository<Administrator, Long> {
}
