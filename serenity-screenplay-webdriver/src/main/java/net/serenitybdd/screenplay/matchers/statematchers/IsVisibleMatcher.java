package net.serenitybdd.screenplay.matchers.statematchers;

import net.serenitybdd.core.pages.WebElementState;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class IsVisibleMatcher<T extends WebElementState> extends TypeSafeMatcher<T> {

    @Override
    protected boolean matchesSafely(T element) {
        return element.isVisible();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("an element that is visible");
    }

    @Override
    protected void describeMismatchSafely(T item, Description mismatchDescription) {
        mismatchDescription.appendText(WebElementStateDescription.forElement(item));
    }
}