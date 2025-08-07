package pageclasses;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private static final Logger log = Logger.getLogger(LoginPage.class);
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("LoginPage initialized");
    }

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login")
    private WebElement loginBtn;

    @FindBy(linkText = "Forgot Password?")
    private WebElement forgotPasswordLink;

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
}
