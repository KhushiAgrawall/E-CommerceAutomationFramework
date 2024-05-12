package com.khushiagrawal.WebAutoFrameWork;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.khushiagrawal.WebAutoFrameWork.drivers.DriverManager;
import org.testng.annotations.Test;

public class BaseTest {
    private ThreadLocal<WebDriver> driverThreadLocal=new ThreadLocal<>();
    private final String URL="https://web-playground.ultralesson.com/";

    @BeforeMethod(alwaysRun = true)
    public synchronized void setUp(){
        driverThreadLocal.set(new DriverManager().create());
        launch();
        driverThreadLocal.get().manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public synchronized void tearDown(){
        driverThreadLocal.get().quit();
    }
    protected synchronized void launch(){
        driverThreadLocal.get().get(URL);
    }
    protected synchronized WebDriver getWebDriver(){
        return driverThreadLocal.get();
    }
}
