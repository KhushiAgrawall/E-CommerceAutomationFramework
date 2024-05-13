package com.khushiagrawal.WebAutoFrameWork.modals;

import com.khushiagrawal.WebAutoFrameWork.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.khushiagrawal.WebAutoFrameWork.pages.ViewSearchResultPage;
public class SearchModal extends BasePage {
    public SearchModal(WebDriver driver) {
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
