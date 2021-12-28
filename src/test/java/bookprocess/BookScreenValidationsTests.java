package bookprocess;

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
public class BookScreenValidationsTests extends BaseTest {

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

    @Test
    public void validatePassengerPersonalMandatoryFieldTest() {
        String name = Helper.getRandomName();
        String surName = Helper.getRandomLastName();
        int flight = 0;

        String customDate = Helper.getDateWithSpecificMonthsInFuture(Constants.FOUR_MONTHS,"yyyy-MM-dd");
        String fullUrl = BaseClass.OOJO_URL+Helper.getFlightSearchResultOneWay(
                FlightCodes.DALLAS_CODE,
                FlightCodes.LOS_ANGELOS,
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

        bookPageObject.fillName("333");
        Assert.assertEquals(bookPageObject.firstNameInput.getAttribute(BookPageObject.validationAttribute), "true","first name validation should be present");
        bookPageObject.fillName(name);
        Assert.assertEquals(bookPageObject.firstNameInput.getAttribute(BookPageObject.validationAttribute), "false", "First name validation should be present");

        bookPageObject.fillLastName("333");
        Assert.assertEquals(bookPageObject.lastNameInput.getAttribute(BookPageObject.validationAttribute), "true", "Name is not present");
        bookPageObject.fillLastName(surName);
        Assert.assertEquals(bookPageObject.lastNameInput.getAttribute(BookPageObject.validationAttribute), "false", "Last name validation should not be present");

        bookPageObject.fillPhone("333");
        bookPageObject.fillEmail(name);

        Assert.assertEquals(bookPageObject.phoneNumberInput.getAttribute(BookPageObject.validationAttribute), "true", "Phone validation should not be present");
        bookPageObject.fillPhone(TestData.phoneNumber);

        Assert.assertEquals(bookPageObject.emailInput.getAttribute(BookPageObject.validationAttribute), "true", "Email validation should not be present");
        bookPageObject.fillEmail(TestData.testEmailDynatech);

        bookPageObject.fillPhone(TestData.phoneNumber);

        Assert.assertEquals(bookPageObject.emailInput.getAttribute(BookPageObject.validationAttribute), "false", "Email validation present");
        Assert.assertEquals(bookPageObject.phoneNumberInput.getAttribute(BookPageObject.validationAttribute), "false", "Phone number validation present");
    }

    @Test
    public void validatePaymentCardNumberAndCardNameFieldsTest() {
        int flight = 0;

        String customDate = Helper.getDateWithSpecificMonthsInFuture(Constants.FOUR_MONTHS,"yyyy-MM-dd");
        String fullUrl = BaseClass.OOJO_URL+Helper.getFlightSearchResultOneWay(
                FlightCodes.DALLAS_CODE,
                FlightCodes.LOS_ANGELOS,
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

        baseClass.switchToiFrame(PaymentInfoPageObject.iFrame);
        logWrite.info("Fill card information & other client data");

        paymentInfoPageObject.fillCardNumber("333");
        paymentInfoPageObject.fillCardName("Pjotrr");
        Assert.assertEquals(paymentInfoPageObject.cardNumberInput.getAttribute(PaymentInfoPageObject.validationPaymentInfoAttribute),"true", "Card number validation is not present");

        paymentInfoPageObject.fillCardNumber(TestData.cardNumber);
        Assert.assertEquals(paymentInfoPageObject.cardNumberInput.getAttribute(PaymentInfoPageObject.validationPaymentInfoAttribute),"false", "Card number validation was present");

    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }

}
