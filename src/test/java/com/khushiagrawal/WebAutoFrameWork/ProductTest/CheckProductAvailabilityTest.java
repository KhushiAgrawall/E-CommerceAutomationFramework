package com.khushiagrawal.WebAutoFrameWork.ProductTest;

import com.khushiagrawal.WebAutoFrameWork.utils.BaseTest;
import com.khushiagrawal.WebAutoFrameWork.actions.SearchContentAction;
import com.khushiagrawal.WebAutoFrameWork.pages.HomePage;
import com.khushiagrawal.WebAutoFrameWork.pages.ProductDetailsPage;
import com.khushiagrawal.WebAutoFrameWork.pages.ViewSearchResultPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckProductAvailabilityTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(CheckProductAvailabilityTest.class);
    private static final Logger searchLogger = LogManager.getLogger("SearchLogger");
    private static final Logger availabilityLogger = LogManager.getLogger("AvailabilityLogger");

    @Test(testName = "verify the Availability of the Product", description = "User is able to verify that product is available")
    public void verifyAvailabilityOfProduct() {
        logger.info("Starting test: verifyAvailabilityOfProduct");

        SearchContentAction searchContent = SearchContentAction.builder().build().stellTop();
        searchLogger.info("Search content: {}", searchContent.getInput());

        HomePage homePage = new HomePage(getWebDriver());
        homePage.getHeader().clickSearchBtn().searchProduct(searchContent.getInput());
        searchLogger.info("Searching for product: {}", searchContent.getInput());

        ViewSearchResultPage searchResultPage = new ViewSearchResultPage(getWebDriver());
        availabilityLogger.debug("Navigated to search result page");

        ProductDetailsPage productDetailsPage = searchResultPage.clickToViewProductByIndex(0);
        availabilityLogger.debug("Viewing product details for the first result");

        boolean isProductSoldOut = productDetailsPage.isProductSoldOut();
        if (isProductSoldOut) {
            availabilityLogger.warn("Product is sold out");
        } else {
            availabilityLogger.info("Product is available");
        }
        Assert.assertFalse(isProductSoldOut, "Product is available");
    }

    @Test(testName = "verify Product Is SoldOut", description = "Verify that product is sold out")
    public void verifyProductIsSoldOut() {
        logger.info("Starting test: verifyProductIsSoldOut");

        SearchContentAction searchContent = SearchContentAction.builder().build().soldOutProduct();
        searchLogger.info("Search content for sold out product: {}", searchContent.getInput());

        HomePage homePage = new HomePage(getWebDriver());
        homePage.getHeader().clickSearchBtn().searchProduct(searchContent.getInput());
        searchLogger.info("Searching for sold out product: {}", searchContent.getInput());

        ViewSearchResultPage searchResultPage = new ViewSearchResultPage(getWebDriver());
        availabilityLogger.debug("Navigated to search result page");

        ProductDetailsPage productDetailsPage = searchResultPage.clickToViewProductByIndex(0);
        availabilityLogger.debug("Viewing product details for the first result");

        boolean isProductSoldOut = productDetailsPage.isProductSoldOut();
        if (isProductSoldOut) {
            availabilityLogger.info("Product is sold out");
        } else {
            availabilityLogger.warn("Product is available when it should be sold out");
        }
        Assert.assertTrue(isProductSoldOut, "Product is out of stock");
    }
}
