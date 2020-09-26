package net.thucydides.core.model;

import java.math.BigDecimal;

public class TestDuration {

    private final long duration;

    private TestDuration(long duration) {
        this.duration = duration;
    }

    public static TestDuration of(long duration) {
        return new TestDuration(duration);
    }

    public double inSeconds() {
        if (duration == 0) {
            return 0.0;
        } else {
            BigDecimal unroundedDurationInSeconds = new BigDecimal(duration).divide(BigDecimal.valueOf(1000));
            BigDecimal roundedDuration = unroundedDurationInSeconds.setScale(2, BigDecimal.ROUND_HALF_UP);
            return roundedDuration.doubleValue();
        }
    }

}
