package tim.projekat.e2e.tests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import tim.projekat.e2e.pages.LoginPage;
import tim.projekat.e2e.pages.VoznjaPage;

public class ZavrsetakTest extends TestBase{

    @Test
    public void ZavrsiVoznjuTest() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.get("http://localhost:4200/login");

        // Create an instance of LoginPage
        LoginPage loginPage = new LoginPage(driver);

        // Use the loginPage object to perform login

        loginPage.login("nikica@gmail.com","999");

        // Wait for some time to see the result (optional)
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Navigate to your page
     // Replace with the actual URL of your page

        // Create an instance of YourPage
        VoznjaPage yourPage = new VoznjaPage(driver);

        // Use the YourPage object to click the start button
        yourPage.clickStartButton();

        yourPage.clickZapocniteVoznjuButton();

        // Wait for some time to see the result (optional)
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        yourPage.clickZavrsiVoznjuButton();

        // Close the browser
        driver.quit();
    }
}
