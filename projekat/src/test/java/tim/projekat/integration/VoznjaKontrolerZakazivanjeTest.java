package tim.projekat.integration;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tim.projekat.model.Vozac;
import tim.projekat.model.Voznja;
import tim.projekat.model.enums.NacinPlacanja;
import tim.projekat.requestDTO.VoznjaDTO;
import tim.projekat.servisi.KorisnikServis;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VoznjaKontrolerZakazivanjeTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private KorisnikServis korisnikServis;

    @Test
    @Order(1)
    public void testIfItReturns200OK() {
        // Arrange
        String baseUrl = "/drives/add";
        VoznjaDTO voznjaDTO = createValidVoznjaDTO();

        // Act
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(baseUrl, voznjaDTO, Void.class);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Order(2)
    public void testIfKlijentNotFound() {
        // Arrange
        String baseUrl = "/drives/add";
        VoznjaDTO voznjaDTO = createInvalidClientVoznjaDTO();

        // Act
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(baseUrl, voznjaDTO, Void.class);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    @Order(3)
    public void testIfNoDriversAreInactive() {
        // Arrange
        String baseUrl = "/drives/add";
        VoznjaDTO voznjaDTO = createValidVoznjaDTO();

        this.makeAllDriversInactive();

        // Act
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(baseUrl, voznjaDTO, Void.class);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());

    }

    @Test
    @Order(4)
    public void testDistanceCalculation() {
        // Arrange
        Vozac driver1 = createDriver("driver1", "44.8000, 20.4000");
        Vozac driver2 = createDriver("driver2", "44.8100, 20.4500");
        VoznjaDTO voznjaDTO = createValidVoznjaDTO();

        // Act
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/drives/add", voznjaDTO, Void.class);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Vozac selectedDriver = (Vozac) korisnikServis.getKorisnikByEmail(driver2.getEmail());
        assertNotNull(selectedDriver);
        assertTrue(selectedDriver.getStatus());

        Voznja voznja = new Voznja(voznjaDTO);
        assertTrue(selectedDriver.getVoznje().stream().anyMatch(v -> areVoznjasEqual(v, voznja)),
                "The driver's list should contain the voznja");
    }

    @Test
    @Order(5)
    public void testIfValidDriverIsSelected() {
        // Arrange
        this.makeAllDriversInactive();
        VoznjaDTO voznjaDTO = createValidVoznjaDTO();
        Vozac activeDriver = createDriver("activeDriver", "44.8200, 20.4600");

        // Act
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/drives/add", voznjaDTO, Void.class);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Vozac selectedDriver = (Vozac) korisnikServis.getKorisnikByEmail(activeDriver.getEmail());
        assertNotNull(selectedDriver);
        assertTrue(selectedDriver.getStatus());

        Voznja voznja = new Voznja(voznjaDTO);
        assertTrue(selectedDriver.getVoznje().stream().anyMatch(v -> areVoznjasEqual(v, voznja)),
                "The driver's list should contain the voznja");
    }

    @Test
    @Order(6)
    public void testEmptyDTO() {
        // Arrange
        String baseUrl = "/drives/add";
        VoznjaDTO voznjaDTO = new VoznjaDTO();

        // Act
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(baseUrl, voznjaDTO, Void.class);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    @Order(7)
    public void testNullInput() {
        // Arrange
        String baseUrl = "/drives/add";
        VoznjaDTO voznjaDTO = null;

        // Act
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(baseUrl, voznjaDTO, Void.class);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }


    private void makeAllDriversInactive() {
        List<Vozac> allDrivers = korisnikServis.getDrivers();
        for (Vozac driver : allDrivers) {
            driver.setStatus(false);
            korisnikServis.save(driver);
        }
    }

    private void makeOneDriverActive() {
        List<Vozac> allDrivers = korisnikServis.getDrivers();
        if (!allDrivers.isEmpty()) {
            Vozac driver = allDrivers.get(0);
            driver.setStatus(true);
            korisnikServis.save(driver);
        }
    }

    private void makeAllDriversActive() {
        List<Vozac> allDrivers = korisnikServis.getDrivers();
        for (Vozac driver : allDrivers) {
            driver.setStatus(true);
            korisnikServis.save(driver);
        }
    }

    private VoznjaDTO createValidVoznjaDTO() {
        return new VoznjaDTO("44.7866, 20.4489", "44.8120, 20.4619", "Napomena 1", "teateodora2000@gmail.com", 5.2, 1200, "2022-01-15T08:30");
    }

    private VoznjaDTO createInvalidClientVoznjaDTO() {
        return new VoznjaDTO("44.7866, 20.4489", "44.8120, 20.4619", "Napomena 1", "nonexistent_client@example.com", 5.2, 1200, "2022-01-15T08:30");
    }

    private Vozac createDriver(String email, String currentLocation) {
        Vozac driver = new Vozac();
        driver.setEmail(email);
        driver.setLozinka("password");  // Set a default password for testing
        driver.setIme("DriverFirstName");
        driver.setPrezime("DriverLastName");
        driver.setGrad("DriverCity");
        driver.setBrojTel("123456789");
        driver.setTrenutnaLokacija(currentLocation);
        driver.setNacinPlacanja(NacinPlacanja.PayPal);
        driver.setPodaciPlacanja("PaymentInfo");
        driver.setuVoznji(false);
        driver.setBlokiran(false);
        driver.setStatus(true);  // Set initially as inactive
        // You might need to set other fields based on your actual implementation

        // Save the driver in the repository if needed
        korisnikServis.save(driver);

        return driver;
    }

    private boolean areVoznjasEqual(Voznja v1, Voznja v2) {
        return Objects.equals(v1.getPolaziste(), v2.getPolaziste())
                && Objects.equals(v1.getDestinacija(), v2.getDestinacija())
                && Objects.equals(v1.getDatumVreme(), v2.getDatumVreme())
                && Objects.equals(v1.getNapomena(), v2.getNapomena())
                && Objects.equals(v1.getBrojKilometara(), v2.getBrojKilometara())
                && Objects.equals(v1.getCena(), v2.getCena())
                && Objects.equals(v1.getGotova(), v2.getGotova());
    }

}
