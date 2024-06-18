package com.khushiagrawal.WebAutoFrameWork;

import com.khushiagrawal.WebAutoFrameWork.actions.SearchContentAction;
import com.khushiagrawal.WebAutoFrameWork.modals.CartModal;
import com.khushiagrawal.WebAutoFrameWork.pages.CartPage;
import com.khushiagrawal.WebAutoFrameWork.pages.HomePage;
import com.khushiagrawal.WebAutoFrameWork.pages.ProductDetailsPage;
import com.khushiagrawal.WebAutoFrameWork.pages.ViewSearchResultPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidateCartContentsTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(ValidateCartContentsTest.class);
    private static final Logger cartLogger = LogManager.getLogger("CartLogger");

    @Test(testName = "able is navigate to cart page", description = "Verifies that the user is navigated to the cart page")
    public void userIsAbleToNavigateToCartPage() {
        logger.info("Starting test: userIsAbleToNavigateToCartPage");

        SearchContentAction searchContent = SearchContentAction.builder().build().stellTop();
        cartLogger.info("Initialized search content: {}", searchContent);

        HomePage homePage = new HomePage(getWebDriver());
        homePage.getHeader().clickSearchBtn().searchProduct(searchContent.getInput());
        cartLogger.debug("Navigated to search results for: {}", searchContent.getInput());

        ViewSearchResultPage viewSearchResultPage = new ViewSearchResultPage(getWebDriver());
        ProductDetailsPage productDetailsPage = viewSearchResultPage.clickToViewProductByName();
        cartLogger.debug("Navigated to product details page");

        CartModal cartModal = new CartModal(getWebDriver());
        CartPage cartPage = new CartPage(getWebDriver());

        if (!productDetailsPage.isProductSoldOut()) {
            cartPage = productDetailsPage.clickAddToCart();
            cartLogger.info("Product added to cart: {}", productDetailsPage.getProductName());
        } else {
            String message = "Product is not available, it is out of stock";
            cartLogger.error(message);
            Assert.fail(message);
        }

        String cartHeading = cartModal.clickAddToCart().getCartHeading();
        cartLogger.debug("Cart heading: {}", cartHeading);
        Assert.assertTrue(cartHeading.contains("Your cart"), "Cart heading verification");

        logger.info("Test userIsAbleToNavigateToCartPage completed successfully");
    }

    @Test(testName = "verify that Product which is added Listed in Cart With Correct Details", description = "Checks that the product added is listed in the cart with the correct details such as name, size, and quantity.")
    public void checkTheProductWhichIsAddedIsListedInCartWithCorrectDetails() {
        logger.info("Starting test: checkTheProductWhichIsAddedIsListedInCartWithCorrectDetails");

        SearchContentAction searchContent = SearchContentAction.builder().build().golfShoes();
        cartLogger.info("Initialized search content: {}", searchContent);

        HomePage homePage = new HomePage(getWebDriver());
        homePage.getHeader().clickSearchBtn().searchProduct(searchContent.getInput());
        cartLogger.debug("Navigated to search results for: {}", searchContent.getInput());

        ViewSearchResultPage viewSearchResultPage = new ViewSearchResultPage(getWebDriver());
        ProductDetailsPage productDetailsPage = viewSearchResultPage.clickToViewProductByName();
        cartLogger.debug("Navigated to product details page");

        CartModal cartModal = new CartModal(getWebDriver());
        CartPage cartPage = new CartPage(getWebDriver());

        if (!productDetailsPage.isProductSoldOut()) {
            cartPage = productDetailsPage.clickAddToCart();
            cartLogger.info("Product added to cart: {}", productDetailsPage.getProductName());
        } else {
            String message = "Product is not available, it is out of stock";
            cartLogger.error(message);
            Assert.fail(message);
        }

        cartModal.clickAddToCart();
        String addedProductName = cartPage.getAddedProductName();
        String productSize = cartPage.getSizeOfAddedProduct();
        int productQuantity = cartPage.getQuantityOfAddedProduct();
        double productPrice = cartPage.getProductAddedPrice();

        cartLogger.debug("Verifying product details in cart");
        cartLogger.debug("Expected Product Name: {}, Actual Product Name: {}", productDetailsPage.getProductName(), addedProductName);
        cartLogger.debug("Expected Size: {}, Actual Size: {}", productDetailsPage.getSizeSelected(), productSize);
        cartLogger.debug("Expected Quantity: {}, Actual Quantity: {}", productDetailsPage.getQuantitySelected(), productQuantity);
        cartLogger.debug("Expected Price: {}, Actual Price: {}", productDetailsPage.getProductPrice(), productPrice);

        Assert.assertTrue(addedProductName.contains(productDetailsPage.getProductName()), "Product name verification");
        Assert.assertTrue(productSize.contains(productDetailsPage.getSizeSelected()), "Product size verification");
        Assert.assertEquals(productQuantity, productDetailsPage.getQuantitySelected(), "Product quantity verification");
        Assert.assertEquals(productPrice, productDetailsPage.getProductPrice(), "Product price verification");

        logger.info("Test checkTheProductWhichIsAddedIsListedInCartWithCorrectDetails completed successfully");
    }
}
