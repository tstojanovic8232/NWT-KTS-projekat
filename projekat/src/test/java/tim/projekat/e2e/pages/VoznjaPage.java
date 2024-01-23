package tim.projekat.e2e.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class VoznjaPage {

        private WebDriver driver;

        // Define locators for the elements on your page
        private By clientNameLocator = By.xpath("//tbody//td[contains(@style, 'border-bottom-left-radius')]");
        private By dateLocator = By.xpath("//tbody//td[2]");
        private By priceLocator = By.xpath("//tbody//td[3]");
        private By originLocator = By.xpath("//tbody//td[4]");
        private By destinationLocator = By.xpath("//tbody//td[5]");
        private By startButtonLocator = By.xpath("//button[text()='start']");

    // Define locators for the elements on your page
    private By zavrsiVoznjuButtonLocator = By.xpath("//div[@id='dugmici']//button[@id='1']");
    private By zapocniteVoznjuButtonLocator = By.xpath("//div[@id='dugmici']//button[@id='2']");
    private By mapContainerLocator = By.xpath("//div[@class='map-container']");
    private By mapFrameLocator = By.xpath("//div[@class='map-frame']");
    private By mapLocator = By.xpath("//div[@id='map']");

    // Constructor to initialize the WebDriver


        // Constructor to initialize the WebDriver
        public VoznjaPage(WebDriver driver) {
            this.driver = driver;
        }

        // Method to get the client name text
        public String getClientName() {
            WebElement clientNameElement = driver.findElement(clientNameLocator);
            return clientNameElement.getText();
        }

        // Method to get the date text
        public String getDate() {
            WebElement dateElement = driver.findElement(dateLocator);
            return dateElement.getText();
        }

        // Method to get the price text
        public String getPrice() {
            WebElement priceElement = driver.findElement(priceLocator);
            return priceElement.getText();
        }

        // Method to get the origin text
        public String getOrigin() {
            WebElement originElement = driver.findElement(originLocator);
            return originElement.getText();
        }

        // Method to get the destination text
        public String getDestination() {
            WebElement destinationElement = driver.findElement(destinationLocator);
            return destinationElement.getText();
        }

        // Method to click the start button
        public void clickStartButton() {
            WebElement startButton = driver.findElement(startButtonLocator);
            startButton.click();
        }


    // Method to click the "Zavrsi voznju" button
    public void clickZavrsiVoznjuButton() {
        WebElement zavrsiVoznjuButton = driver.findElement(zavrsiVoznjuButtonLocator);
        zavrsiVoznjuButton.click();
    }

    // Method to click the "Zapocnite voznju" button
    public void clickZapocniteVoznjuButton() {
        WebElement zapocniteVoznjuButton = driver.findElement(zapocniteVoznjuButtonLocator);
        zapocniteVoznjuButton.click();
    }

    // Method to check if the "Zapocnite voznju" button is disabled
    public boolean isZapocniteVoznjuButtonDisabled() {
        WebElement zapocniteVoznjuButton = driver.findElement(zapocniteVoznjuButtonLocator);
        return zapocniteVoznjuButton.getAttribute("disabled") != null;
    }

    // Method to check if the map container is present
    public boolean isMapContainerPresent() {
        return driver.findElements(mapContainerLocator).size() > 0;
    }
    }


