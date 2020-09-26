package net.serenitybdd.screenplay.matchers.statematchers;

import net.serenitybdd.core.pages.WebElementState;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class IsNotSelectedMatcher<T extends WebElementState> extends TypeSafeMatcher<T>{

    @Override
    protected boolean matchesSafely(T element) {
        return !element.isSelected();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("an element that is not selected");
    }

    @Override
    protected void describeMismatchSafely(T item, Description mismatchDescription) {
        mismatchDescription.appendText(WebElementStateDescription.forElement(item)).appendText(" was selected");
    }
}