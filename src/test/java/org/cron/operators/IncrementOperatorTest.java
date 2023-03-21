package org.cron.operators;

import org.cron.utils.OutOfRangeException;
import org.cron.utils.Range;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IncrementOperatorTest {

    @Test
    public void getRunTimesOutOfRangeTest() {
        var thrown = Assertions.assertThrows(OutOfRangeException.class, () ->
                new IncrementOperator("3/2", new Range(0, 2)).getRunTimes());
        Assertions.assertEquals("Increment operator out of range. Given starting value: 3. Range: 0-2",
                thrown.getMessage());
    }

    @Test
    public void getRunTimesNumbersTest() {
        int[] expected = {0,1,2};
        Assertions.assertArrayEquals(expected, new IncrementOperator("0/1", new Range(0, 2)).getRunTimes());
    }

    @Test
    public void getRunTimesAsteriskTest() {
        int[] expected = {0,1,2,3,4,5};
        Assertions.assertArrayEquals(expected, new IncrementOperator("*/1", new Range(0, 5)).getRunTimes());
    }

    @Test
    public void getRunTimesStepTest() {
        int[] expected = {0,2,4};
        Assertions.assertArrayEquals(expected, new IncrementOperator("*/2", new Range(0, 5)).getRunTimes());
    }
}
