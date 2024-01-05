package tim.projekat.unit.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tim.projekat.model.*;
import tim.projekat.model.enums.NacinPlacanja;
import tim.projekat.repozitorijumi.KorisnikRepo;
import tim.projekat.repozitorijumi.VoznjaRepo;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class VoznjaRepoTests {

    @Autowired
    private VoznjaRepo voznjaRepo;

    @Test
    void testGetVoznjasByGotovaTrue() {
        // Arrange
        Voznja voznja1 = createVoznja(true);

        Voznja voznja2 = createVoznja(true);

        voznjaRepo.saveAll(List.of(voznja1, voznja2));

        // Act
        List<Voznja> result = voznjaRepo.getVoznjasByGotovaTrue();

        // Assert
        assertThat(result).hasSize(2);
        assertThat(result).containsAll(List.of(voznja1, voznja2));
    }

    @Test
    void testGetVoznjasByGotovaFalse() {
        // Arrange
        Voznja voznja1 = createVoznja(false);
        Voznja voznja2 = createVoznja(false);
        voznjaRepo.saveAll(List.of(voznja1, voznja2));
        System.out.println(voznjaRepo.findAll());


        List<Voznja> result = voznjaRepo.getVoznjasByGotovaFalse();


        assertThat(result).hasSize(2);

        assertThat(result).usingRecursiveComparison().isEqualTo(List.of(voznja1, voznja2));
    }
    @Test
    void testGetVoznjaByGotovaFalseReturnsEmptyList() {
        // Arrange: Insert Voznja instances with 'gotova' set to false
        Voznja voznja1 = createVoznja(true);
        Voznja voznja2 = createVoznja(true);
        voznjaRepo.saveAll(List.of(voznja1,voznja2));
        // Act
        List<Voznja> result = voznjaRepo.getVoznjasByGotovaFalse();

        // Assert
        assertThat(result).isEmpty();
    }

    @Test
    void testGetVoznjaByGotovaTrueReturnsEmptyList() {
        // Arrange: Insert Voznja instances with 'gotova' set to false
        Voznja voznja1 = createVoznja(false);
        Voznja voznja2 = createVoznja(false);
        voznjaRepo.saveAll(List.of(voznja1, voznja2));

        // Act
        List<Voznja> result = voznjaRepo.getVoznjasByGotovaTrue();

        // Assert
        assertThat(result).isEmpty();
    }

    // Helper method to create a Voznja object
    private Voznja createVoznja(boolean gotova) {
        Voznja voznja = new Voznja();
        voznja.setPolaziste("Start");

        voznja.setDestinacija("Destination");
        voznja.setDatumVreme(LocalDateTime.now());
        voznja.setCena(100.0);
        voznja.setGotova(gotova);
        // Set other properties as needed
        return voznja;
    }
}
