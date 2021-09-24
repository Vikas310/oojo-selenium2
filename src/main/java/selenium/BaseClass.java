package selenium;

import common.PropertyLoader;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass extends BaseTest {

    public static final int TIMEOUT_5 = 5;
    public static final int TIMEOUT_10 = 10;
    public static final int TIMEOUT_20 = 20;
    public static final int TIMEOUT_40 = 40;

    public WebDriver driver;
    public String env = System.getProperty("env");

    public BaseClass(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static String OOJO_URL_ASSIGN;
    public static String OOJO_ADMIN_URL_ASSIGN;

    public static String OOJO_ADMIN_URL;
    public static String OOJO_ADMIN_USERNAME;
    public static String OOJO_ADMIN_PASS;

    public static final String OOJO_STAGE_URL_CONFIG = PropertyLoader.loadProperty("OOJO_STAGE_BASE_URL");
    public static final String OOJO_STAGE_ADMIN_BOOKING_URL_CONFIG = PropertyLoader.loadProperty("OOJO_STAGE_ADMIN_BOOKING_BASE_URL");
    public static final String BOOK_ADMIN_USER_CONFIG = PropertyLoader.loadProperty("BOOK_ADMIN_USER");
    public static final String BOOK_ADMIN_PASS_CONFIG = PropertyLoader.loadProperty("BOOK_ADMIN_PASS");

    public static final String OOJO_DEV_URL_CONFIG = PropertyLoader.loadProperty("OOJO_DEV_BASE_URL");
    public static final String OOJO_DEV_ADMIN_BOOKING_URL_CONFIG = PropertyLoader.loadProperty("OOJO_DEV_ADMIN_BOOKING_BASE_URL");

    public static final String OOJO_PROD_URL_CONFIG = PropertyLoader.loadProperty("OOJO_PROD_BASE_URL");
    public static final String OOJO_PROD_ADMIN_BOOKING_URL_CONFIG = PropertyLoader.loadProperty("OOJO_PROD_ADMIN_BOOKING_BASE_URL");


    public void getEnvironment(){

        if (env == null) {
            //dev env
            OOJO_URL_ASSIGN = OOJO_STAGE_URL_CONFIG;
            OOJO_ADMIN_URL = OOJO_STAGE_ADMIN_BOOKING_URL_CONFIG;
            OOJO_ADMIN_USERNAME = BOOK_ADMIN_USER_CONFIG;
            OOJO_ADMIN_PASS = BOOK_ADMIN_PASS_CONFIG;
            logWrite.info("Env value is " +env+ " url used default stage " + OOJO_URL_ASSIGN + " " + OOJO_ADMIN_URL + " " + OOJO_ADMIN_USERNAME);
        } else {
            switch (env) {
                case "dev":
                    OOJO_URL_ASSIGN = OOJO_DEV_URL_CONFIG;
                    OOJO_ADMIN_URL = OOJO_DEV_ADMIN_BOOKING_URL_CONFIG;
                    OOJO_ADMIN_USERNAME = BOOK_ADMIN_USER_CONFIG;
                    OOJO_ADMIN_PASS = BOOK_ADMIN_PASS_CONFIG;
                    logWrite.info("Env value is " +env+ " url used default stage " + OOJO_URL_ASSIGN + " " + OOJO_ADMIN_URL + " " + OOJO_ADMIN_USERNAME);
                    break;
                case "stage":
                    OOJO_URL_ASSIGN = OOJO_STAGE_URL_CONFIG;
                    OOJO_ADMIN_URL = OOJO_STAGE_ADMIN_BOOKING_URL_CONFIG;
                    OOJO_ADMIN_URL_ASSIGN = BOOK_ADMIN_USER_CONFIG;
                    OOJO_ADMIN_PASS = BOOK_ADMIN_PASS_CONFIG;
                    logWrite.info("Env value is " +env+ " url used default stage " + OOJO_URL_ASSIGN + " " + OOJO_ADMIN_URL + " " + OOJO_ADMIN_USERNAME);
                    break;
                case "prod":

                    OOJO_URL_ASSIGN = OOJO_PROD_URL_CONFIG;
                    OOJO_ADMIN_URL = OOJO_PROD_ADMIN_BOOKING_URL_CONFIG;
                    OOJO_ADMIN_URL_ASSIGN = BOOK_ADMIN_USER_CONFIG;
                    OOJO_ADMIN_PASS = BOOK_ADMIN_PASS_CONFIG;
                    logWrite.info("Env value is " +env+ " url used default stage " + OOJO_URL_ASSIGN + " " + OOJO_ADMIN_URL + " " + OOJO_ADMIN_USERNAME);

                    break;
            }
        }
    }

    public Boolean waitForElementVisibility(WebElement locator, int $seconds) {
        try {
            logWrite.info("Wait for element to be visible");
            WebDriverWait wait = new WebDriverWait(driver, $seconds);
            wait.until(ExpectedConditions.visibilityOf(locator));
            return true;
        } catch (NoSuchElementException e) {
            logWrite.info("Element not found!");
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean waitForElementToBeClickable(WebElement locator, int $seconds) {
        try {
            logWrite.info("Wait for element to be clickable");
            WebDriverWait wait = new WebDriverWait(driver, $seconds);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        } catch (NoSuchElementException e) {
            logWrite.info("Element not found!");
            return false;
        } catch (Exception e){
            return false;
        }
    }

    public Boolean waitForElementVisibilityByXpath(By xpath, int $seconds) {
        try {
            System.out.println("Waiting... " + 3 + " For element " + xpath);
            WebDriverWait wait = new WebDriverWait(driver, $seconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("!!!!Element was not found!!!");
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean waitForElementInvisibility(WebElement locator, int $seconds) {
        try {
            System.out.println("Waiting... " + $seconds + " For element " + locator);
            WebDriverWait wait = new WebDriverWait(driver, $seconds);
            wait.until(ExpectedConditions.invisibilityOf(locator));
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("!!!!Element was not found!!!");
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void openPage(String $page) {
        driver.get($page);
        new WebDriverWait(driver, 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).
                        executeScript("return document.readyState").equals("complete"));
    }

    public void switchToiFrame(String locator) {
        driver.switchTo().frame(locator);
        new WebDriverWait(driver, 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).
                        executeScript("return document.readyState").equals("complete"));
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
        new WebDriverWait(driver, 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).
                        executeScript("return document.readyState").equals("complete"));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

}
