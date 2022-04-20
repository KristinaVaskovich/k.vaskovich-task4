package pages;

import driver.DriverProvider;
import elements.TextField;
import org.openqa.selenium.By;
import utils.ConfigManagerUtil;
import java.util.Properties;

public class BasicAuthorizationPage extends BaseForm {

    private static Properties propertyTestData = ConfigManagerUtil.readFile("src/test/resources/testData.properties");
    private static String congratulationText = propertyTestData.getProperty("congratulations");
    private static TextField textWithCongratulations = new TextField(By.xpath("//*[@id='content']/div/p"), "textWithCongratulations");
    private By textWithCongratulationsLocator = textWithCongratulations.getLocator();

    public BasicAuthorizationPage() {
        super(textWithCongratulations, "basicAuthorizationPage");
    }

    public boolean isTextDisplayed() {
        String text = DriverProvider.getDriver().findElement(textWithCongratulationsLocator).getText();
        return text.equals(congratulationText);
    }
}
