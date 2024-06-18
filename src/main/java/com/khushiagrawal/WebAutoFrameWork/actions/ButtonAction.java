package com.khushiagrawal.WebAutoFrameWork.actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ButtonAction extends WebActions {
    public ButtonAction(WebDriver webDriver) {
        super(webDriver);
    }

    public static void click(WebElement webElement){
        driverWait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
    }
}
