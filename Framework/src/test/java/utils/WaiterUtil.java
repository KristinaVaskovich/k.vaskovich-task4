package utils;

import driver.DriverProvider;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WaiterUtil {

    private static Properties property = ConfigManagerUtil.readFile("src/test/resources/config.properties");
    private static int seconds = Integer.parseInt(property.getProperty("seconds"));

    public static void waitForOpen() {
        DriverProvider.getDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }
}
