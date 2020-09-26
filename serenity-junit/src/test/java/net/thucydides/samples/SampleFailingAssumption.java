package net.thucydides.samples;

import net.thucydides.junit.runners.ThucydidesRunner;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ThucydidesRunner.class)
public class SampleFailingAssumption {

    @Test
    public void tests_with_failing_assumptions_are_ignored() {
        Assume.assumeTrue(false);
    }
}
