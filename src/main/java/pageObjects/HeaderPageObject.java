package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.BaseClass;

public class HeaderPageObject extends BaseClass {

    public HeaderPageObject(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//*[@id='onetrust-accept-btn-handler']")
    public WebElement acceptCookiesButtonInput;

    @FindBy (xpath = "//a[contains(text(),'No thanks')]")
    public WebElement cancelMemberOfferInput;

    @FindBy(xpath = "//div[@class='loaderLarge__IconStyle-sc-1mw9lob-0 jdlvBU block-center']")
    public WebElement bookLoaderBeeInput;

    public void acceptCookies(){
        if(this.waitForElementVisibility(acceptCookiesButtonInput, TIMEOUT_5)){
            logWrite.info("Cookies are present - accept cookies");
            acceptCookiesButtonInput.click();
        }
    }

    public void cancelMemberOffer(){
        if(this.waitForElementVisibility(cancelMemberOfferInput,TIMEOUT_5)) {
            logWrite.info("Click No thanks in member offer popover");
            cancelMemberOfferInput.click();
        }
    }

    public void waitForLoadingBeeToLoad() {
        //this.waitForElementVisibility(bookLoaderBeeInput,TIMEOUT_10);
        this.waitForElementInvisibility(bookLoaderBeeInput,TIMEOUT_10);
    }
}
