package elements;

import driver.DriverProvider;
import org.openqa.selenium.*;

public abstract class BaseElement {

    public By locator;
    public String name;

    public BaseElement(By locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    public By getLocator() {
        return locator;
    }

    public WebElement findElement() {
        return DriverProvider.getDriver().findElement(locator);
    }

    public boolean isDisplayed() {
        return findElement().isDisplayed();
    }

    public boolean isEnabled() {
        return findElement().isEnabled();
    }

    public void click() {
        findElement().click();
    }

    public String getText() {
        return findElement().getText();
    }

    public String getAttribute(String s) {
        return findElement().getAttribute(s);
    }
}
