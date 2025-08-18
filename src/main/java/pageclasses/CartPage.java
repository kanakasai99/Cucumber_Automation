package pageclasses;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = ".cart-item")
    private List<WebElement> cartItems;

    @FindBy(xpath = "//div[contains(text(),'Free LEGO Product')]")
    private WebElement freeLegoProduct;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    public boolean isFreeLegoProductDisplayed() {
        return isDisplayed(freeLegoProduct);
    }
}
