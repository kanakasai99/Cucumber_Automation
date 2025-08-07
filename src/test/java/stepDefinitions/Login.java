package stepDefinitions;

import Driver.WebDriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pageclasses.LoginPage;

public class Login {

    private WebDriver driver;
    private LoginPage loginPage;

    @Given("user is on login page")
    public void user_is_on_login_page() {
        driver = WebDriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        driver.get("https://adactinhotelapp.com/");
    }

    @When("user gets the title of the login page")
    public void user_gets_the_title_of_the_login_page() {
       // LoginPage loginPage = new LoginPage(WebDriverFactory.getDriver());
        System.out.println("Page title: " + loginPage.getLoginPageTitle());
    }

    @Then("page title should be {string}")
    public void page_title_should_be(String expectedTitle) {
       // LoginPage loginPage = new LoginPage(WebDriverFactory.getDriver());
        String actualTitle = loginPage.getLoginPageTitle();
        assert actualTitle.equals(expectedTitle);
    }

    @Then("forgot password link should be displayed")
    public void forgot_password_link_should_be_displayed() {
        //LoginPage loginPage = new LoginPage(WebDriverFactory.getDriver());
        assert loginPage.isForgotPasswordLinkExist();
    }

    @When("user enter the username {string}")
    public void user_enter_the_username(String username) {
       // LoginPage loginPage = new LoginPage(WebDriverFactory.getDriver());
        loginPage.enterUsername(username);
    }

    @When("user enter the password {string}")
    public void user_enter_the_password(String password) {
       // LoginPage loginPage = new LoginPage(WebDriverFactory.getDriver());
        loginPage.enterPassword(password);
    }

    @When("clicks on login button")
    public void clicks_on_login_button() {
      //  LoginPage loginPage = new LoginPage(WebDriverFactory.getDriver());
        loginPage.clickLogin();
    }
}
