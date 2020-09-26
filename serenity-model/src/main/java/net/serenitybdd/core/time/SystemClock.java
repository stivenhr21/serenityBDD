package net.serenitybdd.core.time;

import java.time.ZonedDateTime;

/**
 * Find the current system time.
 */
public interface SystemClock {
    void pauseFor(long timeInMilliseconds);
    ZonedDateTime getCurrentTime();
}
