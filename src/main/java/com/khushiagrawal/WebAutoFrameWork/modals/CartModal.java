package com.khushiagrawal.WebAutoFrameWork.modals;

import com.khushiagrawal.WebAutoFrameWork.pages.BasePage;
import com.khushiagrawal.WebAutoFrameWork.pages.CartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartModal extends BasePage {
    public CartModal(WebDriver driver) {
        super(driver);
    }
    WebDriverWait webDriverWait=new WebDriverWait(driver, Duration.ofSeconds(10));
    @FindBy(xpath = "//*[@id=\"cart-notification\"]/div[1]/h2")
    private WebElement addToCartSuccessMessage;

    @FindBy(xpath = "//*[@id=\"cart-notification-button\"]")
    private WebElement addToCartBtn;

    public CartPage clickAddToCart(){
        buttonActions.click(addToCartBtn);
        return new CartPage(driver);
    }
    public String getSuccessMessage(){
        webDriverWait.until(ExpectedConditions.visibilityOf(addToCartSuccessMessage));
        return webActions.getText(addToCartSuccessMessage);
    }
}
