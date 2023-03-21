package org.cron.operators;

import org.cron.utils.OutOfRangeException;
import org.cron.utils.Range;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RangeOperatorTest {

    @Test
    public void getRunTimesOutOfRangeTest() {
        var thrown = Assertions.assertThrows(OutOfRangeException.class, () ->
                new RangeOperator("0-2", new Range(0, 1)).getRunTimes());
        Assertions.assertEquals("Range operator out of range. Given range: 0-2. Valid range: 0-1", thrown.getMessage());
    }

    @Test
    public void getRunTimesMinBiggerThanMaxTest() {
        var thrown = Assertions.assertThrows(OutOfRangeException.class, () ->
                new RangeOperator("2-1", new Range(0, 3)).getRunTimes());
        Assertions.assertEquals("Invalid range. Start cannot be bigger that end. Given range: 2-1. Valid range: 0-3",
                thrown.getMessage());
    }

    @Test
    public void getRunTimesTest() {
        int[] expected = {0,1,2};
        Assertions.assertArrayEquals(expected, new RangeOperator("0-2", new Range(0, 2)).getRunTimes());
    }
}
