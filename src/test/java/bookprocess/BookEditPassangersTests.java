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

import java.util.Arrays;
import java.util.stream.DoubleStream;

import static constants.TestData.nameone;


//@Listeners(common.Listeners.class)

public class BookEditPassangersTests extends BaseTest {

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
    public void addAnotherAdultAndCheckTotalFlightPrice() {

        String name = Helper.getRandomName();
        String surName = Helper.getRandomLastName();
        int flight = 0;

        String customDate = Helper.getDateWithSpecificMonthsInFuture(Constants.FOUR_MONTHS,"yyyy-MM-dd");
        String fullUrl = BaseClass.OOJO_URL+Helper.getFlightSearchResultOneWay(
                FlightCodes.NEW_YORK_CODE,
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

        logWrite.info("Search stats: " + searchResultPageObject.getSearchStats());
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
        logWrite.info("Now click on the edit passanger link");
        bookPageObject.clickEditPassanger()
        .addPassenger(Constants.ADULT)
        .passengerSaveButton();
        logWrite.info("Now wait for another pax to add");
        headerPageObject.waitForLoadingBeeToLoad();
        logWrite.info("Check for added passenger text");
        //TODO: add message should be present?
        logWrite.info("Collect all pax prices in array & very full price");

        String pax1Price = bookPageObject.getSinglePaxPrice(1);
        String pax2Price = bookPageObject.getSinglePaxPrice(2);
        //TODO: we have to create a method in which we sum the float total amount. Lets use getPaxFullPrice() for it
        float convert = Float.sum(Float.parseFloat(pax1Price),Float.parseFloat(pax2Price));

        logWrite.info("Verify the added pax text by Comparing " + convert);
        Assert.assertEquals(Float.toString(convert),bookPageObject.getPaxFinalPrice().toString());


         logWrite.info("Fill clients info " + name + " "  + surName + " " + TestData.testEmailDynatech + " " + TestData.phoneNumber);
        bookPageObject.fillName(name)
                .fillLastName(surName)
                .selectGender("male1",1)
                .clickOnDateOfBirth()
                .fillDateOfBirth("Jan","15","2000");

        logWrite.info("Fill clients info " + name + " "  + surName + " " + TestData.testEmailDynatech + " " + TestData.phoneNumber);
        bookPageObject.fillName1(name)
                .fillLastName1(surName)
                .fillEmail(TestData.testEmailDynatech)
                .selectGender("male1",2)
                .fillPhone(TestData.phoneNumber)
                .clickOnDateOfBirth1()
                .fillDateOfBirth1("Feb","25","2001");

                bookPageObject.selectNoToPriceDropAssurance();

        baseClass.switchToiFrame(PaymentInfoPageObject.iFrame);

        logWrite.info("Fill card information & other client data");
        paymentInfoPageObject.fillCardNumber(TestData.cardNumber)
                .fillCardName(TestData.nameOnCardJarvis)
                .fillCardExpDate(TestData.cardExpirationDate)
                .fillSecurityCode(TestData.cardSecurityCode)
                .fillAddress(TestData.streetAddress)
                .fillCity(TestData.cityLosAngelos)
                .fillPaymentEmail(TestData.testEmailDynatech)
                .fillZipCode(TestData.zipPostalCode)
                .fillState(TestData.stateCalifornia)
                .fillBillingPhone(TestData.phoneNumber);

        baseClass.switchToParentFrame();
        logWrite.info("Click on agree terms & conditions");
    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }
}
