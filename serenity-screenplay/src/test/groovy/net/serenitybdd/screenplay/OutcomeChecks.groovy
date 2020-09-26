package net.serenitybdd.screenplay

import net.thucydides.core.model.TestOutcome

class OutcomeChecks {

    static def TestOutcomeChecker resultsFrom(List<TestOutcome> testOutcomes) {
        new TestOutcomeChecker(testOutcomes);
    }

    static class TestOutcomeChecker {
        private final List<TestOutcome> testOutcomes;

        public TestOutcomeChecker(List<TestOutcome> testOutcomes) {
            this.testOutcomes = testOutcomes;
        }

        public TestOutcome getAt(String methodName) {
            def matchingOutcome = testOutcomes.find { it.name.equals(methodName) }
            if (!matchingOutcome) {
                throw new AssertionError("No matching test method called $methodName")
            }
            return matchingOutcome
        }

        public boolean isEmpty() {
            return testOutcomes.isEmpty()
        }
    }
}
