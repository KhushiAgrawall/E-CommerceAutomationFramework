package com.khushiagrawal.WebAutoFrameWork;

import com.khushiagrawal.WebAutoFrameWork.actions.SearchContentAction;
import com.khushiagrawal.WebAutoFrameWork.models.CartModel;
import com.khushiagrawal.WebAutoFrameWork.pages.CartPage;
import com.khushiagrawal.WebAutoFrameWork.pages.HomePage;
import com.khushiagrawal.WebAutoFrameWork.pages.ProductDetailsPage;
import com.khushiagrawal.WebAutoFrameWork.pages.ViewSearchResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidateCartContentsTest extends BaseTest{
    @Test(testName = "able is navigate to cart page",description = "Verifies that the user is navigated to the cart page")
    public void userIsAbleToNavigateToCartPage(){
        SearchContentAction searchContent=SearchContentAction.builder().build().stellTop();
        HomePage homePage=new HomePage(getWebDriver());
        homePage.getHeader().clickSearchBtn().searchProduct(searchContent.getInput());
        ViewSearchResultPage viewSearchResultPage=new ViewSearchResultPage(getWebDriver());
        ProductDetailsPage productDetailsPage = viewSearchResultPage.clickToViewProductByName();
        CartModel cartModel=new CartModel(getWebDriver());
        CartPage cartPage=new CartPage(getWebDriver());
        if(!productDetailsPage.isProductSoldOut()){
            cartPage = productDetailsPage.clickAddToCart();
        }else{
            Assert.fail("Product is not available, it is out of stock");
        }
        String cartHeading = cartModel.clickAddToCart().getCartHeading();
        Assert.assertTrue(cartHeading.contains("Your cart"));
    }


    @Test(testName = "verify that Product which is added Listed in Cart With Correct Details", description = "Checks that the product added is listed in the cart with the correct details such as name, size, and quantity.")
    public void checkTheProductWhichIsAddedIsListedInCartWithCorrectDetails() {
        SearchContentAction searchContent=SearchContentAction.builder().build().golfShoes();
        HomePage homePage=new HomePage(getWebDriver());
        homePage.getHeader().clickSearchBtn().searchProduct(searchContent.getInput());
        ViewSearchResultPage viewSearchResultPage=new ViewSearchResultPage(getWebDriver());
        ProductDetailsPage productDetailsPage = viewSearchResultPage.clickToViewProductByName();
        CartModel cartModel=new CartModel(getWebDriver());
        CartPage cartPage=new CartPage(getWebDriver());
        if(!productDetailsPage.isProductSoldOut()){
            cartPage = productDetailsPage.clickAddToCart();

        }else{
            Assert.fail("Product is not available, it is out of stock");
        }
        cartModel.clickAddToCart();
        Assert.assertTrue(cartPage.getAddedProductName().contains(productDetailsPage.getProductName()));
        Assert.assertTrue((cartPage.getSizeOfAddedProduct().contains(productDetailsPage.getSizeSelected())));
        Assert.assertEquals(cartPage.getQuantityOfAddedProduct(),productDetailsPage.getQuantitySelected());
        Assert.assertEquals(cartPage.getProductAddedPrice(),productDetailsPage.getProductPrice());

    }
}
