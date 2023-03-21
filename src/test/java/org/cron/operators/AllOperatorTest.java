package org.cron.operators;

import org.cron.utils.Range;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AllOperatorTest {

    @Test
    public void getRunTimesTest() {
        AllOperator all = new AllOperator("*", new Range(0, 3));
        int[] exptected = {0, 1, 2, 3};
        Assertions.assertArrayEquals(exptected, all.getRunTimes());
    }
}
