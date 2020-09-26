package net.thucydides.core;

import net.serenitybdd.core.SerenityListeners;
import net.serenitybdd.core.SerenityReports;
import net.thucydides.core.reports.ReportService;
import net.thucydides.core.webdriver.Configuration;

/** @deprecated Use SerenityListeners instead
 *
 */
public class ThucydidesReports {
    public static ReportService getReportService(Configuration systemConfiguration) {
        return SerenityReports.getReportService(systemConfiguration);
    }

    public static SerenityListeners setupListeners(Configuration systemConfiguration) {
        return SerenityReports.setupListeners(systemConfiguration);
    }
}
