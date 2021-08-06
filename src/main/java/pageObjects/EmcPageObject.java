package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.BaseClass;

public class EmcPageObject extends BaseClass {

    public EmcPageObject(WebDriver driver) {
            super(driver);
        }

        @FindBy(xpath = "//*[@name='login']")
        private WebElement loginInput;

        @FindBy(xpath = "//*[@name='password']")
        private WebElement passwordInput;

        @FindBy(xpath = "//button[@type='submit']")
        private WebElement submitInput;

        @FindBy(xpath = "//div[@class='sk-circle']")
        private WebElement loginSpinnerInput;


        public EmcPageObject fillLogin(String $login) {
            this.waitForElementToBeClickable(submitInput, TIMEOUT_5);
            this.waitForElementVisibility(loginInput, TIMEOUT_10);
            loginInput.sendKeys($login);
            return this;
        }

        public EmcPageObject fillPassword(String $password) {
            this.waitForElementVisibility(passwordInput, TIMEOUT_5);
            passwordInput.sendKeys($password);
            return this;
        }

        public void submitCredentials() {
            submitInput.click();
            this.waitForElementInvisibility(loginSpinnerInput, TIMEOUT_10);
        }
}
