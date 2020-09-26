package net.serenitybdd.core.webdriver

import net.thucydides.core.webdriver.ThucydidesWebDriverSupport
import spock.lang.Specification


/**
 * Created by john on 12/05/2016.
 */
class WhenConfiguringWebdriverSupport extends Specification {

    def "should be able to define the default driver type"() {
        when:
            ThucydidesWebDriverSupport.initialize("htmlunit")
        then:
            ThucydidesWebDriverSupport.webdriverManager.defaultDriverType == "htmlunit"
    }

    def "the default driver should come from the system configuration if unspecified"() {
        when:
            ThucydidesWebDriverSupport.initialize("")
        then:
            ThucydidesWebDriverSupport.webdriverManager.defaultDriverType == "FIREFOX"
    }

    def "the default driver type can be updated"() {
        when:
            ThucydidesWebDriverSupport.initialize("htmlunit")
            ThucydidesWebDriverSupport.initialize("chrome")
        then:
            ThucydidesWebDriverSupport.webdriverManager.defaultDriverType == "chrome"
    }


}