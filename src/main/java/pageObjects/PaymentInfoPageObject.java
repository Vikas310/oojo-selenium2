package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.BaseClass;

public class PaymentInfoPageObject extends BaseClass {

    public PaymentInfoPageObject (WebDriver driver) {
        super(driver);
    }

    public static final String iFrame = "card-iframe";

    @FindBy(xpath = "//*[@id='cardNumber']")
    public WebElement cardNumberInput;

    @FindBy(xpath = "//input[@id='cardholder']")
    public WebElement nameOnCardInput;

    @FindBy(xpath = "//*[@id='cardExpirationDate']")
    public WebElement cardExpDateInput;

    @FindBy(xpath = "//*[@id='securityCode']")
    public WebElement securityCodeInput;

    @FindBy(xpath = "//*[@id='address']")
    public WebElement addressInput;

    @FindBy(xpath = "//*[@id='city']")
    public WebElement cityInput;

    @FindBy(xpath = "//*[@id='postal_code']")
    public WebElement zipCodeInput;

    @FindBy(xpath = "//*[@id='email']")
    public WebElement paymentEmailInput;

    @FindBy(xpath = "//*[@id='billing-phone']")
    public WebElement billingPhoneInput;

    @FindBy(xpath = "//*[@id='state-select-name']")
    public WebElement stateInput;

    public static final String validationPaymentInfoAttribute = "aria-invalid";


    public PaymentInfoPageObject fillCardNumber(String value){
        this.waitForElementVisibility(cardNumberInput,TIMEOUT_2);
        cardNumberInput.clear();
        cardNumberInput.sendKeys(value);
        return this;
    }

    public PaymentInfoPageObject fillCardName(String value){
        this.waitForElementVisibility(nameOnCardInput,TIMEOUT_2);
        nameOnCardInput.clear();
        nameOnCardInput.sendKeys(value);
        return this;
    }

    public PaymentInfoPageObject fillCardExpDate(String value){
        cardExpDateInput.clear();
        cardExpDateInput.sendKeys(value);
        return this;
    }

    public PaymentInfoPageObject fillSecurityCode(String value){
        securityCodeInput.clear();
        securityCodeInput.sendKeys(value);
        return this;
    }

    public PaymentInfoPageObject fillAddress(String value){
        addressInput.sendKeys(value);
        return this;
    }

    public PaymentInfoPageObject fillCity(String value){
        cityInput.sendKeys(value);
        return this;
    }

    public PaymentInfoPageObject fillPaymentEmail(String value){
        paymentEmailInput.sendKeys(value);
        return this;
    }

    public PaymentInfoPageObject fillZipCode(String value){
        zipCodeInput.sendKeys(value);
        return this;
    }

    public PaymentInfoPageObject fillState(String value){
        stateInput.sendKeys(value);
        return this;
    }

    public PaymentInfoPageObject fillBillingPhone(String value){
        billingPhoneInput.sendKeys(value);
        return this;
    }



}
