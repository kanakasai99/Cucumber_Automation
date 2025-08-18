package base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;   // Common driver for all page classes
    private static final int DEFAULT_TIMEOUT = 10;

    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize all @FindBy elements
    }

    // -------------------- Wait Utilities --------------------
    protected void waitForVisibility(WebElement element, int timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForClickable(WebElement element, int timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForPageTitle(String title, int timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.titleContains(title));
    }

    // -------------------- Common Actions --------------------
    protected void click(WebElement element) {
        waitForClickable(element, DEFAULT_TIMEOUT);
        element.click();
    }

    protected void type(WebElement element, String text) {
        waitForVisibility(element, DEFAULT_TIMEOUT);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(WebElement element) {
        waitForVisibility(element, DEFAULT_TIMEOUT);
        return element.getText();
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // -------------------- Dropdown Helpers --------------------
    protected void selectByVisibleText(WebElement element, String text) {
        new Select(element).selectByVisibleText(text);
    }

    protected void selectByValue(WebElement element, String value) {
        new Select(element).selectByValue(value);
    }

    protected void selectByIndex(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    // -------------------- JavaScript Helpers --------------------
    protected void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // -------------------- Actions Class Helpers --------------------
    protected void hoverOverElement(WebElement element) {
        new Actions(driver).moveToElement(element).perform();
    }

    protected void dragAndDrop(WebElement source, WebElement target) {
        new Actions(driver).dragAndDrop(source, target).perform();
    }

    // -------------------- Navigation --------------------
    public void navigateTo(String url) {
        driver.get(url);
    }

    protected void switchToWindow(String windowTitle) {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getTitle().equals(windowTitle)) {
                break;
            }
        }
    }
}
