package com.khushiagrawal.WebAutoFrameWork.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebActions {
    private WebDriver driver;
    protected WebDriverWait driverWait;

    public WebActions(WebDriver driver) {
        this.driver = driver;
        this.driverWait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public String getText(WebElement element){
        return driverWait.until(ExpectedConditions.visibilityOf(element)).getText();
    }
}
