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


    @FindBy (xpath = "//input[@id='firstName_1']")
    public WebElement firstName1Input;

    @FindBy (xpath = "//input[@id='middleName_1']")
    public WebElement middleName2Input;

    @FindBy (xpath = "//input[@id='lastName_1']")
    public WebElement lastName2Input;


    @FindBy (xpath = "//label[contains(text(),'Male')]")
    public WebElement labelMale2Input;

    @FindBy (xpath = "//label[contains(text(),'Female')]")
    public WebElement labelFemale2Input;

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

    @FindBy(xpath = "//body/div[@id='__next']/div[2]/div[1]/div[1]/div[1]/div[4]/div[1]/div[2]/div[3]/div[2]/div[4]/div[1]/div[1]/div[2]")
    public WebElement dateOfBirthInput1;

    @FindBy(xpath = "//div[@class='style__ContentWrapperStyle-sc-wetkh4-0 modal___StyledContentWrapperStyle-sc-ivh8e9-0 hCddKl pos-rlt bg-white']")
    public WebElement dateOfBirthPopoverInput;

    //body/div[2]/div[3]/div[1]/div[1]

    @FindBy(xpath = "//body/div[2]/div[3]")
    public WebElement dateOfBirthPopoverInput1;

    //@FindBy (xpath = "//div[@class='stretch']//label[contains(text(),'No,')]")
    @FindBy (xpath = "//body/div[@id='__next']/div[2]/div[1]/div[1]/div[1]/div[5]/div[2]/div[4]/div[2]/label[1]")
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

    @FindBy (xpath = "//a[contains(text(),'Edit passengers')]")
    public WebElement editPassangerslink;

    @FindBy (xpath = "//button[contains(text(),'Save')]")
    public WebElement editPassangersSaveButton;

    @FindBy (xpath = "//button[contains(text(),'Cancel')]")
    public WebElement editPassangersCancelButton;

    @FindBy (xpath = "//button[contains(@class,'qa-ptcPlus1')]")
    public WebElement editPassangersAdultAdd;

    @FindBy(xpath="(//div[@class='style__TitleStyle-sc-1cvrw2b-2 liwfAG f-s-12'])[3]")
    public WebElement editAdultPassangerText;

    @FindBy(xpath = "(//div[@class='style__TitleStyle-sc-1cvrw2b-2 liwfAG f-s-12'])[2]")
    public WebElement pax1Price;

    @FindBy(xpath = "(//div[@class='style__TitleStyle-sc-1cvrw2b-2 liwfAG f-s-12'])[4]")
    public WebElement pax2Price;

    @FindBy(xpath ="//*[@data-qa='_totPrice']")
    public WebElement finalPaxPrice;

    @FindBy(xpath ="//input[@id='firstName_1']")
    public WebElement secondPaxFirstname;

    @FindBy(xpath ="//input[@id='lastName_1']")
    public WebElement secondPaxLastname;

    private WebElement getFirstNameInputByIndex(int index) {
//        int value = index-1;
        return driver.findElement(By.xpath("//input[@id='firstName_"+index+"']"));
    }

    private WebElement getLastNameInputByIndex(int index) {
//        int value = index-1;
        return driver.findElement(By.xpath("//input[@id='lastName_"+index+"']"));
    }

    public WebElement getAddPassengerButtonInput(int value){
        return driver.findElement(By.xpath("//button[contains(@class,'qa-ptcPlus"+value+"')]"));
    }

    public WebElement getRemovePassengerButtonInput(int value){
        return driver.findElement(By.xpath("//button[contains(@class,'qa-ptcMinus"+value+"')]"));
    }

    public WebElement getBirthOfDateByIndex(int index) {
        return driver.findElements(By.xpath("//*[contains(@class,'qa-dob-cover')]")).get(index);
    }

    public String getSinglePaxPrice(int pax) {
        WebElement price = driver.findElement(By.xpath("//*[@data-qa='_totPaxPrice"+pax+"']"));
        String paxPriceText = price.getText();
        return paxPriceText.substring(1);
    }

    public void getPaxFullPrice(String[] pax) {

    }

    public static final String validationAttribute = "aria-invalid";

    By takeSegmentError = By.xpath("//button[contains(text(),'Continue')]");

    public BookPageObject addPassenger(String passenger){
            switch (passenger) {
                case "adult":
                    this.getAddPassengerButtonInput(1).click();
                    break;
                case "child":
                    this.getAddPassengerButtonInput(2).click();
                    break;
                case "infant":
                    this.getAddPassengerButtonInput(3).click();
            }
            return this;
    }

    public BookPageObject removePassenger(String passenger){
        switch (passenger) {
            case "adult":
                this.getRemovePassengerButtonInput(1).click();
                break;
            case "child":
                this.getRemovePassengerButtonInput(2).click();
                break;
            case "infant":
                this.getRemovePassengerButtonInput(3).click();
        }
        return this;
    }

    // To fetch final price from System
    public Float getPaxFinalPrice() {
        String paxFinalPrice = finalPaxPrice.getText();
        String removeSymbol = paxFinalPrice.substring(1);
        return Float.parseFloat(removeSymbol);
    }

    // To click on Adult + Button to add more pax
    public BookPageObject passengerAdultAdd(){
        editPassangersAdultAdd.click();
        return this;
    }


    public BookPageObject passengerSaveButton(){
        editPassangersSaveButton.click();
        return this;
    }

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
        this.waitForElementVisibility(getTotalPriceBookPageInput,TIMEOUT_20);
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
        this.waitForElementToBeClickable(priceDropAssuranceNoInput,TIMEOUT_10);
        priceDropAssuranceNoInput.click();
        return this;
    }


    private WebElement getPersonGender(int index) {
        return driver.findElement(By.xpath("//*[contains(@class,'gender')]//label["+index+"]"));
    }

    private WebElement getAllGenderElements(int index,int value){
        return driver.findElements(By.xpath("//*[contains(@class,'gender')]//label["+index+"]")).get(value);
    }

    public BookPageObject selectGender(String gender,int index) {
        int value = index-1;
        switch (gender) {
            case "male":
                this.waitForElementVisibility(this.getAllGenderElements(1,value),TIMEOUT_5);
                this.getAllGenderElements(1,value).click();
                break;
            case "female":
                this.waitForElementVisibility(this.getAllGenderElements(2,value),TIMEOUT_5);
                this.getAllGenderElements(2,value).click();
                break;
        }
        return this;
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

    public BookPageObject clickOnDateOfBirth (int index) {
        int position = index-1;
        getBirthOfDateByIndex(position).click();
        this.waitForElementVisibility(dateOfBirthPopoverInput,TIMEOUT_5);
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
        //driver.findElements(By.xpath("//*[contains(text(),'Save')]"));
        return this;
    }

    public BookPageObject fillDateOfBirth1(String month, String day, String year){
        clickMonth(month)
                .clickDay(day)
                .clickYear(year);
        driver.findElements(By.xpath("//button[contains(text(),'Save')]"));
        return this;
    }

    public void clickOnCheckMoreFlights(){
        this.waitForElementVisibility(checkMoreFlightsButtonInput,TIMEOUT_10);
        checkMoreFlightsButtonInput.click();
    }

    public BookPageObject fillName(String value,int index){
        int position = index-1;
        this.waitForElementVisibility(getFirstNameInputByIndex(position),TIMEOUT_10);
        System.out.println(getFirstNameInputByIndex(position).toString() + " HEEE");
        getFirstNameInputByIndex(position).sendKeys(value);
        return this;
    }

    public BookPageObject fillLastName(String value,int index){
        int position = index-1;
        this.waitForElementVisibility(getLastNameInputByIndex(position),TIMEOUT_10);
        getLastNameInputByIndex(position).sendKeys(value);
        return this;
    }

    public BookPageObject fillName(String value){
        this.waitForElementVisibility(firstName1Input,TIMEOUT_10);
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

    public BookPageObject clickEditPassanger(){
        editPassangerslink.click();
        return this;
    }

//    public WebElement getBookPageAttribute(WebElement element, String attribute){
//        return element.getAttribute(attribute);
//    }

//    private String getAttribute(String attribute) {
//    }


}
