package pageclasses;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(id = "searchBarBN")   // Adjust locator based on Barnes & Noble actual DOM
    private WebElement searchBox;

    @FindBy(css = "button.icon-search-2")  // Adjust locator if button differs
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void searchProduct(String productName) {
        type(searchBox, productName);
        click(searchButton);
    }
}
