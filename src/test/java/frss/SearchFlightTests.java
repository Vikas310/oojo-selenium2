package frss;

import common.Helper;
import constants.Constants;
import constants.FlightCodes;
import constants.TestData;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.*;
import selenium.BaseClass;
import selenium.BaseTest;

@Listeners(common.Listeners.class)
public class SearchFlightTests extends BaseTest {

    BaseClass baseClass;
    HomePageObject homePageObject;
    SearchFlightObject searchFlightObject;
    SearchResultPageObject searchResultPageObject;
    PqTripDetailedViewPageObject pqTripDetailedViewPageObject;
    BookPageObject bookPageObject;
    HeaderPageObject headerPageObject;
    PaymentInfoPageObject paymentInfoPageObject;

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
    }

    @Test
    public void searchFlightAndOpenItAgainFromDallasToManchester() {

        int flight = 0;

        String customDate = Helper.getDateWithSpecificMonthsInFuture(Constants.FOUR_MONTHS,"yyyy-MM-dd");
        String fullUrl = BaseClass.OOJO_URL+Helper.getFlightSearchResultOneWay(
                FlightCodes.DALLAS_CODE,
                FlightCodes.MANCHESTER_CODE,
                customDate);

        logWrite.info("Open direct search url " + fullUrl);
        baseClass.openPage(
                fullUrl);

        searchResultPageObject.waitForSearchLoad();
        logWrite.info("Accept cookies if there are any");
        headerPageObject.acceptCookies();
        headerPageObject.cancelMemberOffer();

        String pQFlightPrice = searchResultPageObject.getTripOptionPriceByIndex(flight).getText();
        logWrite.info("Select trip");
        searchResultPageObject.selectTripOptionPq(flight);

        logWrite.info("Assert that price from the list is equal with the price in overview screen");
        String price = pqTripDetailedViewPageObject.getDetailedViewFlightPrice().getText();
        Assert.assertEquals(price,pQFlightPrice);

        logWrite.info("Click on book flight");
        headerPageObject.cancelMemberOffer();
        pqTripDetailedViewPageObject.clickBookFlight();
        headerPageObject.waitForLoadingBeeToLoad();
        //TODO: need an assertion to check the price for the flight, no stable locator

        String cachedUrl = baseClass.getCurrentUrl();

        logWrite.info("Open direct search url " + cachedUrl);
        baseClass.openPage(cachedUrl);
        headerPageObject.waitForLoadingBeeToLoad();

        //TODO: need an assertion to check the price for the flight, no stable locator
    }

    @Test
    public void searchFlightAndOpenSavedUrl() {

        int flight = 0;

        String from = Helper.getRandomFlight();
        String to = Helper.getRandomFlight();
        String customDate = Helper.getDateWithSpecificMonthsInFuture(Constants.FOUR_MONTHS,"yyyy-MM-dd");

        if(from == to){
            searchFlightAndOpenSavedUrl();
            }
        String fullUrl = BaseClass.OOJO_URL+Helper.getFlightSearchResultOneWay(from, to, customDate);
        logWrite.info("Open direct search url " + fullUrl);
        baseClass.openPage(
                fullUrl);

        searchResultPageObject.waitForSearchLoad();
        logWrite.info("Accept cookies if there are any");
        headerPageObject.acceptCookies();
        headerPageObject.cancelMemberOffer();
        String pQFlightPrice = searchResultPageObject.getTripOptionPriceByIndex(flight).getText();
        logWrite.info("Select trip");
        searchResultPageObject.selectTripOptionPq(flight);

        logWrite.info("Assert that price from the list is equal with the price in overview screen");
        String price = pqTripDetailedViewPageObject.getDetailedViewFlightPrice().getText();
        Assert.assertEquals(price,pQFlightPrice);

        logWrite.info("Click on book flight");
        headerPageObject.cancelMemberOffer();
        pqTripDetailedViewPageObject.clickBookFlight();
        headerPageObject.waitForLoadingBeeToLoad();
        //TODO: need an assertion to check the price for the flight, no stable locator

        String cachedUrl = baseClass.getCurrentUrl();

        logWrite.info("Open direct search url " + cachedUrl);
        baseClass.openPage(cachedUrl);
        headerPageObject.waitForLoadingBeeToLoad();

        //TODO: need an assertion to check the price for the flight, no stable locator
    }


    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }

}
