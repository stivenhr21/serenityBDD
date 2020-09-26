package net.thucydides.core.reports.adaptors.common;

import net.thucydides.core.model.TestOutcome;
import net.thucydides.core.reports.adaptors.TestOutcomeAdaptor;

import java.io.IOException;
import java.util.List;


public abstract class FilebasedOutcomeAdaptor implements TestOutcomeAdaptor {

    @Override
    public List<TestOutcome> loadOutcomes() throws IOException {
        throw new UnsupportedOperationException("File based adaptors need to be provided a directory");
    }
}
