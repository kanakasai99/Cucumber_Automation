package utils;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogInitializer {
    public static void initializeLogger() {
        PropertyConfigurator.configure("src/test/resources/log4j.properties");


        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String logFileName = "logs/automation_" + timestamp + ".log";

        try {
            // Ensure logs directory exists
            new File("logs").mkdirs();

            FileAppender fileAppender = new FileAppender();
            fileAppender.setName("dynamicFileAppender");
            fileAppender.setFile(logFileName);
            fileAppender.setLayout(new org.apache.log4j.PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n"));
            fileAppender.setAppend(true);
            fileAppender.activateOptions();

            Logger.getRootLogger().addAppender(fileAppender);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
