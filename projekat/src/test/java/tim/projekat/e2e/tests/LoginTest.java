package tim.projekat.e2e.tests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import tim.projekat.e2e.pages.LoginPage;

public class LoginTest {

    @Test
    public void login_right_Cred_test() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        // Navigate to your login page


        driver.get("http://localhost:4200/login");

        // Create an instance of LoginPage
        LoginPage loginPage = new LoginPage(driver);

        // Use the loginPage object to perform login
        loginPage.login("pera@gmail.com", "222");
//        loginPage.login("nonexistantuser","nonexistantpassword");

        // Wait for some time to see the result (optional)
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close the browser
        driver.quit();
    }

    @Test
    public void login_wrong_Cred_test() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        driver.get("http://localhost:4200/login");

        // Create an instance of LoginPage
        LoginPage loginPage = new LoginPage(driver);

        // Use the loginPage object to perform login

        loginPage.login("nonexistantuser","nonexistantpassword");

        // Wait for some time to see the result (optional)
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close the browser
        driver.quit();
    }



}
