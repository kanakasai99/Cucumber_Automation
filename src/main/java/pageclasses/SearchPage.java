package pageclasses;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver); // Call BasePage constructor
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
        log.info("Location displayed: " + exists);
        return exists;
    }

    public boolean adultPerRoomDisplayed() {
        boolean exists = adultPerRoom.isDisplayed();
        log.info("Adults per room displayed: " + exists);
        return exists;
    }

    public boolean roomTypeDisplayed() {
        boolean exists = roomType.isDisplayed();
        log.info("Room type displayed: " + exists);
        return exists;
    }
}
