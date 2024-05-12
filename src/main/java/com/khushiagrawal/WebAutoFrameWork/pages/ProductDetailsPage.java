package com.khushiagrawal.WebAutoFrameWork.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends BasePage{
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(className = "product__title")
    private WebElement productName;
    public String getProductName(){
        return webActions.getText(productName);
    }
}
