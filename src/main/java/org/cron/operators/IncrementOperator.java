package org.cron.operators;

import org.cron.utils.OutOfRangeException;
import org.cron.utils.Range;

import java.util.LinkedList;

public class IncrementOperator extends CronOperator {

    public IncrementOperator(String value, Range range) {
        super(value, range);
    }

    @Override
    public int[] getRunTimes() {
        String[] splited = value.split("/");
        int start = 0;
        if(splited[0].equals("*")) {
            start = this.range.getMin();
        } else {
            start = Integer.parseInt(splited[0]);
            validateRange(start);
        }
        int step = Integer.parseInt(splited[1]);
        LinkedList<Integer> result = new LinkedList<>();
        while(start <= this.range.getMax()) {
            result.add(start);
            start += step;
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private void validateRange(int start) {
        if (start < this.range.getMin() || start > this.range.getMax()) {
            throw new OutOfRangeException(String.format("Increment operator out of range. Given starting " +
                            "value: %d. Range: %d-%d",
                    start, this.range.getMin(), this.range.getMax()));
        }
    }
}
