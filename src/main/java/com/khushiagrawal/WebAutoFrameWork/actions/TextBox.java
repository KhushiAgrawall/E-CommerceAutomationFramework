package com.khushiagrawal.WebAutoFrameWork.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TextBox extends WebActions{
    public TextBox(WebDriver driver) {
        super(driver);
    }

    public void type(WebElement element,String input){
        driverWait.until(ExpectedConditions.visibilityOf(element)).sendKeys(input);
    }
    public static void type(WebElement element, int input1) {
        String input = String.valueOf(Math.max(input1, 0));
        driverWait.until(ExpectedConditions.visibilityOf(element)).sendKeys(input);
    }
}
