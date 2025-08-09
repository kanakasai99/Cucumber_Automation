package stepDefinitions;

import Driver.WebDriverFactory;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageclasses.ContactUsPage;
import utils.ExcelReader;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class ContactUs {

    private WebDriver driver;
    private ContactUsPage contactUsPage;

    @Given("user navigates to contact us page")
    public void user_navigates_to_contact_us_page() {
        driver = WebDriverFactory.getDriver();
        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver is not initialized. Call WebDriverFactory.init_Driver() before this step."
            );
        }
        driver.get("http://www.automationpractice.pl/index.php");
        contactUsPage = new ContactUsPage(driver);
        contactUsPage.clickContactUs();

        // Wait until Contact Us page loads
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.titleContains("Contact us"));
    }

    @When("user fills the form from excel {string} and row {int}")
    public void user_fills_the_form_from_excel_and_row(String sheetName, Integer rowNumber) {
        // Path to Excel file inside src/test/resources
        String filePath = System.getProperty("user.dir") + File.separator +
                "src" + File.separator + "test" + File.separator +
                "resources" + File.separator + "ContactUs.xlsx";

        // Read data from Excel
        List<Map<String, String>> testData = ExcelReader.getData(filePath, sheetName);

        if (rowNumber >= testData.size()) {
            throw new IllegalArgumentException(
                    "Row number " + rowNumber + " is out of bounds for sheet: " + sheetName
            );
        }

        Map<String, String> row = testData.get(rowNumber);

        String subject = row.get("subject heading");
        String email = row.get("email");
        String orderRef = row.get("order reference");
        String message = row.get("message");

        contactUsPage.fillContactForm(subject, email, orderRef, message);
    }

    @When("user clicks on send button")
    public void user_clicks_on_send_button() {
        contactUsPage.clickSend();
    }

    @Then("it shows a success message {string}")
    public void it_shows_a_success_message(String expectedMessage) {
        Assert.assertEquals(
                contactUsPage.getSuccessMessage().trim(),
                expectedMessage.trim(),
                "Success message mismatch"
        );
    }
}
