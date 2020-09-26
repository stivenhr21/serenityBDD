package net.thucydides.core.reports;

import net.thucydides.core.model.TestOutcome;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public interface AcceptanceTestLoader {
    Optional<TestOutcome> loadReportFrom(final File reportFile);

    List<TestOutcome> loadReportsFrom(final File outputDirectory);

    Optional<TestOutcome> loadReportFrom(final Path reportFile);

    List<TestOutcome> loadReportsFrom(final Path outputDirectory);

    Optional<OutcomeFormat> getFormat();
}
