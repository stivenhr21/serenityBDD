package net.thucydides.samples;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.UserStoryCode;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(ThucydidesRunner.class)
@UserStoryCode("US01")
public class SingleTestScenarioWithRuntimeException {
    
    @Managed
    public WebDriver webdriver;

    @ManagedPages(defaultUrl = "classpath:static-site/index.html")
    public Pages pages;
    
    @Steps
    public SampleWebdriverScenarioSteps steps;
        
    @Test
    public void happy_day_scenario() {
        steps.stepThatSucceeds();
        steps.stepThatIsIgnored();
        steps.anotherStepThatSucceeds();
        steps.stepThatFailsWithRuntimeException();
        steps.stepThatShouldBeSkipped();
    }    
}
