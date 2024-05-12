package com.khushiagrawal.WebAutoFrameWork;

import com.khushiagrawal.WebAutoFrameWork.actions.SearchContentAction;
import com.khushiagrawal.WebAutoFrameWork.pages.HomePage;
import com.khushiagrawal.WebAutoFrameWork.pages.ProductDetailsPage;
import com.khushiagrawal.WebAutoFrameWork.pages.ViewSearchResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckProductAvailabilityTest extends BaseTest{
    @Test(testName = "verify the Availability of the Product",description = "user is able to verify that product is available")
    public void verifyAvailabilityOfProduct(){
        SearchContentAction searchContent=SearchContentAction.builder().build().stellTop();
        HomePage homePage=new HomePage(getWebDriver());
        homePage.getHeader().clickSearchBtn().searchProduct(searchContent.getInput());
        ViewSearchResultPage searchResultPage=new ViewSearchResultPage(getWebDriver());
        ProductDetailsPage productDetailsPage = searchResultPage.clickToViewProductByIndex(0);
        Assert.assertFalse((productDetailsPage.isProductSoldOut()), "Product is Available");
    }

    @Test(testName = "verify Product Is SoldOut",description = "verify that product is sold out")
    public void verifyProductIsSoldOut() {
        SearchContentAction searchContent = SearchContentAction.builder().build().soldOutProduct();
        HomePage homePage = new HomePage(getWebDriver());
        homePage.getHeader().clickSearchBtn().searchProduct(searchContent.getInput());
        ViewSearchResultPage searchResultPage = new ViewSearchResultPage(getWebDriver());
        ProductDetailsPage productDetailsPage = searchResultPage.clickToViewProductByIndex(0);
        Assert.assertTrue((productDetailsPage.isProductSoldOut()), "Product is Out of Stock");
    }
}
