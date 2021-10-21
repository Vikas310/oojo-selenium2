package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.BaseClass;

public class BookSuccessPageObject extends BaseClass {

    public BookSuccessPageObject(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[contains(text(),'We are almost there…')]")
    public WebElement thanksForBookingMessageInput;

    @FindBy(xpath = "//*[contains(@class,'NumberStyle-')]")
    public WebElement bookingNumberInput;

    public WebElement getBookSuccessMessage(){
        return thanksForBookingMessageInput;
    }

    public String getBookingId (){
        return bookingNumberInput.getText();
    }
}
