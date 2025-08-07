package Driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class WebDriverFactory {
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    // Make initialization more robust
    public static void init_Driver(String browser) {
        if (getDriver() == null) {
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                tlDriver.set(new ChromeDriver());
            } else if (browser.equalsIgnoreCase("edge")) {
                WebDriverManager.edgedriver().setup();
                tlDriver.set(new EdgeDriver());
            } else {
                throw new IllegalArgumentException("Browser not supported: " + browser);
            }
            getDriver().manage().window().maximize();
        }
    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            tlDriver.remove();
        }
    }
}