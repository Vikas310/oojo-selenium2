package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.BaseClass;

public class PqTripDetailedViewPageObject extends BaseClass {

    public PqTripDetailedViewPageObject(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='l-h-1 price f-s-16 t-s-bold']")
    public WebElement pQDetailedViewFlightPriceInput;

    @FindBy(xpath = "//*[contains(text(),'Book Flight')]")
    public WebElement bookFlightButtonInput;

    @FindBy(xpath = "//*[@data-qa='pqDetailsDates']")
    public WebElement segmentTravelDate;

    @FindBy(xpath = "//button[@class='f-s-16 bg-white p-0-0']//*[@width='1em']")
    public WebElement closeTakeSegmentButtonInput;

    public String getSegmentFlightDate() {
        return segmentTravelDate.getText();
    }

    public WebElement getDetailedViewFlightPrice() {
        this.waitForElementVisibility(pQDetailedViewFlightPriceInput,TIMEOUT_5);
        return pQDetailedViewFlightPriceInput;
    }

    public PqTripDetailedViewPageObject closeTakeSegment() {
        closeTakeSegmentButtonInput.click();
        this.waitForElementInvisibility(segmentTravelDate,TIMEOUT_5);
        return this;
    }

    public PqTripDetailedViewPageObject clickBookFlight() {
        this.waitForElementVisibility(bookFlightButtonInput,TIMEOUT_5);
        bookFlightButtonInput.click();
        return this;
    }

}
