package org.cron.utils;

import org.cron.operators.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OperatorFactoryImplTest {

    OperatorFactoryImpl factory = null;

    @BeforeEach
    public void beforeEach() {
        factory = new OperatorFactoryImpl();
    }

    @Test
    public void createNumberOperatorTest() {
        assert factory.getOperator("1", new Range(0, 10)) instanceof NumberOperator;
        assert factory.getOperator("11", new Range(0, 10)) instanceof NumberOperator;
    }

    @Test
    public void createRangeOperatorTest() {
        assert factory.getOperator("1-2", new Range(0, 10)) instanceof RangeOperator;
        assert factory.getOperator("12-10", new Range(0, 10)) instanceof RangeOperator;
    }

    @Test
    public void createIncrementOperatorTest() {
        assert factory.getOperator("1/2", new Range(0, 10)) instanceof IncrementOperator;
        assert factory.getOperator("12/10", new Range(0, 10)) instanceof IncrementOperator;
        assert factory.getOperator("*/10", new Range(0, 10)) instanceof IncrementOperator;
    }

    @Test
    public void createValuesOperatorTest() {
        assert factory.getOperator("1,2", new Range(0, 10)) instanceof ValuesOperator;
        assert factory.getOperator("12,10", new Range(0, 10)) instanceof ValuesOperator;
    }

    @Test
    public void createAllOperatorTest() {
        assert factory.getOperator("*", new Range(0, 10)) instanceof AllOperator;
    }

    @Test
    public void invalidExpressions() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> factory.getOperator(",2,3", new Range(0, 10)));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> factory.getOperator("2,3,", new Range(0, 10)));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> factory.getOperator("-1", new Range(0, 10)));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> factory.getOperator("-20-1", new Range(0, 10)));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> factory.getOperator("/20/2", new Range(0, 10)));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> factory.getOperator("**", new Range(0, 10)));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> factory.getOperator("*/", new Range(0, 10)));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> factory.getOperator("-", new Range(0, 10)));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> factory.getOperator(",", new Range(0, 10)));
    }

}
