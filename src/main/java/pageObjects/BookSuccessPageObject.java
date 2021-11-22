package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.BaseClass;

public class BookSuccessPageObject extends BaseClass {

    public BookSuccessPageObject(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[contains(text(),'Thank you for completing your booking!')]")
    public WebElement thanksForBookingMessageInput;

    @FindBy(xpath = "//*[contains(@class,'NumberStyle-')]")
    public WebElement bookingNumberInput;

    @FindBy (xpath = "//*[contains(@class,'flex-a-center')]//*[contains(text(),'For extra security, we need your help with verifying the payment card.')]")
    public WebElement phoneVerificationMessageInput;
    //CNT verification
    @FindBy (xpath = "//*[@id='card verification']")
    public WebElement cntVerificationAmountInput;

    @FindBy (xpath = "//*[@data-qa='sbm-btn']")
    public WebElement cntChargeVerificationSubmitButtonInput;

//For extra security, we need your help with verifying the payment card.
    @FindBy (xpath = "//*[contains(@class,'flex-a-center')]//*[contains(text(),'For extra security, we need your help with verifying the payment card.')]")
    public WebElement cntChargeVerificationSuccessMessageInput;

    @FindBy (xpath = "//*[contains(text(),'Your transaction requires further verification. You will need to take photos of a credit card and')]")
    public WebElement cntDynamicPhotoSuccessMessageInput;

    @FindBy (xpath = "//*[contains(text(),'A confirmation email has been sent to')]")
    public WebElement cntAutoChargeSuccessMessage;
    //    public static final String cntAutoChargeVerification = "//*[contains(text(),'A confirmation email has been sent to')]";



    public WebElement getBookSuccessMessage(){
        return thanksForBookingMessageInput;
    }

    public WebElement getCntAutoChargeMessage() {
        this.waitForElementVisibility(cntAutoChargeSuccessMessage,TIMEOUT_5);
        return cntAutoChargeSuccessMessage;
    }

    public WebElement getCntTakePhotoSuccessMessage() {
        this.waitForElementVisibility(cntDynamicPhotoSuccessMessageInput,TIMEOUT_5);
        return cntDynamicPhotoSuccessMessageInput;
    }

    public WebElement getChargeCntSuccessMessage(){
        this.waitForElementVisibility(cntChargeVerificationSuccessMessageInput,TIMEOUT_5);
        return cntChargeVerificationSuccessMessageInput;
    }

    public WebElement getPhoneVerificationMessage(){
        this.waitForElementVisibility(phoneVerificationMessageInput,TIMEOUT_10);
        return phoneVerificationMessageInput;
    }

    public void submitCntVerification(String amount) {
        this.waitForElementVisibility(cntVerificationAmountInput,TIMEOUT_10);
        cntVerificationAmountInput.sendKeys(amount);
        cntChargeVerificationSubmitButtonInput.click();
    }

    public String getBookingId (){
        return bookingNumberInput.getText();
    }
}
