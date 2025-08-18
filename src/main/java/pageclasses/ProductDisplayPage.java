package pageclasses;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDisplayPage extends BasePage {

    @FindBy(id = "add-to-cart-button")
    private WebElement addToCartBtn;

    @FindBy(css = "h1.product-title")
    private WebElement productTitle;

    public ProductDisplayPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart() {
        scrollToElement(addToCartBtn);
        click(addToCartBtn);
        System.out.println("Added product: " + getProductTitle());
    }

    public String getProductTitle() {
        return getText(productTitle);
    }
}
