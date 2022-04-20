package pages;

import elements.Button;
import elements.TextField;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utils.ConfigManagerUtil;
import utils.DriverUtils;
import java.util.Properties;

public class IframePage extends BaseForm {

    private static Properties propertyTestData = ConfigManagerUtil.readFile("src/test/resources/testData.properties");
    private static String fontSizeText = propertyTestData.getProperty("fontSize");

    private static TextField textField = new TextField(By.xpath("//*[@id='tinymce']"), "textField");
    private static TextField styleField = new TextField(By.xpath("//*[@id='tinymce']/p/span"), "styleField");
    private static Button alignLeft = new Button(By.xpath("//button[@aria-label='Align left']"), "alignLeft");
    private static Button format = new Button(By.xpath("//div[@role='menubar']/button[4]"), "format");
    private static Button fontSizes = new Button(By.xpath("//div[@title='Font sizes']/div[@class='tox-collection__item-label']"), "fontSizes");
    private static Button pt8 = new Button(By.xpath("//div[@title='8pt']"), "pt8");
    private static Button file = new Button(By.xpath("//div[@role='menubar']/button[@aria-haspopup='true'][1]"), "file");
    private static Button newDocument = new Button(By.xpath("//div[@title='New document']"), "newDocument");
    private static String frameName = "mce_0_ifr";

    public IframePage() {
        super(alignLeft, "pages.IframePage");
    }

    public boolean clickAndCheckButtonLeft() {
        alignLeft.click();
        return alignLeft.isDisplayed();
    }

    public void changeFontSize() {
        DriverUtils.switchToFrame(frameName);
        int symbols = textField.getText().length();
        for (int i = 0; i < symbols / 2; i++) {
            textField.findElement().sendKeys(Keys.SHIFT, Keys.ARROW_RIGHT);
        }
        DriverUtils.switchToDefaultContent();
        format.click();
        fontSizes.click();
        pt8.click();
    }

    public boolean isFormatCorrect() {
        DriverUtils.switchToFrame(frameName);
        return styleField.getAttribute("style").equals(fontSizeText);
    }

    public boolean clickNewFile() {
        DriverUtils.switchToDefaultContent();
        file.click();
        newDocument.click();
        DriverUtils.switchToFrame(frameName);
        return textField.getText().equals("");
    }
}
