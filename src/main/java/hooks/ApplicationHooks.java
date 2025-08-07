package hooks;

import Driver.WebDriverFactory;
import com.aventstack.extentreports.service.ExtentService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.ConfigReader;

import java.util.Properties;

public class ApplicationHooks {

    private ConfigReader configReader;
    private Properties prop;

    @Before(order = 0)
    public void getProperty() {
        configReader = new ConfigReader();
        prop = configReader.init_prop();
        ExtentService.getInstance(); // Auto-load config
        ExtentService.getInstance().setSystemInfo("QA Engineer", "Sai");
    }

    @Before(order = 1)
    public void launchBrowser() {
        String browserName = prop.getProperty("browser");
        WebDriverFactory.init_Driver(browserName);
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) WebDriverFactory.getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName().replaceAll(" ", "_"));
            } catch (Exception e) {
                System.err.println("Failed to take screenshot: " + e.getMessage());
            }
        }
    }

    @After(order = 0)
    public void quitBrowser() {
        WebDriverFactory.quitDriver();
    }
}
