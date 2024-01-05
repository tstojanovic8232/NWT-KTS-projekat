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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class KorisnikRepoTests {


    @Autowired
    private KorisnikRepo korisnikRepo;

    private Korisnik korisnik;
    private Klijent klijent;
    private Vozac vozac;
    private Long existingVoznjaId;
    private Long nonExistingVoznjaId;
    private String existingEmail;
    private String nonExistingEmail;

    @BeforeEach
    void setUp() {

        klijent = new Klijent();
        klijent.setuVoznji(false);
        klijent.setNacinPlacanja(NacinPlacanja.PayPal);
        klijent.setBlokiran(false);
        klijent.setPodaciPlacanja("teateodora2000@gmail.com");
        klijent.setId(3L);
        klijent.setEmail("teateodora2000@gmail.com");
        klijent.setLozinka("111");
        klijent.setAktivan(true);
        klijent.setGrad("Novi Sad");
        klijent.setBrojTel("063/7077915");
        klijent.setIme("Tea");
        klijent.setPrezime("Stojanovic");

        korisnik = klijent;

        vozac = new Vozac();
        Vozilo vozilo = new Vozilo("ABC123", 100.0);
        vozilo.setId(1L);
        vozac.setVozilo(vozilo);
        vozac.setBlokiran(false);
        vozac.setNacinPlacanja(NacinPlacanja.Bitcoin);
        vozac.setTrenutnaLokacija("45.253893, 19.847793");
        vozac.setuVoznji(false);
        vozac.setPodaciPlacanja("perica@gmail.com");
        vozac.setStatus(true);
        vozac.setId(5L);
        vozac.setEmail("perica@gmail.com");
        vozac.setLozinka("000");
        vozac.setAktivan(true);
        vozac.setGrad("Novi Sad");
        vozac.setBrojTel("064/1834927");
        vozac.setIme("Perica");
        vozac.setPrezime("Peric");

        existingVoznjaId = 1L;
        nonExistingVoznjaId = 20L;
        existingEmail = "teateodora2000@gmail.com";
        nonExistingEmail = "nonexisting@example.com";

        Voznja voznja1 = new Voznja();
        voznja1.setId(1L);
        voznja1.setPolaziste("44.7866, 20.4489");
        voznja1.setDestinacija("44.8120, 20.4619");
        voznja1.setBrojKilometara(5.2);
        voznja1.setNapomena("Napomena 1");
        voznja1.setDatumVreme(LocalDateTime.parse("2022-01-15T08:30:00"));
        voznja1.setCena(1200.0);
        voznja1.setGotova(true);
        voznja1.setOcena(0);

        Voznja voznja2 = new Voznja();
        voznja2.setId(2L);
        voznja2.setPolaziste("44.8093, 20.4682");
        voznja2.setDestinacija("44.8225, 20.4546");
        voznja2.setBrojKilometara(7.8);
        voznja2.setNapomena("Napomena 2");
        voznja2.setDatumVreme(LocalDateTime.parse("2022-01-16T12:15:00"));
        voznja2.setCena(1800.0);
        voznja2.setGotova(true);
        voznja2.setOcena(0);

        // Klijent
        List voznjeKlijent = new ArrayList<Voznja>();
        voznjeKlijent.add(voznja1);
        klijent.setVoznje(voznjeKlijent);

        // Vozac
        List voznjeVozac = new ArrayList<Voznja>(voznjeKlijent);
        voznjeVozac.add(voznja2);
        vozac.setVoznje(voznjeVozac);
    }

    @Test
    void givenExistingEmail_whenGetKorisnikByEmail_thenReturnKorisnik() {
        // Act
        Korisnik result = korisnikRepo.getKorisnikByEmail(existingEmail);

        // Assert
//        assertThat(result).isEqualTo(korisnik);
        assertThat(result).usingRecursiveComparison().isEqualTo(korisnik);

    }

    @Test
    void givenNonExistingEmail_whenGetKorisnikByEmail_thenReturnNull() {
        // Act
        Korisnik result = korisnikRepo.getKorisnikByEmail(nonExistingEmail);

        // Assert
        assertThat(result).isNull();
    }

    @Test
    void givenExistingVoznjaId_whenGetKlijentByVoznjeContains_thenReturnKlijent() {
        // Act
        Korisnik result = korisnikRepo.getKlijentByVoznjeContains(existingVoznjaId);

        // Assert
//        assertThat(result).isEqualTo(klijent);
        assertThat(result).usingRecursiveComparison().isEqualTo(klijent);
    }

    @Test
    void givenNonExistingVoznjaId_whenGetKlijentByVoznjeContains_thenReturnNull() {
        // Act
        Korisnik result = korisnikRepo.getKlijentByVoznjeContains(nonExistingVoznjaId);

        // Assert
        assertThat(result).isNull();
    }

    @Test
    void givenExistingVoznjaId_whenGetVozacByVoznjeContains_thenReturnVozac() {
        // Act
        Korisnik result = korisnikRepo.getVozacByVoznjeContains(existingVoznjaId);

        // Assert
//        assertThat(result).isEqualTo(vozac);
        assertThat(result).usingRecursiveComparison().isEqualTo(vozac);
    }

    @Test
    void givenNonExistingVoznjaId_whenGetVozacByVoznjeContains_thenReturnNull() {
        // Act
        Korisnik result = korisnikRepo.getVozacByVoznjeContains(nonExistingVoznjaId);

        // Assert
        assertThat(result).isNull();
    }

    @Test
    void whenGetAllClients_thenReturnListOfKlijent() {
        // Act
        List<Klijent> result = korisnikRepo.getAllClients();

        // Assert
        assertThat(result).hasSize(2);
    }

    @Test
    void whenGetAllDrivers_thenReturnListOfVozac() {
        // Act
        List<Vozac> result = korisnikRepo.getAllDrivers();

        // Assert
        assertThat(result).hasSize(2);
    }

}
