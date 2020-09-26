package net.thucydides.samples;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.annotations.TestData;
import net.thucydides.junit.runners.ThucydidesParameterizedRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.Collection;

@RunWith(ThucydidesParameterizedRunner.class)
public class ParametrizedTest {
    @Managed
    public WebDriver webdriver;

    @ManagedPages(defaultUrl = "http://www.yandex.com")
    public Pages pages;

    @Steps
    public UserSteps user;

    @TestData
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"euro"},
                {"olympic"}
        });
    }

    private String request;

    public ParametrizedTest(String request) {
        this.request = request;
    }

    @Test
    public void searchForNews() {
        user.entersRequest(request);
        user.startsSearch();
    }

    public static class UserSteps {
        public void entersRequest(String request) {}
        public void startsSearch() {}
    }
}