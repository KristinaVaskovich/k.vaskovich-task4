package pages;

import elements.BaseElement;

public abstract class BaseForm {
    private BaseElement element;
    private String name;

    public BaseForm(BaseElement element, String name) {
        this.element = element;
        this.name = name;
    }

    public boolean isPageOpen() {
        return element.isDisplayed();
    }
}
