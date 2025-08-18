package base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected WebDriver driver;  // Common driver for all page classes
    protected Logger log;        // Logger for printing messages


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.log = Logger.getLogger(BasePage.class); // Logger with BasePage tag
        PageFactory.initElements(driver, this); // Initialize all @FindBy elements
        log.info("Page initialized"); // Simple log message
    }
}
