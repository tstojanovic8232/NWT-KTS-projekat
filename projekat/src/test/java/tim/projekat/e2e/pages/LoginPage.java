package tim.projekat.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;

    // Define locators for the elements on the login page
    private By emailInputLocator = By.xpath("//input[@placeholder='Email']");
    private By passwordInputLocator = By.xpath("//input[@placeholder='Password']");
    private By signInButtonLocator = By.xpath("//button[@type='submit' and contains(@class, 'submit')]");

    // Constructor to initialize the WebDriver
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to enter email in the email input field
    public void enterEmail(String email) {
        WebElement emailInput = driver.findElement(emailInputLocator);
        emailInput.sendKeys(email);
    }

    // Method to enter password in the password input field
    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(passwordInputLocator);
        passwordInput.sendKeys(password);
    }

    // Method to click the Sign In button
    public void clickSignInButton() {
        WebElement signInButton = driver.findElement(signInButtonLocator);
        signInButton.click();
        System.out.println("kliknuto");
    }

    // Method to perform login
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSignInButton();
    }
}
