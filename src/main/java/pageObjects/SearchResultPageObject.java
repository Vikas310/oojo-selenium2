package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import selenium.BaseClass;

import java.util.List;

public class SearchResultPageObject extends BaseClass {

    public SearchResultPageObject(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='nprogress']")
    public WebElement searchResultLoadingInput;

    @FindBy(xpath = "//*[contains(@class,'qa-tripOptionPQ')]")
    public WebElement qaTripOptionPq;

    @FindBy (xpath = "//*[contains(@class,'qa-searchStats')]")
    public WebElement searchStats;

    @FindBy (xpath = "//*[contains(@class,'qa-searchStats')]//span[1]")
    public WebElement flightFoundInput;

    @FindBy (xpath = "//*[contains(@class,'qa-searchSortTab0')]")
    public WebElement cheapestFlightsFilterButtonInput;

    @FindBy (xpath = "//*[contains(@class,'qa-searchSortTab1')]")
    public WebElement bestFlightsFilterButtonInput;

    @FindBy (xpath = "//*[contains(@class,'qa-searchSortTab2')]")
    public WebElement fastestFlightFilterButtonInput;

    public int getFoundFlightCount(){
        return driver.findElements(By.xpath("//*[contains(@class,'itemContext')]//*[contains(@class,'flightdeskroute')]")).size();
    }

    public String getSearchStats(){
        return searchStats.getText();
    }

    public WebElement getTripOptionPriceByIndex (int index) {
        return driver.findElements(By.xpath("//*[contains(@class,'qa-tripOptionPQ')]//*[contains(@class,'qa-tripOptionPrice')]")).get(index);
    }

    public String getFlightStartDate(int index){
        return driver.findElements(By.xpath("//*[@data-qa='pqDateFrom']")).get(index).getText();
    }

    public List<WebElement> getAllFlightStartDates(){
        return driver.findElements(By.xpath("//*[@data-qa='pqDateFrom']"));
    }

    public WebElement getSearchLoadingBar (){
        return driver.findElement(By.xpath("//div[@id='nprogress']"));
    }

    public void waitForSearchLoad(){
        logWrite.info("Wait for search bar to - disappear");
        this.waitForElementInvisibility(this.getSearchLoadingBar(),TIMEOUT_80);
    }

    public WebElement getTripOptionByIndex(int index){
        return  driver.findElements(By.xpath("//*[contains(@class,'qa-tripOptionPQ')]")).get(index);
    }

    public void selectTripOptionPq(int index){
        Actions actions = new Actions(driver);
        this.waitForElementVisibility(getTripOptionByIndex(index),TIMEOUT_5);
        actions.moveToElement(getTripOptionByIndex(index));
        getTripOptionByIndex(index).click();
    }

    public SearchResultPageObject selectCheapestFlights() {
        this.waitForElementVisibility(cheapestFlightsFilterButtonInput,TIMEOUT_5);
        cheapestFlightsFilterButtonInput.click();
        return this;
    }

    public SearchResultPageObject selectBestFlights() {
        this.waitForElementVisibility(bestFlightsFilterButtonInput,TIMEOUT_5);
        bestFlightsFilterButtonInput.click();
        return this;
    }

    public SearchResultPageObject selectFastestFlights() {
        this.waitForElementVisibility(fastestFlightFilterButtonInput,TIMEOUT_5);
        fastestFlightFilterButtonInput.click();
        return this;
    }



}
