package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.BaseClass;

public class BookPageObject extends BaseClass {

    public BookPageObject(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//div[@class='stretch']")
    public WebElement priceDropAssuranceMessageBoxInput;

    @FindBy(xpath = "//*[contains(text(),'Check more flights')]")
    public WebElement checkMoreFlightsButtonInput;

    @FindBy (xpath = "//input[@id='firstName_0']")
    public WebElement firstNameInput;

    @FindBy (xpath = "//input[@id='middleName_0']")
    public WebElement middleNameInput;

    @FindBy (xpath = "//input[@id='lastName_0']")
    public WebElement lastNameInput;

    @FindBy (xpath = "//label[contains(text(),'Male')]")
    public WebElement labelMaleInput;

    @FindBy (xpath = "//label[contains(text(),'Female')]")
    public WebElement labelFemaleInput;

    @FindBy (xpath = "//*[@name='phoneNumber']")
    public WebElement phoneNumberInput;

    @FindBy (xpath = "//*[@name='email']")
    public WebElement emailInput;

    @FindBy (xpath = "//*[contains(text(),'Confirm & Book')]")
    public WebElement bookButtonInput;

    @FindBy (xpath = "//*[contains(@class,'agreeCheckbox')]")
    public WebElement agreeOnTerms;

    @FindBy(xpath = "//*[contains(@class,'qa-dob-cover')]")
    public WebElement dateOfBirthInput;

    @FindBy(xpath = "//div[@class='style__ContentWrapperStyle-sc-wetkh4-0 modal___StyledContentWrapperStyle-sc-ivh8e9-0 hCddKl pos-rlt bg-white']")
    public WebElement dateOfBirthPopoverInput;

    @FindBy (xpath = "//div[@class='stretch']//label[contains(text(),'No,')]")
    public WebElement priceDropAssuranceNoInput;

    @FindBy (xpath = "//*[@data-qa='_tcsNoRadio']")
    public WebElement cancelProtectionButtonInput;

    @FindBy (xpath = "//*[@data-qa='_tcsContinueBtn']")
    public WebElement continueButtonInput;

    @FindBy (xpath = "//*[@data-qa='_totPrice']")
    public WebElement getTotalPriceBookPageInput;

    @FindBy (xpath = "//*[@data-qa='_detailsBtn']")
    public WebElement viewDetailsButtonInput;

    @FindBy (xpath = "//*[contains(text(),'Continue')]")
    public WebElement takeSegmentFailedContinueButtonInput;

    public static final String validationAttribute = "aria-invalid";

    By takeSegmentError = By.xpath("//button[contains(text(),'Continue')]");

    public String getValidationAttibuteError() {
        return "aria-invalid";
    }


        public void cancelProtection() {
        try {
            if (this.waitForElementVisibility(cancelProtectionButtonInput,TIMEOUT_20)) {
                cancelProtectionButtonInput.click();
                continueButtonInput.click();
            }
        } catch (NoSuchElementException e){
        }

    }

    public SearchResultPageObject clickContinueTakeSegmentFailed() {
        this.waitForElementToBeClickable(takeSegmentFailedContinueButtonInput,TIMEOUT_10);
        takeSegmentFailedContinueButtonInput.click();
        return new SearchResultPageObject(driver);
    }

    public Boolean takeSegmentFailed() {
        try {
                this.waitForElementVisibility(getTakeSegmentError(),TIMEOUT_30);
                return getTakeSegmentError().isDisplayed();
        } catch (NoSuchElementException e){
                return false;
        }

    }

    public void expandFlightDetails() {
        viewDetailsButtonInput.click();
    }

    public WebElement getTotalPrice(){
        this.waitForElementVisibility(getTotalPriceBookPageInput,TIMEOUT_10);
        return getTotalPriceBookPageInput;
    }

    public WebElement getTakeSegmentError () {
        return driver.findElement(takeSegmentError);
    }

    public void clickIfFlightWhichWasExpired() {
        if(this.waitForElementVisibility(getTakeSegmentError(),TIMEOUT_20)){
            getTakeSegmentError().click();
        }
    }

    public BookPageObject selectNoToPriceDropAssurance(){
        priceDropAssuranceNoInput.click();
        return this;
    }


    private WebElement getPersonGender(int index) {
        return driver.findElement(By.xpath("//*[contains(@class,'gender')]//label["+index+"]"));
    }

    public BookPageObject selectGender(String gender) {
        switch (gender) {
            case "male":
                this.getPersonGender(1).click();
                break;
            case "female":
                this.getPersonGender(2).click();
                break;
        }
        return this;
    }

    private BookPageObject clickMonth(String month){
        driver.findElement(By.xpath("//label[contains(text(),'"+month+"')]")).click();
        return this;
    }

    private BookPageObject clickDay(String day){
        driver.findElement(By.xpath("//label[contains(text(),'"+day+"')]")).click();
        return this;
    }

    private BookPageObject clickYear(String year){
        driver.findElement(By.xpath("//label[contains(text(),'"+year+"')]")).click();
        return this;
    }

    public BookPageObject clickOnDateOfBirth () {
        dateOfBirthInput.click();
        this.waitForElementVisibility(dateOfBirthPopoverInput,TIMEOUT_5);
        return this;
    }

    public BookPageObject fillDateOfBirth(String month, String day, String year){
                clickMonth(month)
                .clickDay(day)
                .clickYear(year);
        driver.findElements(By.xpath("//*[contains(text(),'Save')]"));
        return this;
    }

    public void clickOnCheckMoreFlights(){
        this.waitForElementVisibility(checkMoreFlightsButtonInput,TIMEOUT_10);
        checkMoreFlightsButtonInput.click();
    }

    public BookPageObject fillName(String value){
        this.waitForElementVisibility(firstNameInput,TIMEOUT_10);
        firstNameInput.sendKeys(value);
        return this;
    }

    public BookPageObject fillLastName(String value){
        lastNameInput.clear();
        lastNameInput.sendKeys(value);
        return this;
    }

    public BookPageObject fillEmail(String value){
        emailInput.clear();
        emailInput.sendKeys(value);
        return this;
    }

    public BookPageObject fillPhone(String value){
        phoneNumberInput.clear();
        phoneNumberInput.sendKeys(value);
        return this;
    }

    public void clickBook() {
        logWrite.info("Click on book ");
        bookButtonInput.click();
        this.waitForElementInvisibility(bookButtonInput,TIMEOUT_5);
    }

    public BookPageObject clickAgreeOnTerms (){
        logWrite.info("Click on agree terms & conditions");
        agreeOnTerms.click();
        return this;
    }

//    public WebElement getBookPageAttribute(WebElement element, String attribute){
//        return element.getAttribute(attribute);
//    }

//    private String getAttribute(String attribute) {
//    }


}
