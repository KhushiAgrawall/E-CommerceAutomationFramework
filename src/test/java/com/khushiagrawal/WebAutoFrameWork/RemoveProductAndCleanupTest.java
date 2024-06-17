package com.khushiagrawal.WebAutoFrameWork;

import com.khushiagrawal.WebAutoFrameWork.actions.SearchContentAction;
import com.khushiagrawal.WebAutoFrameWork.modals.CartModal;
import com.khushiagrawal.WebAutoFrameWork.pages.CartPage;
import com.khushiagrawal.WebAutoFrameWork.pages.HomePage;
import com.khushiagrawal.WebAutoFrameWork.pages.ProductDetailsPage;
import com.khushiagrawal.WebAutoFrameWork.pages.ViewSearchResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RemoveProductAndCleanupTest extends BaseTest{
    @Test(testName = "RemoveProductAndCleanupTest", description = "verify the product is removed from the cart and the cart and the clean")
    public void verifyProductIsRemovedFromCart() throws InterruptedException {
        SearchContentAction searchContent=SearchContentAction.builder().build().stellTop();
        HomePage homePage=new HomePage(getWebDriver());
        homePage.getHeader().clickSearchBtn().searchProduct(searchContent.getInput());

        ViewSearchResultPage searchResultPage=new ViewSearchResultPage(getWebDriver());
        ProductDetailsPage productDetailsPage = searchResultPage.clickToViewProductByName();
        CartModal cartModal=new CartModal(getWebDriver());
        CartPage cartPage=new CartPage(getWebDriver());

        if(!productDetailsPage.isProductSoldOut()){
            productDetailsPage.clickAddToCart();
        }else{
            Assert.fail("Product is Out of Stock");
        }

        cartModal.clickAddToCart();
        cartPage.removeProductFromCart(cartPage.getAddedProductName());
        Assert.assertTrue(cartPage.CheckWeatherCartIsEmpty());

    }

}
