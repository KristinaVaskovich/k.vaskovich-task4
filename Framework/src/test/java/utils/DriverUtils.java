package utils;

import driver.DriverProvider;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;

public class DriverUtils {

    public static void openURL(String URL) {
        DriverProvider.getDriver().get(URL);
    }

    public static String getCurrentURL() {
        return DriverProvider.getDriver().getCurrentUrl();
    }

    public static Alert switchToAlert() {
        Alert alert = null;
        try {
            alert = DriverProvider.getDriver().switchTo().alert();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
        return alert;
    }

    public static void switchToFrame(String frameName) {
        try {
            DriverProvider.getDriver().switchTo().frame(frameName);
        } catch (NoSuchFrameException e) {
            e.printStackTrace();
        }
    }

    public static void switchToDefaultContent() {
        DriverProvider.getDriver().switchTo().defaultContent();
    }

    public static void goBack() {
        DriverProvider.getDriver().navigate().back();
    }

    public static void deleteCookies() {
        DriverProvider.getDriver().manage().deleteAllCookies();
    }

    public static void close() {
        DriverProvider.getDriver().quit();
    }
}
