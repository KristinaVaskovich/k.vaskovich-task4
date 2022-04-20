package pages;

import driver.DriverProvider;
import elements.Button;
import elements.TextField;
import org.openqa.selenium.By;
import utils.DriverUtils;

public class AlertsPage extends BaseForm {

    private static Button JSAlertButton = new Button(By.xpath("//button[@onclick='jsAlert()']"), "JSAlertButton");
    private static Button JSConfirmButton = new Button(By.xpath("//button[@onclick='jsConfirm()']"), "JSConfirmButton");
    private static Button JSPromptButton = new Button(By.xpath("//button[@onclick='jsPrompt()']"), "JSPromptButton");
    private static TextField result = new TextField(By.xpath("//p[@id='result']"), "result");
    private static By resultElement = result.getLocator();

    public AlertsPage() {
        super(JSAlertButton, "alertPage");
    }

    public void clickJSAlertButton() {
        JSAlertButton.click();
    }

    public String alertTextDisplayed(){
        String text = DriverUtils.switchToAlert().getText();
        return text;
    }

    public String jsAlertResultDisplayed() {
        DriverUtils.switchToAlert().accept();
        String text = DriverProvider.getDriver().findElement(resultElement).getText();
        return text;
    }

    public String jsConfirmTextDisplayed() {
        JSConfirmButton.click();
        String text = DriverUtils.switchToAlert().getText();
        return text;
    }

    public String jsConfirmResultDisplayed() {
        DriverUtils.switchToAlert().accept();
        String text = DriverProvider.getDriver().findElement(resultElement).getText();
        return text;
    }

    public void clickJSPromptButton() {
        JSPromptButton.click();
    }

    public String jsPromptAlertDisplayed() {
        String text =  DriverUtils.switchToAlert().getText();
        return text;
    }

    public String jsPromptResultDisplayed(String sendKeys) {
        DriverUtils.switchToAlert().sendKeys(sendKeys);
        DriverUtils.switchToAlert().accept();
        String text = DriverProvider.getDriver().findElement(resultElement).getText().substring(13);
        return text;
    }
}
