package stepDefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pageclasses.HomePage;
import pageclasses.ProductDisplayPage;
import pageclasses.CartPage;
import Driver.WebDriverFactory;
import org.openqa.selenium.WebDriver;

public class AddToCartSteps {

    WebDriver driver = WebDriverFactory.getDriver();
    HomePage homePage;
    ProductDisplayPage productPage;
    CartPage cartPage;

    @Given("I am on the Home Page")
    public void i_am_on_the_home_page() {
        homePage = new HomePage(driver);
        homePage.navigateTo("https://www.barnesandnoble.com/");
    }

    @When("I add the following products to my cart:")
    public void i_add_the_following_products_to_my_cart(io.cucumber.datatable.DataTable dataTable) {
        for (String product : dataTable.asList()) {
            homePage.searchProduct(product);
            productPage = new ProductDisplayPage(driver);
            productPage.addToCart();
        }
        cartPage = new CartPage(driver);
    }

    @Then("my cart should contain {int} items")
    public void my_cart_should_contain_items(Integer expectedCount) {
        Assert.assertEquals(cartPage.getCartItemCount(), expectedCount.intValue(),
                "Cart item count does not match!");
    }

    @Then("I should see a free LEGO product added")
    public void i_should_see_a_free_lego_product_added() {
        Assert.assertTrue(cartPage.isFreeLegoProductDisplayed(),
                "Free LEGO product was not added to the cart!");
    }
}
