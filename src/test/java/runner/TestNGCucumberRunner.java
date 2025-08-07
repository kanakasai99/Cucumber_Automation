package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/Features",
        glue = {"stepDefinitions", "hooks"},
        plugin = {
                "pretty",
                "json:target/cucumber-reports/Cucumber.json",
                "html:target/cucumber-reports/Cucumber.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)
public class TestNGCucumberRunner extends AbstractTestNGCucumberTests {
}
