package frss;

import common.Helper;
import constants.Constants;
import constants.FlightCodes;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.*;
import selenium.BaseClass;
import selenium.BaseTest;

import java.util.List;
import java.util.stream.Collectors;

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
    //Open second flight
    //Change filters
    //Check if flight date matches take segment date from dates which user has entered in search
    @Test
    public void checkFlightDateFromSecondFoundFlightFastestFlightTest() {

        int flight = 1;

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

        searchResultPageObject.selectCheapestFlights()
                .selectBestFlights()
                .selectFastestFlights();

        logWrite.info("Search stats: " + searchResultPageObject.getSearchStats());
        String flightStartDate = searchResultPageObject.getFlightStartDate(flight);

        String pQFlightPrice = searchResultPageObject.getTripOptionPriceByIndex(flight).getText();
        logWrite.info("Flight price from the search screen: " + pQFlightPrice);

        searchResultPageObject.selectTripOptionPq(flight);
        headerPageObject.cancelMemberOffer();

        String takeSegmentFlightDate = pqTripDetailedViewPageObject.getSegmentFlightDate();
        String takeSegmentFlightDateDetails = pqTripDetailedViewPageObject.getTakeSegmentFromDates().get(0).getText();

        logWrite.info("Assert flight dates on search result screen & take segment screen");
        logWrite.info("Flight start date is: " + flightStartDate + "should match with: " + customDateSearchResult);
        Assert.assertEquals(flightStartDate,customDateSearchResult, "Flight Search date is different");
        Assert.assertEquals(flightStartDate,takeSegmentFlightDateDetails, "Flights from detailed Search date is different");
        logWrite.info("Take segment flight date is: " + takeSegmentFlightDate + "should match with: " + customDateTakeSegment);
        Assert.assertEquals(takeSegmentFlightDate,customDateTakeSegment, "Flight Search take segment date is different");

        logWrite.info("Assert that price from the list is equal with the price in overview screen");
        Assert.assertEquals(pqTripDetailedViewPageObject.getDetailedViewFlightPrice().getText(), pQFlightPrice);

    }
    //Open second flight
    //Change filters
    //Check if flight date matches take segment date from dates which user has entered in search
    @Test
    public void checkFlightDateFromSecondFoundFlightBestFlightTest() {

        int flight = 1;

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

        searchResultPageObject.selectCheapestFlights()
                .selectBestFlights()
                .selectFastestFlights()
                .selectBestFlights();

        logWrite.info("Search stats: " + searchResultPageObject.getSearchStats());
        String flightStartDate = searchResultPageObject.getFlightStartDate(flight);

        String pQFlightPrice = searchResultPageObject.getTripOptionPriceByIndex(flight).getText();
        logWrite.info("Flight price from the search screen: " + pQFlightPrice);

        searchResultPageObject.selectTripOptionPq(flight);
        headerPageObject.cancelMemberOffer();

        String takeSegmentFlightDate = pqTripDetailedViewPageObject.getSegmentFlightDate();
        String takeSegmentFlightDateDetails = pqTripDetailedViewPageObject.getTakeSegmentFromDates().get(0).getText();

        logWrite.info("Assert flight dates on search result screen & take segment screen");
        logWrite.info("Flight start date is: " + flightStartDate + "should match with: " + customDateSearchResult);
        Assert.assertEquals(flightStartDate,customDateSearchResult, "Flight Search date is different");
        Assert.assertEquals(flightStartDate,takeSegmentFlightDateDetails, "Flights from detailed Search date is different");
        logWrite.info("Take segment flight date is: " + takeSegmentFlightDate + "should match with: " + customDateTakeSegment);
        Assert.assertEquals(takeSegmentFlightDate,customDateTakeSegment, "Flight Search take segment date is different");

        logWrite.info("Assert that price from the list is equal with the price in overview screen");
        Assert.assertEquals(pqTripDetailedViewPageObject.getDetailedViewFlightPrice().getText(), pQFlightPrice);

    }
    //Open second flight
    //Change filters
    //Check if flight date matches take segment date from dates which user has entered in search
    @Test
    public void checkFlightDateFromSecondFoundFlightCheapestFlightTest() {

        int flight = 1;

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

        searchResultPageObject.selectCheapestFlights()
                .selectBestFlights()
                .selectCheapestFlights();

        logWrite.info("Search stats: " + searchResultPageObject.getSearchStats());
        String flightStartDate = searchResultPageObject.getFlightStartDate(flight);

        String pQFlightPrice = searchResultPageObject.getTripOptionPriceByIndex(flight).getText();
        logWrite.info("Flight price from the search screen: " + pQFlightPrice);

        searchResultPageObject.selectTripOptionPq(flight);
        headerPageObject.cancelMemberOffer();

        String takeSegmentFlightDate = pqTripDetailedViewPageObject.getSegmentFlightDate();
        String takeSegmentFlightDateDetails = pqTripDetailedViewPageObject.getTakeSegmentFromDates().get(0).getText();

        logWrite.info("Assert flight dates on search result screen & take segment screen");
        logWrite.info("Flight start date is: " + flightStartDate + "should match with: " + customDateSearchResult);
        Assert.assertEquals(flightStartDate,takeSegmentFlightDateDetails, "Flights from detailed Search date is different");
        Assert.assertEquals(flightStartDate,customDateSearchResult, "Flight Search date is different");
        logWrite.info("Take segment flight date is: " + takeSegmentFlightDate + "should match with: " + customDateTakeSegment);
        Assert.assertEquals(takeSegmentFlightDate,customDateTakeSegment, "Flight Search take segment date is different");

        logWrite.info("Assert that price from the list is equal with the price in overview screen");
        Assert.assertEquals(pqTripDetailedViewPageObject.getDetailedViewFlightPrice().getText(), pQFlightPrice);

    }
    //Open second flight
    //Change filters
    //Get found flight list, split it in half * check if all found flights collection is equal to entered search date by the end user
    @Test
    public void checkFoundFlightSearchResultIfFromDateIsIdenticalForFastestFlightsTest() {

        String customDate = Helper.getDateWithSpecificDaysInFuture(Constants.TEN_DAYS,"yyyy-MM-dd");
        String customDateSearchResult = Helper.getDateWithSpecificDaysInFuture(Constants.TEN_DAYS,"EEE, MMM d");

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

        searchResultPageObject.selectCheapestFlights()
                .selectBestFlights()
                .selectFastestFlights();

        List<WebElement> allFlightsDatesFromSearchList = searchResultPageObject.getAllFlightStartDates();
        List <WebElement> filteredList = allFlightsDatesFromSearchList.stream().filter( ele -> ele.getText().equals(customDateSearchResult)).collect(Collectors.toList());

        Assert.assertEquals(allFlightsDatesFromSearchList.size(),filteredList.size(), "Date from list count is not equal");

    }

    //Open second flight
    //Change filters
    //Get list of found flights, split it in half, open every flight and check flight from date (usual flight check 8, since found flights are mostly 16)

    @Test
    public void checkSeveralFlightFromDatesTest() {

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

        searchResultPageObject.selectCheapestFlights().
                selectFastestFlights();

        List<WebElement> allFlightsDatesFromSearchList = searchResultPageObject.getAllFlightStartDates();
        int allFlightsDatesFromSearchListCount = searchResultPageObject.getAllFlightStartDates().size()/2;

        List <WebElement> filteredList = allFlightsDatesFromSearchList.stream()
                .filter( ele -> ele.getText()
                        .equals(customDateSearchResult))
                .collect(Collectors.toList());
        Assert.assertEquals(allFlightsDatesFromSearchList.size(),filteredList.size(), "Date flight FROM list count is not equal");

        for (int i=0;i<=allFlightsDatesFromSearchListCount;i++){
            logWrite.info("Check flight: " + i);
            logWrite.info("Search stats: " + searchResultPageObject.getSearchStats());
            String flightStartDate = searchResultPageObject.getFlightStartDate(i);

            String pQFlightPrice = searchResultPageObject.getTripOptionPriceByIndex(i).getText();
            logWrite.info("Flight price from the search screen: " + pQFlightPrice);

            searchResultPageObject.selectTripOptionPq(i);
            headerPageObject.cancelMemberOffer();

            String takeSegmentFlightDate = pqTripDetailedViewPageObject.getSegmentFlightDate();
            String takeSegmentFlightDateDetails = pqTripDetailedViewPageObject.getTakeSegmentFromDates().get(0).getText();

            logWrite.info("Assert flight dates on search result screen & take segment screen");
            logWrite.info("Flight start date is: " + flightStartDate + "should match with: " + customDateSearchResult);
            Assert.assertEquals(flightStartDate,takeSegmentFlightDateDetails, "Flights from detailed Search date is different");
            Assert.assertEquals(flightStartDate,customDateSearchResult, "Flight Search date is different");
            logWrite.info("Take segment flight date is: " + takeSegmentFlightDate + "should match with: " + customDateTakeSegment);
            Assert.assertEquals(takeSegmentFlightDate,customDateTakeSegment, "Flight Search take segment date is different");

            logWrite.info("Assert that price from the list is equal with the price in overview screen");
            Assert.assertEquals(pqTripDetailedViewPageObject.getDetailedViewFlightPrice().getText(), pQFlightPrice);
            pqTripDetailedViewPageObject.closeTakeSegment();
        }

    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }

    }
