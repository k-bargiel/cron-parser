package org.cron.operators;

import org.cron.utils.Range;

import java.util.stream.IntStream;

public class AllOperator extends CronOperator {

    public AllOperator(String value, Range range) {
        super(value, range);
    }

    @Override
    public int[] getRunTimes() {
        return IntStream.rangeClosed(this.range.getMin(), this.range.getMax()).toArray();
    }

}