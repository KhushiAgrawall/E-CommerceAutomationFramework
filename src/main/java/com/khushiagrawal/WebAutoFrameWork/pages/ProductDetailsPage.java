package com.khushiagrawal.WebAutoFrameWork.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailsPage extends BasePage{
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(className = "product__title")
    private WebElement productName;
    public String getProductName(){
        return webActions.getText(productName);
    }
    public boolean isProductSoldOut() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            WebElement isSoldOut = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"price-template--15328405717213__main\"]/div/span[2]")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
