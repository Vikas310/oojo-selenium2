package bookprocess;

import common.Helper;
import constants.Constants;
import constants.FlightCodes;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.*;
import selenium.BaseClass;
import selenium.BaseTest;

@Listeners(common.Listeners.class)
public class SearchResultsTests extends BaseTest {

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
    public void checkFlightDateFromForFoundFlightFastestFlightTest() {

        int flight = 0;

        String customDate = Helper.getDateWithSpecificDaysInFuture(Constants.TEN_DAYS,"yyyy-MM-dd");
        String customDateSearchResult = Helper.getDateWithSpecificDaysInFuture(Constants.TEN_DAYS,"EEE, MMM d");
        String customDateTakeSegment = Helper.getDateWithSpecificDaysInFuture(Constants.TEN_DAYS,"MMM d");

        String fullUrl = BaseClass.OOJO_URL+Helper.getFlightSearchResultOneWay(FlightCodes.DALLAS_CODE,
                FlightCodes.LOS_ANGELOS, customDate);

        logWrite.info("Open direct search url " + fullUrl);
        baseClass.openPage(
                fullUrl);

        searchResultPageObject.waitForSearchLoad();
        logWrite.info("Accept cookies if there are any");
        headerPageObject.acceptCookies();
        headerPageObject.cancelMemberOffer();

        logWrite.info("Select trip");
        headerPageObject.cancelMemberOffer();

        searchResultPageObject.selectCheapestFlights();
        searchResultPageObject.selectBestFlights();
        searchResultPageObject.selectFastestFlights();

        logWrite.info("Search stats: " + searchResultPageObject.getSearchStats());
        String flightStartDate = searchResultPageObject.getFlightStartDate(flight);

        String pQFlightPrice = searchResultPageObject.getTripOptionPriceByIndex(flight).getText();
        logWrite.info("Flight price from the search screen: " + pQFlightPrice);

        searchResultPageObject.selectTripOptionPq(flight);
        headerPageObject.cancelMemberOffer();

        String takeSegmentFlightDate = pqTripDetailedViewPageObject.getSegmentFlightDate();
        logWrite.info(takeSegmentFlightDate + " ??!!!!!!");

        logWrite.info("Assert flight dates on search result screen & take segment screen");
        logWrite.info("Flight start date is: " + flightStartDate + "should match with: " + customDateSearchResult);
        Assert.assertEquals(flightStartDate,customDateSearchResult, "Flight Search date is different");
        logWrite.info("Take segment flight date is: " + takeSegmentFlightDate + "should match with: " + customDateTakeSegment);
        Assert.assertEquals(takeSegmentFlightDate,customDateTakeSegment, "Flight Search take segment date is different");

        logWrite.info("Assert that price from the list is equal with the price in overview screen");
        Assert.assertEquals(pqTripDetailedViewPageObject.getDetailedViewFlightPrice().getText(), pQFlightPrice);

    }

    @Test
    public void checkFlightDateFromForFoundFlightBestFlightTest() {

        int flight = 0;

        String customDate = Helper.getDateWithSpecificDaysInFuture(Constants.TEN_DAYS,"yyyy-MM-dd");
        String customDateSearchResult = Helper.getDateWithSpecificDaysInFuture(Constants.TEN_DAYS,"EEE, MMM d");
        String customDateTakeSegment = Helper.getDateWithSpecificDaysInFuture(Constants.TEN_DAYS,"MMM d");

        String fullUrl = BaseClass.OOJO_URL+Helper.getFlightSearchResultOneWay(FlightCodes.DALLAS_CODE,
                FlightCodes.LOS_ANGELOS, customDate);

        logWrite.info("Open direct search url " + fullUrl);
        baseClass.openPage(
                fullUrl);

        searchResultPageObject.waitForSearchLoad();
        logWrite.info("Accept cookies if there are any");
        headerPageObject.acceptCookies();
        headerPageObject.cancelMemberOffer();

        logWrite.info("Select trip");
        headerPageObject.cancelMemberOffer();

        searchResultPageObject.selectCheapestFlights();
        searchResultPageObject.selectBestFlights();

        logWrite.info("Search stats: " + searchResultPageObject.getSearchStats());
        String flightStartDate = searchResultPageObject.getFlightStartDate(flight);

        String pQFlightPrice = searchResultPageObject.getTripOptionPriceByIndex(flight).getText();
        logWrite.info("Flight price from the search screen: " + pQFlightPrice);

        searchResultPageObject.selectTripOptionPq(flight);
        headerPageObject.cancelMemberOffer();

        String takeSegmentFlightDate = pqTripDetailedViewPageObject.getSegmentFlightDate();
        logWrite.info(takeSegmentFlightDate + " ??!!!!!!");

        logWrite.info("Assert flight dates on search result screen & take segment screen");
        logWrite.info("Flight start date is: " + flightStartDate + "should match with: " + customDateSearchResult);
        Assert.assertEquals(flightStartDate,customDateSearchResult, "Flight Search date is different");
        logWrite.info("Take segment flight date is: " + takeSegmentFlightDate + "should match with: " + customDateTakeSegment);
        Assert.assertEquals(takeSegmentFlightDate,customDateTakeSegment, "Flight Search take segment date is different");

        logWrite.info("Assert that price from the list is equal with the price in overview screen");
        Assert.assertEquals(pqTripDetailedViewPageObject.getDetailedViewFlightPrice().getText(), pQFlightPrice);

    }

    @Test
    public void checkFlightDateFromForFoundFlightCheapestFlightTest() {

        int flight = 0;

        String customDate = Helper.getDateWithSpecificDaysInFuture(Constants.TEN_DAYS,"yyyy-MM-dd");
        String customDateSearchResult = Helper.getDateWithSpecificDaysInFuture(Constants.TEN_DAYS,"EEE, MMM d");
        String customDateTakeSegment = Helper.getDateWithSpecificDaysInFuture(Constants.TEN_DAYS,"MMM d");

        String fullUrl = BaseClass.OOJO_URL+Helper.getFlightSearchResultOneWay(FlightCodes.DALLAS_CODE,
                FlightCodes.LOS_ANGELOS, customDate);

        logWrite.info("Open direct search url " + fullUrl);
        baseClass.openPage(
                fullUrl);

        searchResultPageObject.waitForSearchLoad();
        logWrite.info("Accept cookies if there are any");
        headerPageObject.acceptCookies();
        headerPageObject.cancelMemberOffer();

        logWrite.info("Select trip");
        headerPageObject.cancelMemberOffer();

        searchResultPageObject.selectCheapestFlights();
        searchResultPageObject.selectBestFlights();
        searchResultPageObject.selectCheapestFlights();

        logWrite.info("Search stats: " + searchResultPageObject.getSearchStats());
        String flightStartDate = searchResultPageObject.getFlightStartDate(flight);

        String pQFlightPrice = searchResultPageObject.getTripOptionPriceByIndex(flight).getText();
        logWrite.info("Flight price from the search screen: " + pQFlightPrice);

        searchResultPageObject.selectTripOptionPq(flight);
        headerPageObject.cancelMemberOffer();

        String takeSegmentFlightDate = pqTripDetailedViewPageObject.getSegmentFlightDate();
        logWrite.info(takeSegmentFlightDate + " ??!!!!!!");

        logWrite.info("Assert flight dates on search result screen & take segment screen");
        logWrite.info("Flight start date is: " + flightStartDate + "should match with: " + customDateSearchResult);
        Assert.assertEquals(flightStartDate,customDateSearchResult, "Flight Search date is different");
        logWrite.info("Take segment flight date is: " + takeSegmentFlightDate + "should match with: " + customDateTakeSegment);
        Assert.assertEquals(takeSegmentFlightDate,customDateTakeSegment, "Flight Search take segment date is different");

        logWrite.info("Assert that price from the list is equal with the price in overview screen");
        Assert.assertEquals(pqTripDetailedViewPageObject.getDetailedViewFlightPrice().getText(), pQFlightPrice);

    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }

    }
