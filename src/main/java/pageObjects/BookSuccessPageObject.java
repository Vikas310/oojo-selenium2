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

    @FindBy(xpath = "//strong[contains(text(),'Booking Reference ')]")
    public WebElement bookNumber; //for old book screen

    @FindBy(xpath = "//strong[contains(text(),'Booking Reference ')]")
    public WebElement confirmationNumber;

    @FindBy(xpath = "//*[contains(@class,'t-bold f-s-20')]")
    public WebElement bookConfirmation;

    public String BookingNoText(){
        String ConfNo = confirmationNumber.getText();
        //String ConfNoTextOnly = ConfNo.substring(0,19);
        return ConfNo.substring(0,19);
     }

    public WebElement getConfirmationNumber(){
        try {
            this.waitForElementVisibility(confirmationNumber,TIMEOUT_10);
            return confirmationNumber;
        } catch (NoSuchElementException e) {
            return confirmationNumber;
        }
    }

    public WebElement getBookNumber() {
        try {
            this.waitForElementVisibility(bookConfirmation,TIMEOUT_10);
            return bookConfirmation;
        } catch (NoSuchElementException e)  {
            return bookConfirmation;
        }
    }

    public WebElement getBookNumberSecure() {
        try {
            this.waitForElementVisibility(bookNumber,TIMEOUT_10);
            return bookNumber;
        } catch (NoSuchElementException e)  {
            return bookNumber;
        }
    }


    public WebElement opaqueBookingNumber() {
        try {
            this.waitForElementVisibility(confirmationNumber,TIMEOUT_10);
            return confirmationNumber;
        } catch (NoSuchElementException e)  {
            return confirmationNumber;
        }
    }
    public WebElement nonOpaqueBookingNumber() {
        try {
            this.waitForElementVisibility(bookConfirmation,TIMEOUT_10);
            return bookConfirmation;
        } catch (NoSuchElementException e)  {
            return bookConfirmation;
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
