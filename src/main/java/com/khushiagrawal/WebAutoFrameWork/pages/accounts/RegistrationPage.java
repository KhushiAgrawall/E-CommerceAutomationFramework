package com.khushiagrawal.WebAutoFrameWork.pages.accounts;

import com.khushiagrawal.WebAutoFrameWork.models.UserModel;
import com.khushiagrawal.WebAutoFrameWork.pages.BasePage;
import com.khushiagrawal.WebAutoFrameWork.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {
    @FindBy(id = "RegisterForm-FirstName")
    private WebElement firstName;
    @FindBy(id = "RegisterForm-LastName")
    private WebElement lastName;
    @FindBy(id = "RegisterForm-email")
    private WebElement emailID;
    @FindBy(id = "RegisterForm-password")
    private WebElement password;
    @FindBy(xpath = "//*[@id=\"create_customer\"]/button")
    private WebElement createBtn;

    @FindBy(xpath = "//*[@id=\"create_customer\"]/ul/li/a")
    private WebElement errorMessageEle;

    public HomePage createAccount(UserModel user){
        textBox.type(firstName, user.getFirst_name());
        textBox.type(lastName, user.getLast_name());
        textBox.type(emailID, user.getEmailID());
        textBox.type(password, user.getPassword());
        buttonActions.click(createBtn);
        return new HomePage(driver);
    }

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public String errorMessage(){
        return webActions.getText(errorMessageEle);
    }

}
