package com.khushiagrawal.WebAutoFrameWork;

import com.khushiagrawal.WebAutoFrameWork.models.UserModel;
import com.khushiagrawal.WebAutoFrameWork.pages.HomePage;
import com.khushiagrawal.WebAutoFrameWork.pages.accounts.LoginPage;
import com.khushiagrawal.WebAutoFrameWork.pages.accounts.ProfilePage;
import com.khushiagrawal.WebAutoFrameWork.pages.accounts.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserRegistrationTest extends BaseTest{
    @Test(testName = "Verify That The User Registration Is Successful",description = "Verify that a new user is able to register on the website by creating an account and accessing their profile page.")
    public void VerifyThatTheUserRegistrationIsSuccessful(){
        //arrange
        UserModel user= UserModel.builder().build().init();
        HomePage homePage=new HomePage(getWebDriver());
        LoginPage loginPage = homePage.getHeader().navToLoginPage();
        //act
        loginPage.navToRegisterationPage().createAccount(user);
        ProfilePage profilePage = homePage.getHeader().navToProfilePage();
        //assert
        String accountDetails= profilePage.getAccountDetails();
        Assert.assertTrue(accountDetails.contains(user.getFirst_name()));
    }
    @Test(testName = "Registration Fails With Empty Email", description = "Verifies that the user is unable to register without providing an email address by attempting to create an account with an empty email field.")
    public void verifyThatTheUserIsNotAbleToRegisterWithEmptyEmail(){
        //arrange
        UserModel user= UserModel.builder().build().userWithoutEmail();
        HomePage homePage=new HomePage(getWebDriver());
        LoginPage loginPage = homePage.getHeader().navToLoginPage();
        RegistrationPage registrationPage=new RegistrationPage(getWebDriver());
        //act
        loginPage.navToRegisterationPage().createAccount(user);
        //assert
        String errorMessage = registrationPage.errorMessage();
        Assert.assertTrue(errorMessage.contains("Email cannot be blank"));

    }

    @Test(testName = "Registration Fails With Empty Password", description = "Validates that the user is prevented from registering without providing a password by attempting to create an account with an empty password field.")
    public void verifyThatTheUserIsNotAbleToRegisterWithEmptyPassword(){
        //arrange
        UserModel user= UserModel.builder().build().userWithoutPassword();
        HomePage homePage=new HomePage(getWebDriver());
        LoginPage loginPage = homePage.getHeader().navToLoginPage();
        RegistrationPage registrationPage=new RegistrationPage(getWebDriver());
        //act
        loginPage.navToRegisterationPage().createAccount(user);
        //assert
        String errorMessage = registrationPage.errorMessage();
        Assert.assertTrue(errorMessage.contains("Password cannot be blank"));
    }
}
