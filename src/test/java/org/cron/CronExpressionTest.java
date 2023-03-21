package org.cron;

import org.cron.operators.CronReader;
import org.cron.operators.RangeOperator;
import org.cron.utils.OperatorFactoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CronExpressionTest {


    @Test
    public void tooShortCronExpressionTest() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new CronExpression("1 1 1 1 test", new OperatorFactoryImpl()));
    }

    @Test
    public void returnValidMapTest() {
        OperatorFactoryImpl factoryMock = mock(OperatorFactoryImpl.class);
        CronReader operatorMock = mock(RangeOperator.class);
        when(operatorMock.getRunTimes()).thenReturn(new int[1], new int[2], new int[3], new int[4], new int[5]);
        when(factoryMock.getOperator(any(), any())).thenReturn(operatorMock);

        CronExpression c = new CronExpression("1 1 1 1 1 test", factoryMock);
        var timesTable = c.getTimesTable();

        Assertions.assertArrayEquals(new int[1], timesTable.get("Minute"));
        Assertions.assertArrayEquals(new int[2], timesTable.get("Hour"));
        Assertions.assertArrayEquals(new int[3], timesTable.get("MonthDay"));
        Assertions.assertArrayEquals(new int[4], timesTable.get("Month"));
        Assertions.assertArrayEquals(new int[5], timesTable.get("WeekDay"));
    }

}
