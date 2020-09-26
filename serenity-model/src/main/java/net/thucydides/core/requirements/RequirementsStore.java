package net.thucydides.core.requirements;

import net.thucydides.core.requirements.model.Requirement;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface RequirementsStore {
    void clear();

    Optional<List<Requirement>> read() throws IOException;

    void write(List<Requirement> classpathRequirements) throws IOException;
}
