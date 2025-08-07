package stepDefinitions;

import Driver.WebDriverFactory;
import io.cucumber.java.en.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pageclasses.LoginPage;

public class Login {

    private static final Logger log = Logger.getLogger(Login.class);
    private WebDriver driver;
    private LoginPage loginPage;

    @Given("user is on login page")
    public void user_is_on_login_page() {
        driver = WebDriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        driver.get("https://adactinhotelapp.com/");
        log.info("Navigated to login page");
    }

    @When("user gets the title of the login page")
    public void user_gets_the_title_of_the_login_page() {
        String title = loginPage.getLoginPageTitle();
        log.info("Login page title is: " + title);
    }

    @Then("page title should be {string}")
    public void page_title_should_be(String expectedTitle) {
        String actualTitle = loginPage.getLoginPageTitle();
        log.info("Asserting page title");
        assert actualTitle.equals(expectedTitle);
    }

    @Then("forgot password link should be displayed")
    public void forgot_password_link_should_be_displayed() {
        log.info("Checking if forgot password link is visible");
        assert loginPage.isForgotPasswordLinkExist();
    }

    @When("user enter the username {string}")
    public void user_enter_the_username(String username) {
        loginPage.enterUsername(username);
    }

    @When("user enter the password {string}")
    public void user_enter_the_password(String password) {
        loginPage.enterPassword(password);
    }

    @When("clicks on login button")
    public void clicks_on_login_button() {
        loginPage.clickLogin();
    }
}
