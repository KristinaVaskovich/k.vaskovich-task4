package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.ConfigManagerUtil;
import utils.DriverUtils;
import utils.WaiterUtil;
import java.util.Properties;

public class TestHerokuappCom extends BaseTest {

    private static Properties property = ConfigManagerUtil.readFile("src/test/resources/config.properties");
    private static String url = property.getProperty("url");
    private static String protocol = property.getProperty("protocol");
    private static String protocolWithUrl = property.getProperty("protocolWithUrl");
    private static Properties propertyTestData = ConfigManagerUtil.readFile("src/test/resources/testData.properties");
    private static String userName = propertyTestData.getProperty("userName");
    private static String password = propertyTestData.getProperty("password");
    private static String pathForBasicAuth = property.getProperty("pathForBasic_Auth");
    private static String pathAlerts = property.getProperty("pathAlerts");
    private static String pathFrame = property.getProperty("pathFrame");
    private static String pathHovers = property.getProperty("pathHovers");
    private static String pathSlider = property.getProperty("pathSlider");
    private static String sliderValue = propertyTestData.getProperty("sliderValue");
    private static String iAmJavaScriptAlertText = "I am a JS Alert";
    private static String successfullyClicked = "You successfully clicked an alert";
    private static String jsConfirm = "I am a JS Confirm";
    private static String jsPrompt = "I am a JS prompt";
    private static String clickOK = "You clicked: Ok";
    private static String enteredTextToAlert = "Hello!";

    @Test
    public void authorizationTest() {
        BasicAuthorizationPage basicAuthorizationPage = new BasicAuthorizationPage();
        logger.info("start authorizationTest");
        String URL = protocol + userName + ":" + password + "@" + url + pathForBasicAuth;
        DriverUtils.openURL(URL);
        logger.info("Navigate to pages.BasicAuthorizationPage is successful");
        WaiterUtil.waitForOpen();
        Assert.assertTrue(basicAuthorizationPage.isTextDisplayed(), "Text isn't display");
        logger.info("Pass basic authorization test");
    }

    @Test
    public void alertsTest() {
        AlertsPage alertsPage = new AlertsPage();
        logger.info("start alertsTest");
        DriverUtils.openURL(protocolWithUrl + pathAlerts);
        logger.info("Alert page is open");
        WaiterUtil.waitForOpen();
        Assert.assertTrue(alertsPage.isPageOpen(), "Alerts page isn't open");
        alertsPage.clickJSAlertButton();
        Assert.assertEquals(alertsPage.alertTextDisplayed(), iAmJavaScriptAlertText, "JS alert text isn't display");
        Assert.assertEquals(alertsPage.jsAlertResultDisplayed(), successfullyClicked, "JS alert isn't close");
        Assert.assertEquals(alertsPage.jsConfirmTextDisplayed(), jsConfirm, "Confirm text isn't display");
        Assert.assertEquals(alertsPage.jsConfirmResultDisplayed(), clickOK, "Confirm alert isn't close");
        alertsPage.clickJSPromptButton();
        Assert.assertEquals(alertsPage.jsPromptAlertDisplayed(), jsPrompt, "Alert prompt isn't display");
        Assert.assertEquals(alertsPage.jsPromptResultDisplayed(enteredTextToAlert), enteredTextToAlert, "Prompt text isn't display");
        logger.info("Pass alertsTest");
    }

    @Test
    public void sliderTest() {
        SliderPage sliderPage = new SliderPage();
        logger.info("start sliderTest");
        DriverUtils.openURL(protocolWithUrl + pathSlider);
        WaiterUtil.waitForOpen();
        Assert.assertTrue(sliderPage.isPageOpen(), "Slider page isn't open");
        logger.info("Slider page is open");
        sliderPage.setSliderValue();
        Assert.assertEquals(sliderPage.getCurrentSliderValue(), sliderValue, "Slider value is  not valid");
        logger.info("Slider value is valid");
        logger.info("Pass sliderTest");
    }

    @Test(dataProvider = "dp")
    public void hoversPageTest(String id) {
        HoversPage hoversPage = new HoversPage();
        logger.info("start hoversPageTest");
        DriverUtils.openURL(protocolWithUrl + pathHovers);
        WaiterUtil.waitForOpen();
        Assert.assertTrue(hoversPage.isPageOpen(), "Hovers page isn't open");
        logger.info("Hovers page is open");
        hoversPage.moveToLabelUser(id);
        Assert.assertTrue(hoversPage.checkNameAndLink(id), "User name isn't correct, Link to profile isn't displayed");
        logger.info("User name is correct, Link to profile is displayed");
        hoversPage.clickUserLink();
        Assert.assertTrue(hoversPage.isURLCurrent(id), "Current URL doesn't  matches link from step 2");
        logger.info("Current URL matches link from step 2");
        DriverUtils.goBack();
        Assert.assertTrue(hoversPage.isPageOpen(), "Hovers page isn't open");
        logger.info("Users page is open");
        logger.info("Pass hoversPageTest");
    }

    @Test
    public void frameTest() {
        IframePage iframePage = new IframePage();
        logger.info("start frameTest");
        DriverUtils.openURL(protocolWithUrl + pathFrame);
        WaiterUtil.waitForOpen();
        Assert.assertTrue(iframePage.isPageOpen(), "Iframe page isn't open");
        logger.info("Frame page is open");
        Assert.assertTrue(iframePage.clickAndCheckButtonLeft(), "Text formatting isn't correct");
        logger.info("Text formatting is correct");
        iframePage.changeFontSize();
        Assert.assertTrue(iframePage.isFormatCorrect(), "Format is not correct");
        logger.info("Text formatting is correct");
        Assert.assertTrue(iframePage.clickNewFile(), "New file isn't open");
        logger.info("Text is empty");
        logger.info("Pass frameTest");
    }

    @DataProvider(name = "dp")
    public Object[][] getData() {
        return new Object[][]{
                {"1"},
                {"3"}
        };
    }
}
