package net.thucydides.core;

import net.serenitybdd.core.SerenityListeners;
import net.thucydides.core.webdriver.Configuration;

/** @deprecated Use SerenityListeners instead
 *
 */
@Deprecated
public class ThucydidesListeners extends SerenityListeners {
    public ThucydidesListeners(Configuration systemConfiguration) {
        super(systemConfiguration);
    }
}
