package adminbooking;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.EmcPageObject;
import selenium.BaseClass;
import selenium.BaseTest;
@Listeners(common.Listeners.class)
public class AdminBookingTests extends BaseTest {

    BaseClass baseClass;
    EmcPageObject emcPageObject;

    @BeforeMethod(alwaysRun = true)
    public void initiate() {
        driver = getDriver();
        baseClass = new BaseClass(driver);
        baseClass.getEnvironment();
        baseClass.openPage(BaseClass.OOJO_ADMIN_URL);
        emcPageObject = new EmcPageObject(driver);
    }

    @Test
    public void loginInAdminPage()  {
        logWrite.info("Login with admin user via Booking Admin");
        emcPageObject.fillLogin(BaseClass.OOJO_ADMIN_USERNAME).
                fillPassword(BaseClass.OOJO_ADMIN_PASS).
                submitCredentials();
        //TODO: Dummy example
    }


    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }

}
