package com.khushiagrawal.WebAutoFrameWork.pages;

import com.khushiagrawal.WebAutoFrameWork.actions.ButtonAction;
import com.khushiagrawal.WebAutoFrameWork.actions.TextBox;
import com.khushiagrawal.WebAutoFrameWork.actions.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
  protected WebDriver driver;
    protected ButtonAction buttonActions;
    protected TextBox textBox;
    protected WebActions webActions;
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        this.buttonActions=new ButtonAction(driver);
        this.textBox=new TextBox(driver);
        this.webActions=new WebActions(driver);
    }


}
