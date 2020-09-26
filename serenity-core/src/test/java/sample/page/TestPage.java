package sample.page;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;
import sample.elements.WebElementFacadeInput;

@DefaultUrl("classpath:static-site/index.html")
public class TestPage extends PageObject {

	@org.openqa.selenium.support.FindBy(css="#firstname")
	public WebElementFacadeInput elementFirst;

	@FindBy(css = "#lastname")
	public WebElementFacadeInput elementLast;

	public TestPage(WebDriver driver) {
		super(driver);
	}

}
