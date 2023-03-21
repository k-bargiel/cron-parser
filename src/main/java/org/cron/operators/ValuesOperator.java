package org.cron.operators;

import org.cron.utils.OutOfRangeException;
import org.cron.utils.Range;

import java.util.Arrays;

public class ValuesOperator extends CronOperator {

    public ValuesOperator(String value, Range range) {
        super(value, range);
    }

    @Override
    public int[] getRunTimes() {
        int[] result =  Arrays.stream(value.split(",")).mapToInt(Integer::parseInt).toArray();
        validateRange(result);
        return result;
    }

    private void validateRange(int[] numbers) {
        if (Arrays.stream(numbers).anyMatch(number -> number > this.range.getMax() || number < this.range.getMin())) {
            throw new OutOfRangeException(String.format("Values operator out of range. Given numbers: %s. Valid range: %d-%d",
                    Arrays.toString(numbers), this.range.getMin(), this.range.getMax()));
        }
    }

}
