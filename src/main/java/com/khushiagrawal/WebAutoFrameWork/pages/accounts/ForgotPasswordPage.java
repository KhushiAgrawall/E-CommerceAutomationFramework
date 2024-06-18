package com.khushiagrawal.WebAutoFrameWork.pages.accounts;

import com.khushiagrawal.WebAutoFrameWork.actions.ButtonAction;
import com.khushiagrawal.WebAutoFrameWork.actions.TextBox;
import com.khushiagrawal.WebAutoFrameWork.actions.WebActions;
import com.khushiagrawal.WebAutoFrameWork.models.UserModel;
import com.khushiagrawal.WebAutoFrameWork.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ForgotPasswordPage extends BasePage {

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"recover\"]")
    private WebElement resetPageHeading;

    @FindBy(id = "RecoverEmail")
    private WebElement emailIdTextBox;

    @FindBy(xpath = "//*[@id=\"MainContent\"]/div/div[1]/form/button")
    private WebElement submitBtn;

    public LoginPage forgotPassword(UserModel user){
        TextBox.type(emailIdTextBox, Integer.parseInt(user.getEmailID()));
        ButtonAction.click(submitBtn);
        return new LoginPage(driver);
    }

    public String getResetPasswordPageHeading(){
        return WebActions.getText(resetPageHeading);
    }


    }

