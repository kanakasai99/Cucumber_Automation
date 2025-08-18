package pageclasses;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);  // Call BasePage constructor
    }

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login")
    private WebElement loginBtn;

    @FindBy(linkText = "Forgot Password?")
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//td[text()='Location']")
    private WebElement location;

    public String getLoginPageTitle() {
        String title = driver.getTitle();
        log.info("Login Page Title: " + title);
        return title;
    }

    public boolean isForgotPasswordLinkExist() {
        boolean exists = forgotPasswordLink.isDisplayed();
        log.info("Forgot Password Link Displayed: " + exists);
        return exists;
    }

    public void enterUsername(String uname) {
        log.info("Entering username: " + uname);
        username.clear();
        username.sendKeys(uname);
    }

    public void enterPassword(String pwd) {
        log.info("Entering password");
        password.clear();
        password.sendKeys(pwd);
    }

    public void clickLogin() {
        log.info("Clicking on Login Button");
        loginBtn.click();
    }

    public boolean locationDisplayed() {
        boolean exists = location.isDisplayed();
        log.info("Checking location element " + exists);
        return exists;
    }
}
