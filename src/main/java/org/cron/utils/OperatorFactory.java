package org.cron.utils;

import org.cron.operators.CronReader;
import org.cron.utils.Range;

public interface OperatorFactory {

    public CronReader getOperator(String cronPartialExpression, Range range);
}
