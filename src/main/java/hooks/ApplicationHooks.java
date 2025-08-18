package hooks;

import Driver.WebDriverFactory;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;
import utils.ExtentSparkReporterManager;
import utils.LogInitializer;
import utils.ScreenshotUtils;

import java.io.File;
import java.time.Duration;
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
//    @Before(value="@skip",order=0)
//    public void skipTaggedScenario(Scenario scenario) {
//        if (scenario.getSourceTagNames().contains("@Skip")) {
//            throw new SkipException("Skipping scenario: " + scenario.getName());
//        }
//    }


    @Before(order = 0)
    public void loadConfig() {
        configReader = new ConfigReader();
        prop = configReader.init_prop();
    }
    @Before(order = 1)
    public void launchBrowser(Scenario scenario) {
        // Try to get browser from system property or TestNG parameter
        // If not set, fall back to the Config.properties value
        String browserName = System.getProperty("browser", prop.getProperty("browser"));

        // Initialize the driver
        WebDriverFactory.init_Driver(browserName);

        // Set implicit wait
        WebDriverFactory.getDriver()
                .manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        // Create Extent Report entry for this scenario
        ExtentTest test = ExtentSparkReporterManager.getInstance()
                .createTest("Scenario: " + scenario.getName());
        extentTest.set(test);
        extentTest.get().log(Status.INFO,
                "Started scenario: " + scenario.getName() + " on browser: " + browserName);
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