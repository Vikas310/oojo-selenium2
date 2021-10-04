package selenium;

import common.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class BaseTest {

    public static WebDriver driver;
    String evnExecution = System.getenv("EXECUTION");
    public Logger logWrite = Logger.getLogger("Logger activated");
    protected String targetBrowser = PropertyLoader.loadProperty("TARGET_BROWSER");
    protected String targetExecution = PropertyLoader.loadProperty("DEFAULT_EXECUTION");

    public BaseTest() {
        if (evnExecution != null) {
            targetExecution = evnExecution;
        }
    }

    public WebDriver firefoxDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setBrowserName("firefox");
        if(targetExecution.equals("local")){
            return fireFoxDriver();
        } else if(targetExecution.equals("remote")){
            return getRemoteWebDriver(capabilities);
        } else {
            throw new IllegalStateException("Execution was not found" + targetExecution);
        }
    }

    public WebDriver chromeDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("chrome");
        if(targetExecution.equals("local")){
            return fireFoxDriver();
        } else if(targetExecution.equals("remote")){
            return getRemoteWebDriver(capabilities);
        } else {
            throw new IllegalStateException("Execution was not found" + targetExecution);
        }
    }

    public WebDriver fireFoxDriver() {
        System.setProperty("webdriver.gecko.driver", PropertyLoader.loadProperty("DRIVER_PATH"));
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public WebDriver getDriver() {
        try {
            if (targetBrowser.equals("firefox")) {
                return this.firefoxDriver();
            } else if (targetBrowser.equals("chrome")) {
                return this.chromeDriver();
            } else {
                throw new IllegalStateException("Target browser not supported: " + targetBrowser);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private WebDriver getRemoteWebDriver(DesiredCapabilities capabilities) throws MalformedURLException {
        String baseUrl = PropertyLoader.loadProperty("DEFAULT_SE_HUB_BASE_URL");
        String evnBaseUrl = System.getenv("SE_HUB_BASE_URL");
        if (evnBaseUrl != null) {
            baseUrl = evnBaseUrl;
        }
        return new RemoteWebDriver(
                new URL(new URL(baseUrl).toExternalForm() + "/wd/hub"),
                capabilities
        );
    }

}
