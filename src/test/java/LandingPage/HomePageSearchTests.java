package LandingPage;

import common.Helper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import pageObjects.SearchFlightObject;
import selenium.BaseClass;
import selenium.BaseTest;

public class HomePageSearchTests extends BaseTest {

    BaseClass baseClass;
    HomePageObject homePageObject;
    SearchFlightObject searchFlightObject;

    @BeforeMethod(alwaysRun = true)
    public void initiate() {
        driver = getDriver();
        baseClass = new BaseClass(driver);
        baseClass.getEnvironment();
        baseClass.openPage(BaseClass.OOJO_STAGE_URL);
        homePageObject = new HomePageObject(driver);
        searchFlightObject = new SearchFlightObject(driver);

    }

    @Test
    public void searchResultFound() throws InterruptedException {
        logWrite.info("Test comments, put before every action - for better debug in CI");

        searchFlightObject.fillFlightFrom("New York")
                .fillFlightTo("Manila")
                .submitFlightSearch();

        searchFlightObject.selectTravelers();
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }
}
