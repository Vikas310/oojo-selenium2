package pageObjects;

import org.openqa.selenium.By;
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

    @FindBy(xpath = "//div[contains(@class,'loaderLarge__IconStyle')]")
    public WebElement bookLoaderBeeInput;

    @FindBy(xpath = "//*[contains(@class,'qa-mainLogo')]")
    public WebElement oojoMainLogoInput;


    private WebElement getLoadingBeeIcon() {
        return driver.findElement(By.xpath("//div[contains(@class,'loaderLarge__IconStyle')]"));
    }

    public void acceptCookies(){
        if(this.waitForElementVisibility(acceptCookiesButtonInput, TIMEOUT_5)){
            logWrite.info("Cookies are present - accept cookies");
            acceptCookiesButtonInput.click();
        }
    }

    public void navigateToMainPage(){
        this.waitForElementToBeClickable(oojoMainLogoInput,TIMEOUT_10);
        oojoMainLogoInput.click();
    }

    public void cancelMemberOffer(){
        if(this.waitForElementVisibility(cancelMemberOfferInput,TIMEOUT_5)) {
            logWrite.info("Click No thanks in member offer popover");
            cancelMemberOfferInput.click();
        }
    }

    public void waitForLoadingBeeToLoad() {
        this.waitForElementVisibility(this.getLoadingBeeIcon(),TIMEOUT_10);
        this.waitForElementInvisibility(this.getLoadingBeeIcon(),TIMEOUT_80);
    }
}
