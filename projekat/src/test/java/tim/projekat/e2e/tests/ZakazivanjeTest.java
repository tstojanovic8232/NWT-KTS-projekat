package tim.projekat.e2e.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tim.projekat.e2e.pages.KlijentHomePage;
import tim.projekat.e2e.pages.RacunPage;

public class ZakazivanjeTest extends TestBase {

    static final String POLAZISTE = "Adi Endrea 27";
    static final String DESTINACIJA = "Bulevar Oslobodjenja 37";
    static final String NAPOMENA = "napomena";
    static final String TIP_VOZILA = "Karavan";

    @Test
    public void makeReservation() {
        driver.get("https://localhost:4200/client-home");

        KlijentHomePage reservationPage = new KlijentHomePage(driver);
        RacunPage racunPage = new RacunPage(driver);

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
}
