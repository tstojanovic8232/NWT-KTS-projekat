package tim.projekat.e2e.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class KlijentHomePage {
    private WebDriver webDriver;

    @FindBy(id = "addressFrom")
    private WebElement addressFrom;

    @FindBy(id = "addressTo")
    private WebElement addressTo;

    @FindBy(id = "nap")
    private WebElement nap;

    @FindBy(id = "tip")
    private WebElement tip;

    @FindBy(xpath = "//button[@name='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//button[@id='sim']")
    private WebElement simulateDriveButton;

    public KlijentHomePage(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setAddressFrom(String address) {
        addressFrom.clear();
        addressFrom.sendKeys(address);
    }

    public void setAddressTo(String address) {
        addressTo.clear();
        addressTo.sendKeys(address);
    }

    public void setNapomena(String napomena) {
        nap.clear();
        nap.sendKeys(napomena);
    }

    public void selectVehicleType(String vehicleType) {
        tip.click();
        webDriver.findElement(By.xpath("//option[text()='" + vehicleType + "']")).click();
    }

    public void clickReserveButton() {
        submitButton.click();
    }

    public boolean checkIfDisabled() {
        return !submitButton.isEnabled();
    }

    public void clickSimulateDriveButton() {
        simulateDriveButton.click();
    }

    public boolean isPageOpened() {
        try {
            new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(
                    webDriver -> {
                        WebElement element = webDriver.findElement(By.id("map"));
                        return element.isDisplayed();
                    });
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
