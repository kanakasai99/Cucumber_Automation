package hooks;

import Driver.WebDriverFactory;
import com.aventstack.extentreports.service.ExtentService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.ConfigReader;

import java.util.Properties;

public class ApplicationHooks {

    private static final Logger log = Logger.getLogger(ApplicationHooks.class);
    private ConfigReader configReader;
    private Properties prop;

    @Before(order = 0)
    public void getProperty() {
        configReader = new ConfigReader();
        prop = configReader.init_prop();
        ExtentService.getInstance(); // Auto-load config
        ExtentService.getInstance().setSystemInfo("QA Engineer", "Sai");
        log.info("Config properties loaded and Extent report initialized");
    }

    @Before(order = 1)
    public void launchBrowser() {
        String browserName = prop.getProperty("browser");
        WebDriverFactory.init_Driver(browserName);
        log.info("Launched browser: " + browserName);
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) WebDriverFactory.getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName().replaceAll(" ", "_"));
                log.warn("Scenario failed. Screenshot attached: " + scenario.getName());
            } catch (Exception e) {
                log.error("Failed to capture screenshot: " + e.getMessage());
            }
        } else {
            log.info("Scenario passed: " + scenario.getName());
        }
    }

    @After(order = 0)
    public void quitBrowser() {
        WebDriverFactory.quitDriver();
        log.info("Browser session ended");
    }
}
