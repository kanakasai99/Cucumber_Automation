package utils;

import Driver.WebDriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    // Folder where screenshots will be stored
    private static final String SCREENSHOT_FOLDER =
            System.getProperty("user.dir") + "/test-output/screenshots/";

    /**
     * Captures screenshot and returns RELATIVE path for Extent report linking.
     */
    public static String captureScreenshot(String scenarioName) {
        WebDriver driver = WebDriverFactory.getDriver();
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "screenshot_" + scenarioName + "_" + timestamp + ".png";

        File destFile = new File(SCREENSHOT_FOLDER + fileName);

        try {
            FileUtils.copyFile(src, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Relative path from Extent report HTML (inside test-output/ExtentReport) to screenshots folder
        return "../screenshots/" + fileName;
    }

    public static byte[] getScreenshotBytes() {
        WebDriver driver = WebDriverFactory.getDriver();
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
