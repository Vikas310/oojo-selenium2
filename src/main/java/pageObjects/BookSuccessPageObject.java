package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.BaseClass;

public class BookSuccessPageObject extends BaseClass {

    public BookSuccessPageObject(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[contains(@class,'flex-a-center')]//*[contains(text(),'For extra security, we need your help with verifying the payment card.')]")
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

    @FindBy (xpath = "//*[contains(@class,'flex-a-center')]//*[contains(text(),'For extra security, we need your help with verifying the payment card.')]")
    public WebElement cntChargeVerificationSuccessMessageInput;
                                                                            //A confirmation email has been sent to
    @FindBy (xpath = "//*[contains(@class,'flex-a-center')]//*[contains(text(),'We’ve sent an email with the link and instructions to')]")
    public WebElement cntDynamicPhotoSuccessMessageInput;

    @FindBy (xpath = "//*[contains(text(),'A confirmation email has been sent to')]")
    public WebElement cntAutoChargeSuccessMessage;

    @FindBy(xpath = "//*[contains(@class,'style__TitleStyle')]//*[2]")
    public WebElement bookNumber; //for old book screen

    @FindBy(xpath = "//*[@data-qa='confirmNumber']")
    public WebElement confirmationNumber;

    public WebElement getConfirmationNumber(){
        try {
            this.waitForElementVisibility(confirmationNumber,TIMEOUT_5);
            return confirmationNumber;
        } catch (NoSuchElementException e) {
            return confirmationNumber;
        }
    }

    public WebElement getBookNumber() {
        try {
            this.waitForElementVisibility(bookNumber,TIMEOUT_20);
            return bookNumber;
        } catch (NoSuchElementException e)  {
            return bookNumber;
        }
    }

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
        this.waitForElementVisibility(phoneVerificationMessageInput,TIMEOUT_20);
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
