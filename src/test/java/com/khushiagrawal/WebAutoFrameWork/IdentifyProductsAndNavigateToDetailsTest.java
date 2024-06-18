package com.khushiagrawal.WebAutoFrameWork;

import com.khushiagrawal.WebAutoFrameWork.actions.SearchContentAction;
import com.khushiagrawal.WebAutoFrameWork.pages.HomePage;
import com.khushiagrawal.WebAutoFrameWork.pages.ProductDetailsPage;
import com.khushiagrawal.WebAutoFrameWork.pages.ViewSearchResultPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IdentifyProductsAndNavigateToDetailsTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(IdentifyProductsAndNavigateToDetailsTest.class);
    private static final Logger searchLogger = LogManager.getLogger("SearchLogger");
    private static final Logger navigationLogger = LogManager.getLogger("NavigationLogger");

    @Test(testName = "Navigate to Search page", description = "User should be able to navigate to the Search Page")
    public void userIsAbleToNavigateToSearchPage() {
        logger.info("Starting test: userIsAbleToNavigateToSearchPage");

        SearchContentAction searchContent = SearchContentAction.builder().build().init();
        searchLogger.info("Initiated search with content: {}", searchContent.getInput());

        HomePage homePage = new HomePage(getWebDriver());
        homePage.getHeader().clickSearchBtn().searchProduct(searchContent.getInput());
        searchLogger.info("Searched for product: {}", searchContent.getInput());

        ViewSearchResultPage searchResultPage = new ViewSearchResultPage(getWebDriver());
        navigationLogger.debug("Navigated to search result page");

        String searchResultHeading = searchResultPage.getSearchResultHeading();
        navigationLogger.debug("Search result heading: {}", searchResultHeading);

        Assert.assertTrue(searchResultHeading.contains("Search results"), "Search result heading verification");
        logger.info("Test userIsAbleToNavigateToSearchPage completed successfully");
    }

    @Test(testName = "Navigate to Search page by Name", description = "User should be able to search a product by Name")
    public void userIsAbleToNavigateToSearchPageByName() {
        logger.info("Starting test: userIsAbleToNavigateToSearchPageByName");

        SearchContentAction searchContent = SearchContentAction.builder().build().stellTop();
        searchLogger.info("Initiated search with content: {}", searchContent.getInput());

        HomePage homePage = new HomePage(getWebDriver());
        homePage.getHeader().clickSearchBtn().searchProduct(searchContent.getInput());
        searchLogger.info("Searched for product: {}", searchContent.getInput());

        ViewSearchResultPage searchResultPage = new ViewSearchResultPage(getWebDriver());
        navigationLogger.debug("Navigated to search result page");

        String productName = searchResultPage.getProductName();
        navigationLogger.debug("First product name in search results: {}", productName);

        Assert.assertTrue(searchContent.getInput().contains(productName), "Search result product name verification");

        ProductDetailsPage productDetailsPage = searchResultPage.clickToViewProductByName();
        navigationLogger.debug("Navigated to product details page for product: {}", productDetailsPage.getProductName());

        Assert.assertTrue(searchContent.getInput().contains(productDetailsPage.getProductName()), "Product details name verification");
        logger.info("Test userIsAbleToNavigateToSearchPageByName completed successfully");
    }
}
