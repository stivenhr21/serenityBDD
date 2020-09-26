package net.thucydides.core.output;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import net.thucydides.core.matchers.SimpleValueMatcher;

import java.io.IOException;
import java.util.List;

public interface ResultsOutput {
    void recordResult(List<? extends Object> columnValues,
                      SimpleValueMatcher... check) throws IOException, WriteException, BiffException;
}
