package pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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
        return driver.getTitle();
    }

    public boolean isForgotPasswordLinkExist() {
        return forgotPasswordLink.isDisplayed();
    }

    public void enterUsername(String uname) {
        username.clear();
        username.sendKeys(uname);
    }

    public void enterPassword(String pwd) {
        password.clear();
        password.sendKeys(pwd);
    }

    public void clickLogin() {
        loginBtn.click();
    }
}
