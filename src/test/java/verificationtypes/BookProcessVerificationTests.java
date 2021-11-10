package verificationtypes;

import common.Helper;
import constants.Constants;
import constants.FlightCodes;
import constants.TestData;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.*;
import selenium.BaseClass;
import selenium.BaseTest;

@Listeners(common.Listeners.class)
public class BookProcessVerificationTests extends BaseTest {

    BaseClass baseClass;
    HomePageObject homePageObject;
    SearchFlightObject searchFlightObject;
    SearchResultPageObject searchResultPageObject;
    PqTripDetailedViewPageObject pqTripDetailedViewPageObject;
    BookPageObject bookPageObject;
    HeaderPageObject headerPageObject;
    PaymentInfoPageObject paymentInfoPageObject;
    BookSuccessPageObject bookSuccessPageObject;

    @BeforeMethod(alwaysRun = true)
    public void initiate() {
        driver = getDriver();
        baseClass = new BaseClass(driver);
        baseClass.getEnvironment();
        homePageObject = new HomePageObject(driver);
        searchFlightObject = new SearchFlightObject(driver);
        searchResultPageObject = new SearchResultPageObject(driver);
        pqTripDetailedViewPageObject = new PqTripDetailedViewPageObject(driver);
        bookPageObject = new BookPageObject(driver);
        headerPageObject = new HeaderPageObject(driver);
        paymentInfoPageObject = new PaymentInfoPageObject(driver);
        bookSuccessPageObject = new BookSuccessPageObject(driver);
    }

    @Test(retryAnalyzer = common.RetryTest.class)
    public void cntPhoneVerificationSuccessScreen() {

        String name = Helper.getRandomName();
        String surName = Helper.getRandomLastName();
        int flight = 0;

        String customDate = Helper.getDateWithSpecificMonthsInFuture(Constants.SEVEN_MONTHS,"yyyy-MM-dd");
        String fullUrl = BaseClass.OOJO_URL+Helper.getFlightSearchResultOneWay(
                FlightCodes.DALLAS_CODE,
                FlightCodes.LAHORE_CODE, // Lahore
                customDate);

        logWrite.info("Open direct search url " + fullUrl);
        baseClass.openPage(
                fullUrl);

        searchResultPageObject.waitForSearchLoad();
        logWrite.info("Accept cookies if there are any");
        headerPageObject.acceptCookies();
        headerPageObject.cancelMemberOffer();
        String pQFlightPrice = searchResultPageObject.getTripOptionPriceByIndex(flight).getText();
        logWrite.info("Flight price from the search screen: " + pQFlightPrice);

        logWrite.info("Select trip");
        headerPageObject.cancelMemberOffer();

        searchResultPageObject.selectTripOptionPq(flight);
        headerPageObject.cancelMemberOffer();
        logWrite.info("Assert that price from the list is equal with the price in overview screen");
        Assert.assertEquals(pqTripDetailedViewPageObject.getDetailedViewFlightPrice().getText(),pQFlightPrice);
        headerPageObject.cancelMemberOffer();
        logWrite.info("Click on book flight");
        pqTripDetailedViewPageObject.clickBookFlight();
        headerPageObject.waitForLoadingBeeToLoad();
        logWrite.info("Flight quick URL: " + baseClass.getCurrentUrl());

        String totalPrice = bookPageObject.getTotalPrice().getText();
        logWrite.info("Check that price from the search result matches the price on the book screen " + totalPrice);
        Assert.assertEquals(totalPrice,pQFlightPrice, "Price from flight form search result does not match price from book screen");

        logWrite.info("Fill clients info " + name + " "  + surName + " " + TestData.testEmailDynatech + " " + TestData.phoneNumber);
        bookPageObject.fillName(name)
                .fillLastName(surName)
                .fillEmail(TestData.phoneVerificationEmail)
                .selectGender("male")
                .fillPhone(TestData.phoneNumber)
                .clickOnDateOfBirth()
                .fillDateOfBirth("Jan","15","2000")
                .selectNoToPriceDropAssurance();
        baseClass.switchToiFrame(PaymentInfoPageObject.iFrame);

        logWrite.info("Fill card information & other client data");
        paymentInfoPageObject.fillCardNumber(TestData.cardNumber)
                .fillCardName(TestData.nameOnCardJarvis)
                .fillCardExpDate(TestData.cardExpirationDate)
                .fillSecurityCode(TestData.cardSecurityCode)
                .fillAddress(TestData.streetAddress)
                .fillCity(TestData.cityLosAngelos)
                .fillPaymentEmail(TestData.phoneVerificationEmail)
                .fillZipCode(TestData.zipPostalCode)
                .fillState(TestData.stateCalifornia)
                .fillBillingPhone(TestData.phoneNumber);

        baseClass.switchToParentFrame();
        logWrite.info("Click on agree terms & conditions");
        //TODO: For now remove book
        bookPageObject.clickAgreeOnTerms()
                .clickBook();

        logWrite.info("Cancel the protection");
        bookPageObject.cancelProtection();
        headerPageObject.waitForLoadingBeeToLoad();
        bookPageObject.cancelProtection();
        headerPageObject.waitForLoadingBeeToLoad();
        headerPageObject.waitForVerificationSpinnerToStop();
        logWrite.info("Check if phone verification screen is present");
        Assert.assertTrue(bookSuccessPageObject.getPhoneVerificationMessage().isDisplayed(), "Phone verification screen was not present");

    }

    @Test(retryAnalyzer = common.RetryTest.class)
    public void cntChargeVerificationSuccessScreen() {

        String name = Helper.getRandomName();
        String surName = Helper.getRandomLastName();
        int flight = 0;
        String cntCharge = "0.01";

        String customDate = Helper.getDateWithSpecificMonthsInFuture(Constants.SEVEN_MONTHS,"yyyy-MM-dd");
        String fullUrl = BaseClass.OOJO_URL+Helper.getFlightSearchResultOneWay(
                FlightCodes.LOS_ANGELOS,
                FlightCodes.MCCARRAN_INIT_CODE, // Lahore
                customDate);

        logWrite.info("Open direct search url " + fullUrl);
        baseClass.openPage(
                fullUrl);

        searchResultPageObject.waitForSearchLoad();
        logWrite.info("Accept cookies if there are any");
        headerPageObject.acceptCookies();
        headerPageObject.cancelMemberOffer();
        String pQFlightPrice = searchResultPageObject.getTripOptionPriceByIndex(flight).getText();
        logWrite.info("Flight price from the search screen: " + pQFlightPrice);

        logWrite.info("Select trip");
        headerPageObject.cancelMemberOffer();

        searchResultPageObject.selectTripOptionPq(flight);
        headerPageObject.cancelMemberOffer();
        logWrite.info("Assert that price from the list is equal with the price in overview screen");
        Assert.assertEquals(pqTripDetailedViewPageObject.getDetailedViewFlightPrice().getText(),pQFlightPrice);
        headerPageObject.cancelMemberOffer();
        logWrite.info("Click on book flight");
        pqTripDetailedViewPageObject.clickBookFlight();
        headerPageObject.waitForLoadingBeeToLoad();
        logWrite.info("Flight quick URL: " + baseClass.getCurrentUrl());

        String totalPrice = bookPageObject.getTotalPrice().getText();
        logWrite.info("Check that price from the search result matches the price on the book screen " + totalPrice);
        Assert.assertEquals(totalPrice,pQFlightPrice, "Price from flight form search result does not match price from book screen");

        logWrite.info("Fill clients info " + name + " "  + surName + " " + TestData.testEmailDynatech + " " + TestData.phoneNumber);
        bookPageObject.fillName(name)
                .fillLastName(surName)
                .fillEmail(TestData.cntChargeVerificationEmail)
                .selectGender("male")
                .fillPhone(TestData.phoneNumber)
                .clickOnDateOfBirth()
                .fillDateOfBirth("Jan","15","2000")
                .selectNoToPriceDropAssurance();
        baseClass.switchToiFrame(PaymentInfoPageObject.iFrame);

        logWrite.info("Fill card information & other client data");
        paymentInfoPageObject.fillCardNumber(TestData.cardNumber)
                .fillCardName(TestData.nameOnCardJarvis)
                .fillCardExpDate(TestData.cardExpirationDate)
                .fillSecurityCode(TestData.cardSecurityCode)
                .fillAddress(TestData.streetAddress)
                .fillCity(TestData.cityLosAngelos)
                .fillPaymentEmail(TestData.cntChargeVerificationEmail)
                .fillZipCode(TestData.zipPostalCode)
                .fillState(TestData.stateCalifornia)
                .fillBillingPhone(TestData.phoneNumber);

        baseClass.switchToParentFrame();
        logWrite.info("Click on agree terms & conditions");
        //TODO: For now remove book
        bookPageObject.clickAgreeOnTerms()
                .clickBook();

        logWrite.info("Cancel the protection");
        bookPageObject.cancelProtection();
        headerPageObject.waitForLoadingBeeToLoad();
        bookPageObject.cancelProtection();
        headerPageObject.waitForLoadingBeeToLoad();
        headerPageObject.waitForVerificationSpinnerToStop();
        logWrite.info("Submit cnt charge for 0.01");
        bookSuccessPageObject.submitCntVerification(cntCharge);
        Assert.assertTrue(bookSuccessPageObject.getChargeCntSuccessMessage().isDisplayed(), "Submit cnt charge screen was not present");
    }

    @Test(retryAnalyzer = common.RetryTest.class)
    public void cntTakePhotoVerificationSuccessScreen() {

        String name = Helper.getRandomName();
        String surName = Helper.getRandomLastName();
        int flight = 0;

        String customDate = Helper.getDateWithSpecificMonthsInFuture(Constants.SEVEN_MONTHS,"yyyy-MM-dd");
        String fullUrl = BaseClass.OOJO_URL+Helper.getFlightSearchResultOneWay(
                FlightCodes.CANSASCITY,
                FlightCodes.LOS_ANGELOS, // Lahore
                customDate);

        logWrite.info("Open direct search url " + fullUrl);
        baseClass.openPage(
                fullUrl);

        searchResultPageObject.waitForSearchLoad();
        logWrite.info("Accept cookies if there are any");
        headerPageObject.acceptCookies();
        headerPageObject.cancelMemberOffer();
        String pQFlightPrice = searchResultPageObject.getTripOptionPriceByIndex(flight).getText();
        logWrite.info("Flight price from the search screen: " + pQFlightPrice);

        logWrite.info("Select trip");
        headerPageObject.cancelMemberOffer();

        searchResultPageObject.selectTripOptionPq(flight);
        headerPageObject.cancelMemberOffer();
        logWrite.info("Assert that price from the list is equal with the price in overview screen");
        Assert.assertEquals(pqTripDetailedViewPageObject.getDetailedViewFlightPrice().getText(),pQFlightPrice);
        headerPageObject.cancelMemberOffer();
        logWrite.info("Click on book flight");
        pqTripDetailedViewPageObject.clickBookFlight();
        headerPageObject.waitForLoadingBeeToLoad();
        logWrite.info("Flight quick URL: " + baseClass.getCurrentUrl());

        String totalPrice = bookPageObject.getTotalPrice().getText();
        logWrite.info("Check that price from the search result matches the price on the book screen " + totalPrice);
        Assert.assertEquals(totalPrice,pQFlightPrice, "Price from flight form search result does not match price from book screen");

        logWrite.info("Fill clients info " + name + " "  + surName + " " + TestData.testEmailDynatech + " " + TestData.phoneNumber);
        bookPageObject.fillName(name)
                .fillLastName(surName)
                .fillEmail(TestData.cntTakePhotoVerificationEmail)
                .selectGender("male")
                .fillPhone(TestData.phoneNumber)
                .clickOnDateOfBirth()
                .fillDateOfBirth("Jan","15","2000")
                .selectNoToPriceDropAssurance();
        baseClass.switchToiFrame(PaymentInfoPageObject.iFrame);

        logWrite.info("Fill card information & other client data");
        paymentInfoPageObject.fillCardNumber(TestData.cardNumber)
                .fillCardName(TestData.nameOnCardJarvis)
                .fillCardExpDate(TestData.cardExpirationDate)
                .fillSecurityCode(TestData.cardSecurityCode)
                .fillAddress(TestData.streetAddress)
                .fillCity(TestData.cityLosAngelos)
                .fillPaymentEmail(TestData.cntTakePhotoVerificationEmail)
                .fillZipCode(TestData.zipPostalCode)
                .fillState(TestData.stateCalifornia)
                .fillBillingPhone(TestData.phoneNumber);

        baseClass.switchToParentFrame();
        logWrite.info("Click on agree terms & conditions");
        //TODO: For now remove book
        bookPageObject.clickAgreeOnTerms()
                .clickBook();

        logWrite.info("Cancel the protection");
        bookPageObject.cancelProtection();
        headerPageObject.waitForLoadingBeeToLoad();
        bookPageObject.cancelProtection();
        headerPageObject.waitForLoadingBeeToLoad();
        headerPageObject.waitForVerificationSpinnerToStop();
        logWrite.info("Check if take photo screen is present");
        Assert.assertTrue(bookSuccessPageObject.getCntTakePhotoSuccessMessage().isDisplayed(), "Take photo screen was not present");
    }

    @Test(retryAnalyzer = common.RetryTest.class)
    public void cntAutoChargeVerificationSuccessScreen() {

        String name = Helper.getRandomName();
        String surName = Helper.getRandomLastName();
        int flight = 0;

        String customDate = Helper.getDateWithSpecificMonthsInFuture(Constants.SEVEN_MONTHS,"yyyy-MM-dd");
        String fullUrl = BaseClass.OOJO_URL+Helper.getFlightSearchResultOneWay(
                FlightCodes.MIAMI,
                FlightCodes.NEW_YORK_CODE, // Lahore
                customDate);

        logWrite.info("Open direct search url " + fullUrl);
        baseClass.openPage(
                fullUrl);

        searchResultPageObject.waitForSearchLoad();
        logWrite.info("Accept cookies if there are any");
        headerPageObject.acceptCookies();
        headerPageObject.cancelMemberOffer();
        String pQFlightPrice = searchResultPageObject.getTripOptionPriceByIndex(flight).getText();
        logWrite.info("Flight price from the search screen: " + pQFlightPrice);

        logWrite.info("Select trip");
        headerPageObject.cancelMemberOffer();

        searchResultPageObject.selectTripOptionPq(flight);
        headerPageObject.cancelMemberOffer();
        logWrite.info("Assert that price from the list is equal with the price in overview screen");
        Assert.assertEquals(pqTripDetailedViewPageObject.getDetailedViewFlightPrice().getText(),pQFlightPrice);
        headerPageObject.cancelMemberOffer();
        logWrite.info("Click on book flight");
        pqTripDetailedViewPageObject.clickBookFlight();
        headerPageObject.waitForLoadingBeeToLoad();
        logWrite.info("Flight quick URL: " + baseClass.getCurrentUrl());

        String totalPrice = bookPageObject.getTotalPrice().getText();
        logWrite.info("Check that price from the search result matches the price on the book screen " + totalPrice);
        Assert.assertEquals(totalPrice,pQFlightPrice, "Price from flight form search result does not match price from book screen");

        logWrite.info("Fill clients info " + name + " "  + surName + " " + TestData.testEmailDynatech + " " + TestData.phoneNumber);
        bookPageObject.fillName(name)
                .fillLastName(surName)
                .fillEmail(TestData.cntAutoChargeVerification)
                .selectGender("male")
                .fillPhone(TestData.phoneNumber)
                .clickOnDateOfBirth()
                .fillDateOfBirth("Jan","15","2000")
                .selectNoToPriceDropAssurance();
        baseClass.switchToiFrame(PaymentInfoPageObject.iFrame);

        logWrite.info("Fill card information & other client data");
        paymentInfoPageObject.fillCardNumber(TestData.cardNumber)
                .fillCardName(TestData.nameOnCardJarvis)
                .fillCardExpDate(TestData.cardExpirationDate)
                .fillSecurityCode(TestData.cardSecurityCode)
                .fillAddress(TestData.streetAddress)
                .fillCity(TestData.cityLosAngelos)
                .fillPaymentEmail(TestData.cntAutoChargeVerification)
                .fillZipCode(TestData.zipPostalCode)
                .fillState(TestData.stateCalifornia)
                .fillBillingPhone(TestData.phoneNumber);

        baseClass.switchToParentFrame();
        logWrite.info("Click on agree terms & conditions");
        //TODO: For now remove book
        bookPageObject.clickAgreeOnTerms()
                .clickBook();

        logWrite.info("Cancel the protection");
        bookPageObject.cancelProtection();
        headerPageObject.waitForLoadingBeeToLoad();
        bookPageObject.cancelProtection();
        headerPageObject.waitForLoadingBeeToLoad();
        headerPageObject.waitForVerificationSpinnerToStop();
        logWrite.info("Check if auto charge screen is present");
        Assert.assertTrue(bookSuccessPageObject.getCntAutoChargeMessage().isDisplayed(), "Auto charge screen was not shown");
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }

}
