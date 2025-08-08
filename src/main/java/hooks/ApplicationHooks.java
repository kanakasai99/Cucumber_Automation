package hooks;

import Driver.WebDriverFactory;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.ConfigReader;
import utils.ExtentSparkReporterManager;
import utils.LogInitializer;
import utils.ScreenshotUtils;

import java.io.File;
import java.util.Properties;

public class ApplicationHooks {

    private ConfigReader configReader;
    private Properties prop;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @BeforeAll
    public static void setupReport() {
        LogInitializer.initializeLogger();
        ExtentSparkReporterManager.getInstance(); // Initialize Extent report
    }

    @Before(order = 0)
    public void loadConfig() {
        configReader = new ConfigReader();
        prop = configReader.init_prop();
    }

    @Before(order = 1)
    public void launchBrowser(Scenario scenario) {
        String browserName = prop.getProperty("browser");
        WebDriverFactory.init_Driver(browserName);

        ExtentTest test = ExtentSparkReporterManager.getInstance()
                .createTest("Scenario: " + scenario.getName());
        extentTest.set(test);
        extentTest.get().log(Status.INFO, "Started scenario: " + scenario.getName());
    }

    @After(order = 1)
    public void captureResult(Scenario scenario) {
        String scenarioName = scenario.getName().replaceAll(" ", "_");

        if (scenario.isFailed()) {
            String screenshotPath = ScreenshotUtils.captureScreenshot(scenarioName);
            String relPath = "../screenshots/" + new File(screenshotPath).getName();

            extentTest.get().fail("Scenario failed: " + scenario.getName());
            extentTest.get().addScreenCaptureFromPath(relPath);

            byte[] screenshotBytes = ((TakesScreenshot) WebDriverFactory.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotBytes, "image/png", "Failure Screenshot");
        } else {
            extentTest.get().pass("Scenario passed: " + scenario.getName());
        }
    }

    @After(order = 0)
    public void closeBrowser() {
        WebDriverFactory.quitDriver();
    }

    @AfterAll
    public static void tearDownReport() {
        ExtentSparkReporterManager.getInstance().flush();
    }
}
