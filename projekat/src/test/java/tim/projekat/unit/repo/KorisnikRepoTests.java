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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class KorisnikRepoTests {


    @Autowired
    private KorisnikRepo korisnikRepo;
    @Autowired
    private VoznjaRepo voznjaRepo;

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

        Voznja voznja1 = createVoznja("44.7866, 20.4489", "44.8120, 20.4619", 5.2, "Napomena 1", "2022-01-15T08:30:00", 1200.0, true, 0);
        Voznja voznja2 = createVoznja("44.8093, 20.4682", "44.8225, 20.4546", 7.8, "Napomena 2", "2022-01-16T12:15:00", 1800.0, true, 0);

        // Klijent
        List<Voznja> voznjeKlijent = new ArrayList<>();
        voznjeKlijent.add(voznja1);
        klijent.setVoznje(voznjeKlijent);

        // Vozac
        List<Voznja> voznjeVozac = new ArrayList<>(voznjeKlijent);
        voznjeVozac.add(voznja2);
        vozac.setVoznje(voznjeVozac);

        voznjaRepo.saveAll(voznjeVozac);
        korisnikRepo.save(klijent);
        korisnikRepo.save(vozac);

        existingVoznjaId = voznjaRepo.findAll().get(0).getId();
        nonExistingVoznjaId = 20L;
        existingEmail = "teateodora2000@gmail.com";
        nonExistingEmail = "nonexisting@example.com";
    }

    @Test
    void givenExistingEmail_whenGetKorisnikByEmail_thenReturnKorisnik() {
        // Act
        Korisnik result = korisnikRepo.getKorisnikByEmail(existingEmail);

        // Assert
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
        System.out.println(korisnikRepo.findAll());
        System.out.println(voznjaRepo.findAll());
        System.out.println(result);
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
        System.out.println(korisnikRepo.findAll());
        System.out.println(voznjaRepo.findAll());
        System.out.println(result);
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

    private Voznja createVoznja(String polaziste, String destinacija, double brojKilometara, String napomena, String datumVreme, double cena, boolean gotova, int ocena) {
        Voznja voznja = new Voznja();
        voznja.setPolaziste(polaziste);
        voznja.setDestinacija(destinacija);
        voznja.setBrojKilometara(brojKilometara);
        voznja.setNapomena(napomena);
        voznja.setDatumVreme(LocalDateTime.parse(datumVreme));
        voznja.setCena(cena);
        voznja.setGotova(gotova);
        voznja.setOcena(ocena);
        return voznja;
    }

}
