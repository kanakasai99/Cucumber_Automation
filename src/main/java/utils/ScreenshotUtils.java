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

    private static final String SCREENSHOT_FOLDER = System.getProperty("user.dir") + "/screenshots/";

    public static String captureScreenshot(String scenarioName) {
        WebDriver driver = WebDriverFactory.getDriver();
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "screenshot_" + scenarioName + "_" + timestamp + ".png";
        String fullPath = SCREENSHOT_FOLDER + fileName;

        try {
            FileUtils.copyFile(src, new File(fullPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fullPath;
    }

    public static byte[] getScreenshotBytes() {
        WebDriver driver = WebDriverFactory.getDriver();
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
