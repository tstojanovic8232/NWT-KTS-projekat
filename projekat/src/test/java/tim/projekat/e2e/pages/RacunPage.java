package tim.projekat.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RacunPage {
    private WebDriver webDriver;


    @FindBy(xpath = "//div[@class='receipt']//table//tr[1]//td[@class='price']")
    private WebElement from;

    @FindBy(xpath = "//div[@class='receipt']//table//tr[2]//td[@class='price']")
    private WebElement to;

    @FindBy(xpath = "//div[@class='receipt']//table//tr[3]//td[@class='price']")
    private WebElement nap;

    @FindBy(xpath = "//div[@class='receipt']//table//tr[4]//td[@class='price']")
    private WebElement tip;

    @FindBy(xpath = "//div[@class='receipt']//table//tr[@class='total']//td[@class='price']")
    private WebElement cena;

    @FindBy(css = ".receipt button")
    private WebElement confirmPaymentButton;

    public RacunPage(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getFrom() {
        return from.getText();
    }

    public String getTo() {
        return to.getText();
    }

    public String getNap() {
        return nap.getText();
    }

    public String getTip() {
        return tip.getText();
    }

    public String getCena() {
        return cena.getText();
    }

    public void clickConfirmPaymentButton() {
        confirmPaymentButton.click();
    }

    public boolean isPageOpened() {
        try {
            new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".receipt .table")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
