package stepDefinitions.Day1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
@Test
public class DynamicDropDown {

    {
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.flipkart.com/");
        driver.manage().window().maximize();

        WebElement searchbox=driver.findElement(By.xpath("//input[@name='q']"));
        searchbox.sendKeys("iqoo");

        List<WebElement> suggestions=driver.findElements(By.xpath("//li[contains(@class,\"_3D0G9\")]"));

        for(WebElement element: suggestions) {
            System.out.println(element.getText());
        }
        driver.quit();
    }

}