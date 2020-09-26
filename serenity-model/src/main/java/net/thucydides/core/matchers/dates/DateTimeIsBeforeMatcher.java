package net.thucydides.core.matchers.dates;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.joda.time.DateTime;

import static net.thucydides.core.matchers.dates.DateMatcherFormatter.formatted;


class DateTimeIsBeforeMatcher extends TypeSafeMatcher<DateTime> {

    private final DateTime expectedDate;

    public DateTimeIsBeforeMatcher(final DateTime expectedDate) {
        this.expectedDate = expectedDate;
    }

    public boolean matchesSafely(DateTime provided) {
        return provided.isBefore(expectedDate);
    }

    public void describeTo(Description description) {
        description.appendText("a date that is before ");
        description.appendText(formatted(expectedDate));
    }
}
