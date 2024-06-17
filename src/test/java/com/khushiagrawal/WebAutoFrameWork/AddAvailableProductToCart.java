package com.khushiagrawal.WebAutoFrameWork;

import com.khushiagrawal.WebAutoFrameWork.actions.SearchContentAction;
import com.khushiagrawal.WebAutoFrameWork.pages.HomePage;
import com.khushiagrawal.WebAutoFrameWork.pages.ProductDetailsPage;
import com.khushiagrawal.WebAutoFrameWork.pages.ViewSearchResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddAvailableProductToCart  extends BaseTest{
    @Test(testName = "Verify that user adds product to cart when product in stock", description = "Verifies that a user is able to successfully add a product to the cart when it is available for purchase.")
    public void verifyUserIsAbleToAddProductToCart(){
        SearchContentAction searchContentAction=SearchContentAction.builder().build().stellTop();
        HomePage homePage=new HomePage(getWebDriver());
        homePage.getHeader().clickSearchBtn().searchProduct(searchContentAction.getInput());

        ViewSearchResultPage searchResultPage=new ViewSearchResultPage(getWebDriver());
        ProductDetailsPage productDetailsPage = searchResultPage.clickToViewProductByName();

        if(!productDetailsPage.isProductSoldOut()){
            productDetailsPage.clickAddToCart();
            System.out.println("Item added to your cart");
        }else{
            Assert.fail("Product Out of Stock");
        }
    }
}
