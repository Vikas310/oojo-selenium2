package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.BaseClass;

import java.util.List;

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

    @FindBy(xpath = "//*[@data-qa='modalCloseBtn']")
    public WebElement closeTakeSegmentButtonInput;

    @FindBy(xpath = "//*[contains(@class,'alternativeDatesLabel')]")
    public WebElement alternativeFlightLabelInput;

    @FindBy(xpath = "//*[@data-qa='pqFlightInfo']")
    public WebElement flightInfoInput;


    public String getSegmentFlightDate() {
        return segmentTravelDate.getText();
    }

    public WebElement getDetailedViewFlightPrice() {
        this.waitForElementVisibility(pQDetailedViewFlightPriceInput,TIMEOUT_5);
        return pQDetailedViewFlightPriceInput;
    }

    public String getFlightInfo(){
        return flightInfoInput.getText();
    }

    public List<WebElement> getTakeSegmentFromDates () {
        return driver.findElements(By.xpath("//*[@data-qa='pqDetailsDateFrom']"));
    }

    public WebElement getAlternativeFlightLabel() {
        return alternativeFlightLabelInput;
    }

    public PqTripDetailedViewPageObject closeTakeSegment() {
        this.waitForElementVisibility(closeTakeSegmentButtonInput,TIMEOUT_5);
        closeTakeSegmentButtonInput.click();
        return this;
    }

    public PqTripDetailedViewPageObject clickBookFlight() {
        this.waitForElementVisibility(bookFlightButtonInput,TIMEOUT_5);
        bookFlightButtonInput.click();
        return this;
    }

}
