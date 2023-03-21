package org.cron.operators;

import org.cron.utils.OutOfRangeException;
import org.cron.utils.Range;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NumberOperatorTest {

    @Test
    public void getRunTimesOutOfRangeTest() {
        var thrown = Assertions.assertThrows(OutOfRangeException.class, () ->
                new NumberOperator("12", new Range(0, 3)).getRunTimes());
        Assertions.assertEquals("Number operator out of range. Given value: 12. Range: 0-3", thrown.getMessage());
    }

    @Test
    public void getRunTimesTest() {
        int[] expected = {12};
        Assertions.assertArrayEquals(expected, new NumberOperator("12", new Range(0, 12)).getRunTimes());
    }
}
