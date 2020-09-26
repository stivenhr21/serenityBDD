package net.thucydides.core.statistics

import net.thucydides.core.guice.Injectors
import spock.lang.Specification

class WhenKeepingTrackOfTheTestExecutionCount extends Specification {

    TestCount testCount = new AtomicTestCount()

    def "should start test count at 1"() {
        when:
            int initialCount = testCount.getNextTest()
        then:
            initialCount == 1
    }

    def "should increment the test count when a test is executed"() {
        when:
            testCount.getNextTest()
            int nextCount = testCount.getNextTest()
        then:
            nextCount == 2
    }

    def "should be able to query the current test number"() {
        when:
            testCount.getNextTest()
            testCount.getNextTest()
        then:
            testCount.getCurrentTestNumber() == 2
    }

    def "the test count should be managed by Guice"() {
        when:
            TestCount managedTestCount = Injectors.getInjector().getInstance(TestCount)
        then:
            managedTestCount != null
    }
}
