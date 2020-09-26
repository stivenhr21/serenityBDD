package net.thucydides.core.reports.history;

import org.joda.time.DateTime;

public class SystemDateProvider implements DateProvider {
    @Override
    public DateTime getCurrentTime() {
        return DateTime.now();
    }
}
