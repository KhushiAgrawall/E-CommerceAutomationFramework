package com.khushiagrawal.WebAutoFrameWork.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage{
    public CartPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@id=\"shopify-section-template--15328405520605__cart-items\"]/cart-items/div[1]/h1")
    private WebElement cartHeading;

    @FindBy(xpath = "//*[@id=\"CartItem-1\"]/td[2]/a")
    private WebElement productAddedName;

    @FindBy(xpath = "/html[1]/body[1]/main[1]/div[1]/cart-items[1]/form[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/dl[1]/div[2]/dd[1]")
    private WebElement productAddedSize;

    @FindBy(xpath = "//*[@id=\"CartItem-1\"]/td[4]/quantity-input/button[2]")
    private WebElement increaseQuantity;

    @FindBy(xpath = "//input[@id='Quantity-1']")
    private WebElement productAddedQuantity;

    @FindBy(xpath = "//*[@id=\"CartItem-1\"]/td[5]/div[2]/span")
    private WebElement productAddedPrice;

    public double getProductAddedPrice(){
        String getPrice= webActions.getText(productAddedPrice);
        String numberString = getPrice.replaceAll("[^0-9.]", "");
        return Double.parseDouble(numberString);
    }
    public int getQuantityOfAddedProduct(){
        int quantity=Integer.parseInt(productAddedQuantity.getAttribute("value"));
        return quantity;
    }
    public String getSizeOfAddedProduct(){
        return webActions.getText(productAddedSize).trim();
    }
    public String getAddedProductName(){
        return webActions.getText(productAddedName).trim();
    }
    public String getCartHeading(){
        return webActions.getText(cartHeading);
    }
    public CartPage removeProductFromCart(String productName) {
        WebElement deleteBtn= driver.findElement(By.xpath("//*[@id=\"Remove-1\"]/a"));
        buttonActions.click(deleteBtn);
        return new CartPage(driver);
    }

    public boolean CheckWeatherCartIsEmpty() {
        try {
            WebDriverWait webDriverWait=new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement emptyCartMessage = driver.findElement(By.xpath("//h1[normalize-space()='Your cart is empty']"));
            webDriverWait.until(ExpectedConditions.visibilityOf(emptyCartMessage));
            return emptyCartMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}

