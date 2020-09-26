package net.thucydides.junit.integration.pages;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.At;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@At("http://www.apache.org")
public class ApacheHomePage extends PageObject {

    public ApacheHomePage(WebDriver driver) {
        super(driver);
    }
    
    public void clickOnProjects() {
        getDriver().findElement(By.linkText("Projects")).click();
    }

}
