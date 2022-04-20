package pages;

import driver.DriverProvider;
import elements.Button;
import org.openqa.selenium.*;
import utils.JSScripts;

public class SliderPage extends BaseForm {

    private static Button slider = new Button(By.xpath("//div[@class='sliderContainer']/input"), "slider");

    public SliderPage() {
        super(slider, "pages.SliderPage");
    }

    public void setSliderValue() {
        JavascriptExecutor js = (JavascriptExecutor) DriverProvider.getDriver();
        js.executeScript(JSScripts.SLIDER_SCRIPT, slider.findElement());
    }

    public String getCurrentSliderValue() {
        return slider.findElement().getAttribute("value");
    }
}
