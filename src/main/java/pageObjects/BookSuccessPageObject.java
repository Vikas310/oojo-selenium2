package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.BaseClass;

public class BookSuccessPageObject extends BaseClass {

    public BookSuccessPageObject(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[contains(text(),'We are almost there…')]")
    public WebElement thanksForBookingMessageInput;

    @FindBy(xpath = "//*[contains(@class,'NumberStyle-')]")
    public WebElement bookingNumberInput;

    @FindBy (xpath = "//*[contains(text(),'Please, verify your transaction.')]")
    public WebElement phoneVerificationMessageInput;
    //CNT verification
    @FindBy (xpath = "//*[@id='card verification']")
    public WebElement cntVerificationAmountInput;

    @FindBy (xpath = "//*[@data-qa='sbm-btn']")
    public WebElement cntVerificationSubmitButtonInput;

    @FindBy (xpath = "//*[contains(text(),'The purpose of this verification is to have the cardholder identified by the card issuer so we can proceed forward with the transaction.')]")
    public WebElement cntVerificationSuccessMessageInput;

    public WebElement getBookSuccessMessage(){
        return thanksForBookingMessageInput;
    }

    public WebElement getCntSuccessMessage(){
        this.waitForElementVisibility(cntVerificationSuccessMessageInput,TIMEOUT_5);
        return cntVerificationSuccessMessageInput;
    }

    public WebElement getPhoneVerificationMessage(){
        this.waitForElementVisibility(phoneVerificationMessageInput,TIMEOUT_10);
        return phoneVerificationMessageInput;
    }

    public void submitCntVerification(String amount) {
        this.waitForElementVisibility(cntVerificationAmountInput,TIMEOUT_10);
        cntVerificationAmountInput.sendKeys(amount);
        cntVerificationSubmitButtonInput.click();
    }

    public String getBookingId (){
        return bookingNumberInput.getText();
    }
}
