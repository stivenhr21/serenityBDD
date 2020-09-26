package net.serenitybdd.core.webdriver.driverproviders;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.webdriver.appium.AppiumConfiguration;
import net.thucydides.core.webdriver.stubs.AndroidWebDriverStub;
import net.thucydides.core.webdriver.stubs.IOSWebDriverStub;
import net.thucydides.core.webdriver.stubs.WebDriverStub;
import org.openqa.selenium.WebDriver;

public class RemoteWebdriverStub {

    public static WebDriver from(EnvironmentVariables environmentVariables) {
        switch (AppiumConfiguration.from(environmentVariables).getTargetPlatform()) {
            case IOS:
                return new IOSWebDriverStub();

            case ANDROID:
                return new AndroidWebDriverStub();

            case NONE:
            default:
                return new WebDriverStub();
        }
    }
}
