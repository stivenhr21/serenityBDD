package net.thucydides.core.model.results;

import net.thucydides.core.model.TestResult;

public class NextStepWasSkippedStrategy implements StepResultMergeStragegy {
    private final TestResult nextStepResult;

    public NextStepWasSkippedStrategy(TestResult nextStepResult) {
        this.nextStepResult = nextStepResult;
    }

    @Override
    public TestResult with(TestResult previousResult) {
        switch (previousResult) {
            case FAILURE: return previousResult;
            case ERROR: return previousResult;
            case COMPROMISED: return previousResult;
            default: return nextStepResult;
        }
    }
}
