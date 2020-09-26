package net.serenitybdd.demo.pages;

import net.thucydides.core.annotations.At;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@At("https://www.google\\..*")
public class GoogleHomePage extends PageObject {

    @FindBy(name="q")
    WebElement query;

    @FindBy(name="btnG")
    WebElement searchButton;
    
    public GoogleHomePage(WebDriver driver) {
        super(driver);
    }

    public void searchFor(String term) {
        query.sendKeys(term);
        searchButton.click();
        waitABit(200);
    }
}
