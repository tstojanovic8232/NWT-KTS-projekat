package tim.projekat.e2e.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tim.projekat.e2e.pages.KlijentHomePage;
import tim.projekat.e2e.pages.LoginPage;
import tim.projekat.e2e.pages.RacunPage;

import java.time.Duration;

public class ZakazivanjeTest extends TestBase {
    static final String KORISNIK = "teateodora2000@gmail.com";
    static final String LOZINKA = "111";
    static final String POLAZISTE = "Adi Endrea 27";
    static final String DESTINACIJA = "Bulevar Oslobodjenja 37";
    static final String NAPOMENA = "napomena";
    static final String TIP_VOZILA = "Karavan";


    @Test
    public void makeReservationOK() {
        driver.get("http://localhost:4200/login");

        LoginPage loginPage = new LoginPage(driver);
        KlijentHomePage reservationPage = new KlijentHomePage(driver);
        RacunPage racunPage = new RacunPage(driver);

        loginPage.enterEmail(KORISNIK);
        loginPage.enterPassword(LOZINKA);
        loginPage.clickSignInButton();


        Assert.assertTrue(reservationPage.isPageOpened());
        reservationPage.setAddressFrom(POLAZISTE);
        reservationPage.setAddressTo(DESTINACIJA);
        reservationPage.setNapomena(NAPOMENA);
        reservationPage.selectVehicleType(TIP_VOZILA);

        reservationPage.clickReserveButton();

        Assert.assertTrue(racunPage.isPageOpened());
        Assert.assertEquals(racunPage.getFrom(), POLAZISTE);
        Assert.assertEquals(racunPage.getTo(), DESTINACIJA);
        Assert.assertEquals(racunPage.getNap(), NAPOMENA);
        Assert.assertEquals(racunPage.getTip(), TIP_VOZILA);
        String cena = racunPage.getCena();
        System.out.println("Total Price: " + cena);

        racunPage.clickConfirmPaymentButton();
    }


    @Test
    public void makeInvalidReservation() {
        driver.get("http://localhost:4200/login");

        LoginPage loginPage = new LoginPage(driver);
        KlijentHomePage reservationPage = new KlijentHomePage(driver);

        loginPage.enterEmail(KORISNIK);
        loginPage.enterPassword(LOZINKA);
        loginPage.clickSignInButton();

        Assert.assertTrue(reservationPage.isPageOpened());
        reservationPage.setAddressFrom(POLAZISTE);
        reservationPage.selectVehicleType(TIP_VOZILA);

        Assert.assertTrue(reservationPage.checkIfDisabled());
    }
}
