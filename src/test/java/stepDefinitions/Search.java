package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Driver.WebDriverFactory;
import pageclasses.LoginPage;
import pageclasses.SearchPage;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Search {

    private WebDriver driver;
    private SearchPage searchPage;
    private Map<String, Supplier<Boolean>> elementCheckMap;

    @Given("user is already logged in website")
    public void user_is_already_logged_in_website(DataTable dataTable) {
        List<Map<String, String>> creds = dataTable.asMaps(String.class, String.class);
        String username = creds.get(0).get("username");
        String password = creds.get(0).get("password");

        driver = WebDriverFactory.getDriver();
        driver.get("https://adactinhotelapp.com/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        // Wait until Search page's "Location" element is visible
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("location")));

        // Verify we are on the search page
        Assert.assertTrue("Login failed — Search page not loaded",
                driver.getCurrentUrl().contains("SearchHotel.php"));

        // Initialize SearchPage and element check map here
        searchPage = new SearchPage(driver);
        elementCheckMap = new HashMap<>();
        elementCheckMap.put("Location", searchPage::locationDisplayed);
        elementCheckMap.put("Room Type", searchPage::roomTypeDisplayed);
        elementCheckMap.put("Adults per Room", searchPage::adultPerRoomDisplayed);
    }

    @Given("user is on search page")
    public void user_is_on_search_page() {
        Assert.assertTrue("Location element not found", searchPage.locationDisplayed());
    }

    @When("user gets the title of the search page")
    public void user_gets_the_title_of_the_search_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until title contains some expected text
        wait.until(ExpectedConditions.titleContains("Adactin.com - Search Hotel"));
        String title = searchPage.getSearchPageTitle();
        System.out.println("Search Page title: " + title);
    }

    @Then("search page title should be {string}")
    public void page_title_should_be(String expectedTitle) {
        Assert.assertEquals("Page title mismatch!", expectedTitle, searchPage.getSearchPageTitle());
    }

    @Then("user gets search page content")
    public void user_gets_search_page_content(DataTable dataTable) {
        List<String> expectedContents = dataTable.asList();
        for (String content : expectedContents) {
            Supplier<Boolean> checkMethod = elementCheckMap.get(content);
            Assert.assertNotNull("No check found for: " + content, checkMethod);
            Assert.assertTrue(content + " is not displayed!", checkMethod.get());
        }
    }
}
