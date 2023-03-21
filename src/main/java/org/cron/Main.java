package org.cron;

import org.cron.utils.OperatorFactoryImpl;
import org.cron.utils.OutOfRangeException;

import java.util.Arrays;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            String cronExpressionIn = args[0];
            System.out.printf("Cron expression to parse: %s%n", cronExpressionIn);
            CronExpression cronExpression = new CronExpression(cronExpressionIn, new OperatorFactoryImpl());
            Map<String, int[]> timeTable = cronExpression.getTimesTable();
            timeTable.forEach((key, value) -> System.out.printf("%s %s%n", key, Arrays.toString(value)));
            System.out.println(cronExpression.getCommand());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing cron expression argument.");
        } catch (OutOfRangeException | UnsupportedOperationException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected problem.");
        }
        System.exit(0);
    }
}