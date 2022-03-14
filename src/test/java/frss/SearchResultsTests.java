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

        String customDate = Helper.getDateWithSpecificMonthsInFuture(Constants.ONE_MONTH,"yyyy-MM-dd");
        String customDateSearchResult = Helper.getDateWithSpecificMonthsInFuture(Constants.ONE_MONTH,"EEE, MMM d");
        String customDateTakeSegment = Helper.getDateWithSpecificMonthsInFuture(Constants.ONE_MONTH,"MMM d");

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

        String customDate = Helper.getDateWithSpecificMonthsInFuture(Constants.ONE_MONTH,"yyyy-MM-dd");
        String customDateSearchResult = Helper.getDateWithSpecificMonthsInFuture(Constants.ONE_MONTH,"EEE, MMM d");
        String customDateTakeSegment = Helper.getDateWithSpecificMonthsInFuture(Constants.ONE_MONTH,"MMM d");

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

        String customDate = Helper.getDateWithSpecificMonthsInFuture(Constants.ONE_MONTH,"yyyy-MM-dd");
        String customDateSearchResult = Helper.getDateWithSpecificMonthsInFuture(Constants.ONE_MONTH,"EEE, MMM d");
        String customDateTakeSegment = Helper.getDateWithSpecificMonthsInFuture(Constants.ONE_MONTH,"MMM d");

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

        String customDate = Helper.getDateWithSpecificMonthsInFuture(Constants.ONE_MONTH,"yyyy-MM-dd");
        String customDateSearchResult = Helper.getDateWithSpecificMonthsInFuture(Constants.ONE_MONTH,"EEE, MMM d");

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

        List <WebElement> filteredList = allFlightsDatesFromSearchList.stream()
                .filter( ele -> ele.getText().equals(customDateSearchResult))
                .collect(Collectors.toList());

        Assert.assertEquals(allFlightsDatesFromSearchList.size(),filteredList.size(), "Date from list count is not equal");

    }

    //Open second flight
    //Change filters
    //Get list of found flights, split it in half, open every flight and check flight from date (usual flight check 8, since found flights are mostly 16)

    @Test
    public void checkSeveralFlightFromDatesTest() {

        String customDate = Helper.getDateWithSpecificMonthsInFuture(Constants.ONE_MONTH,"yyyy-MM-dd");
        String customDateSearchResult = Helper.getDateWithSpecificMonthsInFuture(Constants.ONE_MONTH, "EEE, MMM d");
        String customDateTakeSegment = Helper.getDateWithSpecificMonthsInFuture(Constants.ONE_MONTH, "MMM d");

        String fullUrl = BaseClass.OOJO_URL + Helper.getFlightSearchResultOneWay(FlightCodes.DALLAS_CODE,
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
        int allFlightsDatesFromSearchListCount = searchResultPageObject.getAllFlightStartDates().size() / 2;

        List<WebElement> filteredList = allFlightsDatesFromSearchList.stream()
                .filter(ele -> ele.getText()
                        .equals(customDateSearchResult))
                .collect(Collectors.toList());
        Assert.assertEquals(allFlightsDatesFromSearchList.size(), filteredList.size(), "Date flight FROM list count is not equal");
        logWrite.info(allFlightsDatesFromSearchListCount + " Total flights which will be checked");

        for (int i = 0; i <= allFlightsDatesFromSearchListCount; i++) {
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
            Assert.assertEquals(flightStartDate, takeSegmentFlightDateDetails, "Flights from detailed Search date is different");
            Assert.assertEquals(flightStartDate, customDateSearchResult, "Flight Search date is different");
            logWrite.info("Take segment flight date is: " + takeSegmentFlightDate + "should match with: " + customDateTakeSegment);
            Assert.assertEquals(takeSegmentFlightDate, customDateTakeSegment, "Flight Search take segment date is different");

            logWrite.info("Assert that price from the list is equal with the price in overview screen");
            Assert.assertEquals(pqTripDetailedViewPageObject.getDetailedViewFlightPrice().getText(), pQFlightPrice);
            pqTripDetailedViewPageObject.closeTakeSegment();
        }

    }
        //TODO: additional date assert for take segment date is required
    //Open Filter Alternative Dates
    //Find the count of all alternative day flights
    //Assert if Start Date from search results is equal to Start date from TakeSegment & check if alternative flight label is displayed for all flights
        @Test
        public void checkSeveralAlternativeFlightDates() {

            String customDate = Helper.getDateWithSpecificMonthsInFuture(Constants.TWO_MONTH,"yyyy-MM-dd");
            String customDateSearchResult = Helper.getDateWithSpecificMonthsInFuture(Constants.TWO_MONTH, "EEE, MMM d");

            String fullUrl = BaseClass.OOJO_URL + Helper.getFlightSearchResultOneWay(FlightCodes.DALLAS_CODE,
                    FlightCodes.LOS_ANGELOS, customDate);

            logWrite.info( "Open direct search url " + fullUrl);
            baseClass.openPage(
                    fullUrl);

            searchResultPageObject.waitForSearchLoad();
            logWrite.info("Accept cookies if there are any");
            headerPageObject.acceptCookies();
            headerPageObject.cancelMemberOffer();

            logWrite.info("Select trip");
            headerPageObject.cancelMemberOffer();

            searchResultPageObject.selectCheapestFlights().
                    selectFastestFlights().
                    selectAlternativeDateFlights();

            int allFlightsDatesFromSearchListCount = searchResultPageObject.getAllFlightStartDates().size()-1;
            logWrite.info("Total flights will be checked: " + allFlightsDatesFromSearchListCount);

            for (int i = 0; i <= allFlightsDatesFromSearchListCount; i++) {
                logWrite.info("Check flight: " + i);
                logWrite.info("Search stats: " + searchResultPageObject.getSearchStats());

                String flightStartDate = searchResultPageObject.getFlightStartDate(i);
                String pQFlightPrice = searchResultPageObject.getTripOptionPriceByIndex(i).getText();
                logWrite.info("Flight price from the search screen: " + pQFlightPrice);

                Assert.assertNotEquals(customDateSearchResult,flightStartDate + "Start date is equal to users searched date.");

                searchResultPageObject.selectTripOptionPq(i);
                headerPageObject.cancelMemberOffer();

                String takeSegmentFlightDateDetails = pqTripDetailedViewPageObject.getTakeSegmentFromDates().get(0).getText();

                logWrite.info("Flight start date is: " + flightStartDate + " should match with: " + takeSegmentFlightDateDetails);

                Assert.assertEquals(flightStartDate, takeSegmentFlightDateDetails, "Flights from detailed Search date is different");
                Assert.assertTrue(pqTripDetailedViewPageObject.getAlternativeFlightLabel().isDisplayed(), "Alternative Flight Label is not displayed");
                logWrite.info("Assert that price from thgetDateWithSpecificMonthse list is equal with the price in overview screen");
                Assert.assertEquals(pqTripDetailedViewPageObject.getDetailedViewFlightPrice().getText(), pQFlightPrice);
                pqTripDetailedViewPageObject.closeTakeSegment();
            }
        }

    @Test
    public void checkSeveralTakeSegmentsAndIfFailsCheckIfTheFlightWasDeleted() {

        String customDate = Helper.getDateWithSpecificDaysInFuture(Constants.TWO_DAYS,"yyyy-MM-dd");
        String customDateSearchResult = Helper.getDateWithSpecificDaysInFuture(Constants.TWO_DAYS, "EEE, MMM d");

        String fullUrl = BaseClass.OOJO_URL + Helper.getFlightSearchResultOneWay(FlightCodes.JOHN_KENNEDY,
                FlightCodes.LOS_ANGELOS, customDate);

        logWrite.info( "Open direct search url " + fullUrl);
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

        int allFlightsDatesFromSearchListCount = searchResultPageObject.getAllFlightStartDates().size()/2;
        logWrite.info("Total flights will be checked: " + allFlightsDatesFromSearchListCount);

        for (int i = 0; i <= allFlightsDatesFromSearchListCount; i++) {
            logWrite.info("Check flight: " + i);
            logWrite.info("Search stats: " + searchResultPageObject.getSearchStats());

            String flightStartDate = searchResultPageObject.getFlightStartDate(i);
            String flightStartTime = searchResultPageObject.getBookScreenFlightStartTime(i).getText();
            String pQFlightPrice = searchResultPageObject.getTripOptionPriceByIndex(i).getText();
            logWrite.info("Flight price from the search screen: " + pQFlightPrice);

            Assert.assertNotEquals(customDateSearchResult,flightStartDate + "Start date is equal to users searched date.");

            searchResultPageObject.selectTripOptionPq(i);
            headerPageObject.cancelMemberOffer();

            String flightDetails = pqTripDetailedViewPageObject.getFlightInfo();
            logWrite.info( flightDetails + " Flight number from search");
            String takeSegmentFlightDateDetails = pqTripDetailedViewPageObject.getTakeSegmentFromDates().get(0).getText();

            logWrite.info("Flight start date is: " + flightStartDate + " should match with: " + takeSegmentFlightDateDetails);

            Assert.assertEquals(flightStartDate, takeSegmentFlightDateDetails, "Flights from detailed Search date is different");

            logWrite.info("Assert that price from the list is equal with the price in overview screen");
            Assert.assertEquals(pqTripDetailedViewPageObject.getDetailedViewFlightPrice().getText(), pQFlightPrice);

            headerPageObject.cancelMemberOffer();
            logWrite.info("Assert that price from the list is equal with the price in overview screen");
            Assert.assertEquals(pqTripDetailedViewPageObject.getDetailedViewFlightPrice().getText(),pQFlightPrice);
            headerPageObject.cancelMemberOffer();

            logWrite.info("Click on book flight");
            pqTripDetailedViewPageObject.clickBookFlight();
            headerPageObject.waitForLoadingBeeToLoad();

            
            if (bookPageObject.takeSegmentFailed()) {
                logWrite.info("Take segment failed " + bookPageObject.takeSegmentFailed());
                bookPageObject.clickContinueTakeSegmentFailed()
                        .waitForSearchLoad();
                logWrite.info("Select the same flight again: " + i );
                searchResultPageObject.selectTripOptionPq(i);

                String flightDetailsForDeletedPq = pqTripDetailedViewPageObject.getFlightInfo();
                Assert.assertEquals(flightDetails,flightDetailsForDeletedPq + "This flight should be deleted");
            }

            logWrite.info("Flight quick URL: " + baseClass.getCurrentUrl());
            String totalPrice = bookPageObject.getTotalPrice().getText();
            logWrite.info("Check that price from the search result matches the price on the book screen " + totalPrice);
            Assert.assertEquals(totalPrice,pQFlightPrice, "Price from flight form search result does not match price from book screen");
            //assert flight date
            String bookScreenStartFlightDate = bookPageObject.getBookScreenFlightStartDate(1).getText();
            String bookScreenStartFlightTime = bookPageObject.getBookScreenFlightStartTime(1).getText();

            logWrite.info("Check if search result start date "+flightStartDate+" matches book screen flight start date " + bookScreenStartFlightDate);
            Assert.assertEquals(flightStartDate,bookScreenStartFlightDate);

            logWrite.info("Check if search result start Time "+flightStartTime+" matches book screen flight start date " + bookScreenStartFlightTime);
            Assert.assertEquals(flightStartTime,bookScreenStartFlightTime);

            bookPageObject.expandFlightDetails();
            String bookScreenFlightDetails = pqTripDetailedViewPageObject.getFlightInfo();
            logWrite.info(flightDetails.toLowerCase() + " PQ detail view flight number");
            logWrite.info(bookScreenFlightDetails.toLowerCase() + "Book screen flight number");

            Assert.assertEquals(flightDetails.toLowerCase(),bookScreenFlightDetails.toLowerCase(), "Flight details are not identical");
            bookPageObject.clickOnCheckMoreFlights();
            searchResultPageObject.waitForSearchLoad();
        }
    }


    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }

    }
