package net.serenitybdd.screenplay.actions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebElement;

public class DoubleClickOnTarget implements Interaction {
    private final Target target;

    @Step("{0} clicks on #target")
    public <T extends Actor> void performAs(T theUser) {
        WebElement element = target.resolveFor(theUser);
        BrowseTheWeb.as(theUser).withAction().moveToElement(element).doubleClick().perform();
    }

    public DoubleClickOnTarget(Target target) {
        this.target = target;
    }

}
