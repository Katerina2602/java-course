package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyticsForComputerClub {
    private static final int NUMBER_OF_MINUTES = 60;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
    private static final Pattern PATTERN = Pattern.compile(
        "(\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}) - (\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2})");

    public String gatAverageTime(ArrayList<String> list) {
        long minuts = 0;
        int count = 0;

        for (String p : list) {
            Matcher matcher = PATTERN.matcher(p);
            if (matcher.find()) {
                String group1 = matcher.group(1);
                String group2 = matcher.group(2);
                LocalDateTime startTime = LocalDateTime.parse(group1, DATE_TIME_FORMATTER);
                LocalDateTime endTime = LocalDateTime.parse(group2, DATE_TIME_FORMATTER);
                Duration between = Duration.between(startTime, endTime);
                minuts = minuts + between.toMinutes();

                count++;
            }
        }
        long average = 0;
        if (count != 0) {
            average = minuts / count;
        }

        return average / NUMBER_OF_MINUTES + "ч " + average % NUMBER_OF_MINUTES + "м";
    }
}
