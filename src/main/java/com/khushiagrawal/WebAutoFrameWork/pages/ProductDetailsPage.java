package com.khushiagrawal.WebAutoFrameWork.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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
    @FindBy(xpath = "//*[@id=\"product-form-template--15328405717213__main\"]/div/button")
    private WebElement addToCartBtn;
    public CartPage clickAddToCart(){
        buttonActions.click(addToCartBtn);
        return new CartPage(driver);
    }
    @FindBy(xpath = "//span[@class='price-item price-item--regular']")
    private WebElement productPrice;
    public double getProductPrice(){
        String getPrice= webActions.getText(productPrice);
        String numericString = getPrice.replace("Rs. ", "");
        double price=Double.parseDouble(numericString);
        return price;
    }
    @FindBy(xpath = "/html[1]/body[1]/main[1]/section[1]/section[1]/div[1]/div[2]/div[1]/div[3]/quantity-input[1]/input[1]")
    private WebElement quantitySelected;
    public int getQuantitySelected(){
        return Integer.parseInt(quantitySelected.getAttribute("value"));
    }
    public String getSizeSelected() {
        WebElement selectedRadio = null;
        List<WebElement> sizeRadioButtons = driver.findElements(By.name("Size"));

        for (WebElement i : sizeRadioButtons) {
            if (i.isSelected()) {
                selectedRadio = i;
                return i.getAttribute("value");
            }
        }
        return null;
    }
}
