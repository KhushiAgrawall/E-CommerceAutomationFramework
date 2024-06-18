package com.khushiagrawal.WebAutoFrameWork.RegistrationTest;

import com.khushiagrawal.WebAutoFrameWork.models.UserModel;
import com.khushiagrawal.WebAutoFrameWork.pages.HomePage;
import com.khushiagrawal.WebAutoFrameWork.pages.accounts.LoginPage;
import com.khushiagrawal.WebAutoFrameWork.pages.accounts.ProfilePage;
import com.khushiagrawal.WebAutoFrameWork.pages.accounts.RegistrationPage;
import com.khushiagrawal.WebAutoFrameWork.utils.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserRegistrationTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(UserRegistrationTest.class);
    private static final Logger registrationLogger = LogManager.getLogger("RegistrationLogger");

    @Test(testName = "Verify That The User Registration Is Successful", description = "Verify that a new user is able to register on the website by creating an account and accessing their profile page.")
    public void VerifyThatTheUserRegistrationIsSuccessful() {
        logger.info("Starting test: VerifyThatTheUserRegistrationIsSuccessful");

        // arrange
        UserModel user = UserModel.builder().build().init();
        registrationLogger.info("Initialized user: {}", user);

        HomePage homePage = new HomePage(getWebDriver());
        LoginPage loginPage = homePage.getHeader().navToLoginPage();
        registrationLogger.debug("Navigated to login page");

        // act
        loginPage.navToRegisterationPage().createAccount(user);
        registrationLogger.info("Created account for user: {}", user);

        ProfilePage profilePage = homePage.getHeader().navToProfilePage();
        registrationLogger.debug("Navigated to profile page");

        // assert
        String accountDetails = profilePage.getAccountDetails();
        registrationLogger.debug("Retrieved account details: {}", accountDetails);
        Assert.assertTrue(accountDetails.contains(user.getFirst_name()), "Account details verification");

        logger.info("Test VerifyThatTheUserRegistrationIsSuccessful completed successfully");
    }

    @Test(testName = "Registration Fails With Empty Email", description = "Verifies that the user is unable to register without providing an email address by attempting to create an account with an empty email field.")
    public void verifyThatTheUserIsNotAbleToRegisterWithEmptyEmail() {
        logger.info("Starting test: verifyThatTheUserIsNotAbleToRegisterWithEmptyEmail");

        // arrange
        UserModel user = UserModel.builder().build().userWithoutEmail();
        registrationLogger.info("Initialized user without email: {}", user);

        HomePage homePage = new HomePage(getWebDriver());
        LoginPage loginPage = homePage.getHeader().navToLoginPage();
        registrationLogger.debug("Navigated to login page");

        RegistrationPage registrationPage = new RegistrationPage(getWebDriver());

        // act
        loginPage.navToRegisterationPage().createAccount(user);
        registrationLogger.info("Attempted to create account for user without email");

        // assert
        String errorMessage = registrationPage.errorMessage();
        registrationLogger.debug("Retrieved error message: {}", errorMessage);
        Assert.assertTrue(errorMessage.contains("Email cannot be blank"), "Error message verification");

        logger.info("Test verifyThatTheUserIsNotAbleToRegisterWithEmptyEmail completed successfully");
    }

    @Test(testName = "Registration Fails With Empty Password", description = "Validates that the user is prevented from registering without providing a password by attempting to create an account with an empty password field.")
    public void verifyThatTheUserIsNotAbleToRegisterWithEmptyPassword() {
        logger.info("Starting test: verifyThatTheUserIsNotAbleToRegisterWithEmptyPassword");

        // arrange
        UserModel user = UserModel.builder().build().userWithoutPassword();
        registrationLogger.info("Initialized user without password: {}", user);

        HomePage homePage = new HomePage(getWebDriver());
        LoginPage loginPage = homePage.getHeader().navToLoginPage();
        registrationLogger.debug("Navigated to login page");

        RegistrationPage registrationPage = new RegistrationPage(getWebDriver());

        // act
        loginPage.navToRegisterationPage().createAccount(user);
        registrationLogger.info("Attempted to create account for user without password");

        // assert
        String errorMessage = registrationPage.errorMessage();
        registrationLogger.debug("Retrieved error message: {}", errorMessage);
        Assert.assertTrue(errorMessage.contains("Password cannot be blank"), "Error message verification");

        logger.info("Test verifyThatTheUserIsNotAbleToRegisterWithEmptyPassword completed successfully");
    }
}
