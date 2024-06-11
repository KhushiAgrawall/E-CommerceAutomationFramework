package com.khushiagrawal.WebAutoFrameWork.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    public int getItemCountInCart() {
        // You may need to locate a web element that represents the item count in the actual cart.
        WebElement itemCountElement = driver.findElement(By.xpath("xpath-to-cart-item-count"));
        return Integer.parseInt(itemCountElement.getText().trim());
    }
}

