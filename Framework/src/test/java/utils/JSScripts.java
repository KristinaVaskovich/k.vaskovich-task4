package utils;

import java.util.Properties;

public class JSScripts {

    private static Properties propertyTestData = ConfigManagerUtil.readFile("src/test/resources/testData.properties");
    private static String sliderValue = propertyTestData.getProperty("sliderValue");
    public static final String SLIDER_SCRIPT ="arguments[0].setAttribute('value', '" + sliderValue + "')";
}
