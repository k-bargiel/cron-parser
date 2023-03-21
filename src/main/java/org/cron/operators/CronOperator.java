package org.cron.operators;

import org.cron.utils.Range;

public abstract class CronOperator implements CronReader {

    protected String value;
    protected Range range;

    public CronOperator(String value, Range range) {
        this.value = value;
        this.range = range;
    }

}

