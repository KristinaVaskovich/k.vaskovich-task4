package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import utils.ConfigManagerUtil;

import java.util.Properties;

public class DriverProvider {

    public static WebDriver driver;
    public static Properties property = ConfigManagerUtil.readFile("src/test/resources/config.properties");
    public static String browser = property.getProperty("browser");

    public static WebDriver getDriver() {
        if (driver == null) {
            switch (browser) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "opera":
                    WebDriverManager.operadriver().setup();
                    driver = new OperaDriver();
                    break;
                default:
                    try {
                        throw new Exception("Unknown browser name");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }
        return driver;
    }
}
