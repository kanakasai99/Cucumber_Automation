package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/*
* this method is used to load the properties from config.properties
 */
public class ConfigReader {

    private Properties prop;
    public Properties init_prop()  {
        prop=new Properties();
        try {
            FileInputStream ip=new FileInputStream("C:\\Users\\Sai\\IdeaProjects\\SeleniumCucumber\\src\\test\\resources\\Config\\Config.properties");
            prop.load(ip);

        } catch (FileNotFoundException e) {
          e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        }
        return prop;
    }
}
