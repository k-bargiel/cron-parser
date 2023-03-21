package org.cron.operators;

import org.cron.utils.OutOfRangeException;
import org.cron.utils.Range;

import java.util.stream.IntStream;

public class RangeOperator extends CronOperator {

    public RangeOperator(String value, Range range) {
        super(value, range);
    }

    @Override
    public int[] getRunTimes() {
        String[] splited = value.split("-");
        int rangeStart = Integer.parseInt(splited[0]);
        int rangeStop = Integer.parseInt(splited[1]);
        this.validateRange(rangeStart, rangeStop);
        return IntStream
                .rangeClosed(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]))
                .toArray();
    }

    private void validateRange(int actualStart, int actualEnd) {
        if((actualStart < this.range.getMin() || actualStart > this.range.getMax()) ||
                (actualEnd < this.range.getMin() || actualEnd > this.range.getMax())) {
            throw new OutOfRangeException(String.format("Range operator out of range. Given range: %d-%d. Valid range: %d-%d",
                    actualStart, actualEnd, this.range.getMin(), this.range.getMax()));
        }
        if(actualStart > actualEnd) {
            throw new OutOfRangeException(String.format("Invalid range. Start cannot be bigger that end. " +
                            "Given range: %d-%d. Valid range: %d-%d",
                    actualStart, actualEnd, this.range.getMin(), this.range.getMax()));
        }
    }
}
