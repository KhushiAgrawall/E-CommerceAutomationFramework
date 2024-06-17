package com.khushiagrawal.WebAutoFrameWork;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    private final String URL="https://web-playground.ultralesson.com/";

    @BeforeMethod(alwaysRun = true)
    public synchronized void setUp(){
        WebDriver driver = DriverFactory.getInstance().getDriver();
        driver.manage().window().maximize();
        launch(driver);
    }

    @AfterMethod(alwaysRun = true)
    public synchronized void tearDown(){
        DriverFactory.getInstance().quitDriver();
    }
    protected synchronized void launch(WebDriver driver){
        driver.get(URL);
    }

    protected synchronized WebDriver getWebDriver(){

        return DriverFactory.getInstance().getDriver();
    }
}
