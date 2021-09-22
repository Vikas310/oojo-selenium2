package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.BaseClass;

public class SearchResultPageObject extends BaseClass {

    public SearchResultPageObject(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='nprogress']")
    public WebElement searchResultLoadingInput;

    @FindBy(xpath = "//*[contains(@class,'qa-tripOptionPQ')]")
    public WebElement qaTripOptionPq;


    public WebElement getTripOptionPriceByIndex (int index) {
        return driver.findElements(By.xpath("//*[contains(@class,'qa-tripOptionPQ')]//*[contains(@class,'qa-tripOptionPrice')]")).get(index);
    }

    public void waitForSearchLoad(){
        logWrite.info("Wait for search bar to appear");
        this.waitForElementVisibility(searchResultLoadingInput,TIMEOUT_5);
        logWrite.info("Wait for search bar to - disappear");
        this.waitForElementInvisibility(searchResultLoadingInput,TIMEOUT_5);
    }

    public WebElement getTripOptionByIndex(int index){
        return  driver.findElements(By.xpath("//*[contains(@class,'qa-tripOptionPQ')]")).get(index);
    }

    public void selectTripOptionPq(int index){
        this.waitForElementVisibility(getTripOptionByIndex(index),TIMEOUT_5);
        getTripOptionByIndex(index).click();
    }



}
