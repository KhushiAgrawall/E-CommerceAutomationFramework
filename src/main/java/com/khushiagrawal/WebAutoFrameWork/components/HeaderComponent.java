package com.khushiagrawal.WebAutoFrameWork.components;

import com.khushiagrawal.WebAutoFrameWork.models.SearchModel;
import com.khushiagrawal.WebAutoFrameWork.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderComponent extends BasePage {
    public HeaderComponent(WebDriver driver) {
        super(driver);
    }
    public HeaderComponent getHeader(){
        return new HeaderComponent(driver);
    }
    @FindBy(xpath = "//a[contains(text(),'Products')]")
    private WebElement productsLinkElement;

    @FindBy(xpath = "//*[@id=\"shopify-section-header\"]/sticky-header/header/div/details-modal/details/summary/span")
    private WebElement searchIconElement;
    public SearchModel clickSearchBtn(){
        buttonActions.click(searchIconElement);
        return new SearchModel(driver);
    }

    public void clickProductsLink() {
        WebElement productsLink = driver.findElement(By.id("products-link"));
        productsLink.click();
    }
}
