package frss;

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
public class SearchFlightTests extends BaseTest {

    BaseClass baseClass;
    HomePageObject homePageObject;
    SearchFlightObject searchFlightObject;
    SearchResultPageObject searchResultPageObject;
    PqTripDetailedViewPageObject pqTripDetailedViewPageObject;
    BookPageObject bookPageObject;
    HeaderPageObject headerPageObject;

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
    public void searchFlightAndOpenSavedUrlFromNewYorkToLosAngelos() {

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
        logWrite.info("Select trip");
        searchResultPageObject.selectTripOptionPq(flight);

        logWrite.info("Assert that price from the list is equal with the price in overview screen");
        String price = pqTripDetailedViewPageObject.getDetailedViewFlightPrice().getText();
        Assert.assertEquals(price,pQFlightPrice);

        logWrite.info("Click on book flight");
        headerPageObject.cancelMemberOffer();
        pqTripDetailedViewPageObject.clickBookFlight();
        headerPageObject.waitForLoadingBeeToLoad();
        String cachedUrl = baseClass.getCurrentUrl();

        String totalPrice = bookPageObject.getTotalPrice().getText();
        logWrite.info("Check that price from the search result matches the price on the book screen " + totalPrice);
        Assert.assertEquals(totalPrice,pQFlightPrice, "Price from flight form search result does not match price from book screen");

        bookPageObject.clickOnCheckMoreFlights();

        logWrite.info("Open direct search url " + cachedUrl);
        baseClass.openPage(cachedUrl);
        headerPageObject.waitForLoadingBeeToLoad();

        String totalPriceSecondTime = bookPageObject.getTotalPrice().getText();
        logWrite.info("Check that price from the search result matches the price on the book screen " + totalPriceSecondTime);
        Assert.assertEquals(totalPriceSecondTime,pQFlightPrice, "Price from flight form search result does not match price from book screen");

    }

    @Test
    public void searchFlightAndOpenSavedUrlFromNewYorkToMiami() {

        int flight = 0;
        String customDate = Helper.getDateWithSpecificMonthsInFuture(Constants.FOUR_MONTHS,"yyyy-MM-dd");

        String fullUrl = BaseClass.OOJO_URL+Helper.getFlightSearchResultOneWay(FlightCodes.NEW_YORK_CODE,
                FlightCodes.MIAMI,
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
        headerPageObject.cancelMemberOffer();
        searchResultPageObject.selectTripOptionPq(flight);

        logWrite.info("Assert that price from the list is equal with the price in overview screen");
        String price = pqTripDetailedViewPageObject.getDetailedViewFlightPrice().getText();
        Assert.assertEquals(price,pQFlightPrice);

        logWrite.info("Click on book flight");
        headerPageObject.cancelMemberOffer();
        pqTripDetailedViewPageObject.clickBookFlight();
        headerPageObject.waitForLoadingBeeToLoad();
        String cachedUrl = baseClass.getCurrentUrl();

        String totalPrice = bookPageObject.getTotalPrice().getText();
        logWrite.info("Check that price from the search result matches the price on the book screen " + totalPrice);
        Assert.assertEquals(totalPrice,pQFlightPrice, "Price from flight form search result does not match price from book screen");

        bookPageObject.clickOnCheckMoreFlights();

        logWrite.info("Open direct search url " + cachedUrl);
        baseClass.openPage(cachedUrl);
        headerPageObject.waitForLoadingBeeToLoad();

        String totalPriceSecondTime = bookPageObject.getTotalPrice().getText();
        logWrite.info("Check that price from the search result matches the price on the book screen " + totalPriceSecondTime);
        Assert.assertEquals(totalPriceSecondTime,pQFlightPrice, "Price from flight form search result does not match price from book screen");

    }


    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }

}
