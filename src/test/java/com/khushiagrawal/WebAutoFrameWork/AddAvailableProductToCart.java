package com.khushiagrawal.WebAutoFrameWork;

import com.khushiagrawal.WebAutoFrameWork.actions.SearchContentAction;
import com.khushiagrawal.WebAutoFrameWork.modals.CartModal;
import com.khushiagrawal.WebAutoFrameWork.pages.HomePage;
import com.khushiagrawal.WebAutoFrameWork.pages.ProductDetailsPage;
import com.khushiagrawal.WebAutoFrameWork.pages.ViewSearchResultPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddAvailableProductToCart  extends BaseTest{
    private static final Logger logger = LogManager.getLogger(AddAvailableProductToCart.class);
    private static final Logger searchLogger= LogManager.getLogger("SearchLogger");
    private static final Logger cartLogger=LogManager.getLogger("CartLogger");
    @Test(testName = "Verify that user adds product to cart when product in stock", description = "Verifies that a user is able to successfully add a product to the cart when it is available for purchase.")
    public void verifyUserIsAbleToAddProductToCart(){
        logger.info("Executing verifyUserIsAbleToAddProductToCart: Verifying that a user can add a product to the cart when product is in stock");
        SearchContentAction searchContentAction=SearchContentAction.builder().build().stellTop();
        searchLogger.info("Search Content : {}",searchContentAction.getInput());
        HomePage homePage=new HomePage(getWebDriver());
        homePage.getHeader().clickSearchBtn().searchProduct(searchContentAction.getInput());

        ViewSearchResultPage searchResultPage=new ViewSearchResultPage(getWebDriver());
        ProductDetailsPage productDetailsPage = searchResultPage.clickToViewProductByName();

        cartLogger.debug("Navigated to Product Details Page");
        CartModal cartModal=new CartModal(getWebDriver());
        if(!productDetailsPage.isProductSoldOut()){
            productDetailsPage.clickAddToCart();
            Assert.assertTrue(cartModal.getSuccessMessage().contains("Item added to your cart"));
            cartLogger.info("Product Added to the Cart");
        }else{
            Assert.fail("Product is Out of Stock");
            cartLogger.error("Product is Out of Stock");
        }
    }
}
