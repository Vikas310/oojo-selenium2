package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.BaseClass;

public class SearchFlightObject extends BaseClass {

    public SearchFlightObject(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[contains(@class,'qa-fromInput')]")
    private WebElement flightFromInput;

    @FindBy(xpath = "//*[contains(@class,'qa-toInput')]")
    private WebElement flightToInput;

    @FindBy(xpath = "//button[@class='searchButton__ButtonStyle-sc-focqmq-0 fPfEGX desktopsearchForm__ButtonStyled-sc-1ljf4qu-0 eHBwuP qa-btnSearch b-radius f-s-16 block-center block p-0-20 flex-no-shrink ga-home-search']")
    private WebElement submitSearchButtonInput;

    @FindBy(xpath = "//body/div[@id='__next']/div[@class='searchBlock__BlockStyle-sc-1kthfde-0 eexMRQ']/div[contains(@class,'searchBlock__ContainerStyle-sc-1kthfde-1 fSEXqz page-container')]/div[@class='block-center']/form[@class='styles__DeskSearchWrapStyle-sc-159alup-4 hIcZrB search-form-wrapper bg-white flex flex-a-center b-radius-5']/div[@class='desktop__DatesWrapperStyle-sc-bnma1e-1 NTMek flex-no-shrink']/div[@class='flex-j-sb lengthen']/div[1]/div[1]/div[2]/input[1]")
    private WebElement departureDateInput;

    @FindBy(xpath = "//body/div[@id='__next']/div[@class='searchBlock__BlockStyle-sc-1kthfde-0 eexMRQ']/div[contains(@class,'searchBlock__ContainerStyle-sc-1kthfde-1 fSEXqz page-container')]/div[@class='block-center']/form[@class='styles__DeskSearchWrapStyle-sc-159alup-4 hIcZrB search-form-wrapper bg-white flex flex-a-center b-radius-5']/div[@class='desktop__DatesWrapperStyle-sc-bnma1e-1 NTMek flex-no-shrink']/div[@class='flex-j-sb lengthen']/div[2]/div[1]/div[2]/input[1]")
    private WebElement departureReturnDateInput;

    @FindBy(xpath = "//*[contains(@class,'qa-paxMenu')]")
    private WebElement travellersInput;


    public SearchFlightObject fillFlightFrom(String value){
        this.waitForElementVisibility(flightFromInput,TIMEOUT_5);
        flightFromInput.clear();
        flightFromInput.sendKeys(value);
        flightFromInput.sendKeys(Keys.TAB);
        return this;
    }

    public SearchFlightObject fillFlightTo(String value){
        this.waitForElementVisibility(flightToInput,TIMEOUT_5);
        flightToInput.clear();
        flightToInput.sendKeys(value);
        flightToInput.sendKeys(Keys.TAB);
        return this;
    }

    public SearchFlightObject fillDepartureDate(String value){
        this.waitForElementVisibility(flightFromInput,TIMEOUT_5);
        departureDateInput.sendKeys(value);
        return this;
    }

    public SearchFlightObject fillDepartureReturnDate(String value){
        departureReturnDateInput.sendKeys(value);
        return this;
    }

    public void submitFlightSearch(){
        submitSearchButtonInput.click();
    }

    public void selectTravelers() {
        this.waitForElementVisibility(travellersInput,TIMEOUT_5);
        travellersInput.click();
    }
}
