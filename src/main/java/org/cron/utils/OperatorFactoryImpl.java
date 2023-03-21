package org.cron.utils;

import org.cron.operators.*;

public class OperatorFactoryImpl implements OperatorFactory {

    @Override
    public CronReader getOperator(String cronPartialExpression, Range range) {
        if (cronPartialExpression.matches("^\\d+$")) {
            return new NumberOperator(cronPartialExpression, range);
        } else if (cronPartialExpression.equals("*")) {
            return new AllOperator(cronPartialExpression, range);
        } else if (cronPartialExpression.matches("^\\d+/\\d+$") || cronPartialExpression.matches("^\\*/\\d+$")) {
            return new IncrementOperator(cronPartialExpression, range);
        } else if (cronPartialExpression.matches("^\\d+-\\d+$")) {
            return new RangeOperator(cronPartialExpression, range);
        } else if (cronPartialExpression.matches("^\\d+(,\\d+)*$")) {
            return new ValuesOperator(cronPartialExpression, range);
        } else {
            throw new UnsupportedOperationException(
                    String.format("Cron expression: %s is not supported.", cronPartialExpression)
            );
        }
    }

}
