package org.cron.operators;

import org.cron.utils.OutOfRangeException;
import org.cron.utils.Range;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValueOperatorTest {

    @Test
    public void getRunTimesOutOfRangeTest() {
        var thrown = Assertions.assertThrows(OutOfRangeException.class, () ->
                new ValuesOperator("2,3", new Range(0, 2)).getRunTimes());
        Assertions.assertEquals("Values operator out of range. Given numbers: [2, 3]. Valid range: 0-2",
                thrown.getMessage());
    }

    @Test
    public void getRunTimesTest() {
        int[] expected = {0,1,2};
        Assertions.assertArrayEquals(expected, new ValuesOperator("0,1,2", new Range(0, 2)).getRunTimes());
    }
}
