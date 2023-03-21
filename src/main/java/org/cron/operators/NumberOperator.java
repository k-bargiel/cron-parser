package org.cron.operators;

import org.cron.utils.OutOfRangeException;
import org.cron.utils.Range;

public class NumberOperator extends CronOperator {

    public NumberOperator(String value, Range range) {
        super(value, range);
    }

    @Override
    public int[] getRunTimes() {
        int[] result = new int[1];
        int valueInt = Integer.parseInt(value);
        result[0] = valueInt;
        this.validateRange(valueInt);
        return result;
    }

    private void validateRange(int number) {
        if(number < range.getMin() || number > range.getMax()) {
            throw new OutOfRangeException(String.format("Number operator out of range. Given value: %d. Range: %d-%d",
                    number, this.range.getMin(), this.range.getMax()));
        }
    }
}
