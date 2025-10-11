package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "@test-output/failedrerun.txt", // read failed scenarios from the file
        glue = {"stepDefinitions", "hooks"},
        plugin = {
                "pretty",
                "json:target/cucumber-reports/CucumberRetry.json",
                "html:target/cucumber-reports/CucumberRetry.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true
)
public class FailedTestRunner extends AbstractTestNGCucumberTests {
        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
                return super.scenarios();
        }
}
