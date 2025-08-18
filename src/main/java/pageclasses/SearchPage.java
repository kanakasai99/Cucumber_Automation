package pageclasses;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
    private static final Logger log = Logger.getLogger(LoginPage.class);
    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("LoginPage initialized");
    }

    @FindBy(xpath = "//td[text()='Location']")
    private WebElement location;


    @FindBy(xpath = "//td[contains(text(),'Adults per Room')]")
    private WebElement adultPerRoom;

    @FindBy(xpath = "//td[contains(text(),'Room Type')]")
    private WebElement roomType;

    public String getSearchPageTitle() {
        String title = driver.getTitle();
        log.info("Search Page Title: " + title);
        return title;
    }

    public boolean locationDisplayed() {
        boolean exists = location.isDisplayed();
        log.info("Forgot Password Link Displayed: " + exists);
        return exists;
    }
    public boolean adultPerRoomDisplayed() {
        boolean exists = adultPerRoom.isDisplayed();
        log.info("Forgot Password Link Displayed: " + exists);
        return exists;
    }
    public boolean roomTypeDisplayed() {
        boolean exists = roomType.isDisplayed();
        log.info("Forgot Password Link Displayed: " + exists);
        return exists;
    }
}
