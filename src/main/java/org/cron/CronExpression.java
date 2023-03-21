package org.cron;

import org.cron.operators.CronReader;
import org.cron.utils.OperatorFactory;
import org.cron.utils.Range;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CronExpression {

    private String minute;
    private String hour;
    private String monthDay;
    private String month;
    private String weekDay;
    private String command;
    private OperatorFactory operatorFactory;

    public CronExpression(String cronExpression, OperatorFactory operatorFactory) {
        String[] expressionSplit = cronExpression.split(" ");
        if(expressionSplit.length < 6) {
            throw new IllegalArgumentException("Cron should have at least 5 space separated expressions.");
        }
        this.minute = expressionSplit[0];
        this.hour = expressionSplit[1];
        this.monthDay = expressionSplit[2];
        this.month = expressionSplit[3];
        this.weekDay = expressionSplit[4];
        this.command = Arrays.stream(expressionSplit).skip(5).collect(Collectors.joining(" "));
        this.operatorFactory = operatorFactory;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMonthDay() {
        return monthDay;
    }

    public void setMonthDay(String monthDay) {
        this.monthDay = monthDay;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
    public Map<String, int[]> getTimesTable() {
        CronReader minute = operatorFactory.getOperator(this.minute, new Range(0, 59));
        CronReader hour = operatorFactory.getOperator(this.hour, new Range(0, 23));
        CronReader monthDay = operatorFactory.getOperator(this.monthDay, new Range(1, 31));
        CronReader month = operatorFactory.getOperator(this.month, new Range(1, 12));
        CronReader weekDay = operatorFactory.getOperator(this.weekDay, new Range(1, 7));
        return new LinkedHashMap<>() {{
           put("Minute", minute.getRunTimes());
           put("Hour", hour.getRunTimes());
           put("MonthDay", monthDay.getRunTimes());
           put("Month", month.getRunTimes());
           put("WeekDay", weekDay.getRunTimes());
        }};
    }

}
