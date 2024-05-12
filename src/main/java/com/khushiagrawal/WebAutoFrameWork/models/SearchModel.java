package com.khushiagrawal.WebAutoFrameWork.models;

import com.khushiagrawal.WebAutoFrameWork.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.khushiagrawal.WebAutoFrameWork.pages.ViewSearchResultPage;
public class SearchModel extends BasePage {
    public SearchModel(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "Search-In-Modal")
    private WebElement searchInput;

    public ViewSearchResultPage searchProduct(String input){
        textBox.type(searchInput,input);
        searchInput.submit();
        return new ViewSearchResultPage (driver);
    }

}
