package pages;

import driver.DriverProvider;
import elements.Button;
import elements.TextField;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import utils.ConfigManagerUtil;
import utils.DriverUtils;
import utils.WaiterUtil;
import java.util.Properties;

public class HoversPage extends BaseForm {

    private static Properties propertyTestData = ConfigManagerUtil.readFile("src/test/resources/testData.properties");
    private static Properties property = ConfigManagerUtil.readFile("src/test/resources/config.properties");
    private static String protocolWithUrl = property.getProperty("protocolWithUrl");
    private static TextField fieldHover = new TextField(By.xpath("//div[@class='example']/p"), "fieldHover");

    private static Button userLabel;
    private static String userLabelLocator = "//div[@class='figure'][%s]/img";
    private static Button userLink;
    private static String userLinkLocator= "//div[@class='figcaption']/a[@href='/users/%s']";
    private static TextField userName;
    private static String userNameLocator = "//*[@id='content']/div/div[%s]/div/h5";

    public HoversPage() {
        super(fieldHover, "pages.HoversPage");
    }

    public void moveToLabelUser(String id) {
        userLabel = new Button(By.xpath(String.format(userLabelLocator, id)), "userLabel");
        Actions actions = new Actions(DriverProvider.getDriver());
        actions.moveToElement(userLabel.findElement()).click().perform();
    }

    public boolean checkNameAndLink(String id) {
        userLink = new Button(By.xpath(String.format(userLinkLocator, id)), "userLink");
        userName = new TextField(By.xpath(String.format(userNameLocator, id)), "userName");
        String text = propertyTestData.getProperty("userText") + id;
        return userLink.isEnabled() && userName.getText().equals(text);
    }

    public void clickUserLink() {
        userLink.click();
        WaiterUtil.waitForOpen();
    }

    public boolean isURLCurrent(String id) {
        String path = propertyTestData.getProperty("userPath") + id;
        String currentUrl = DriverUtils.getCurrentURL();
        String expected = protocolWithUrl + path;
        return currentUrl.equals(expected);
    }
}
