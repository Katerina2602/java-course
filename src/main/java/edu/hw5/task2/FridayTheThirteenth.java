package edu.hw5.task2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FridayTheThirteenth {
    private static final int FRIDAY_THE_THIRTEENTH = 13;
    private static final int NUMBER_OF_MONTHS_IN_YEAR = 13;
    private static final String FORMAT_DATA = "yyyy-MM-dd";

    public List<String> getAllFridaysThirteenthInYear(int year) throws ParseException {

        String startData = year + "-01-13";
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATA);
        Calendar data = Calendar.getInstance();
        data.setTime(dateFormat.parse(startData));
        ArrayList<String> fridayDates = new ArrayList<>();

        for (int i = 1; i <= NUMBER_OF_MONTHS_IN_YEAR; i++) {
            if (data.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                fridayDates.add(dateFormat.format(data.getTime()));
            }
            data.add(Calendar.MONTH, 1);
        }

        return fridayDates;
    }

    public LocalDate getNextFridayTheThirteenth(String date) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(FORMAT_DATA);
        LocalDate localDate = LocalDate.parse(date, dateFormat);

        LocalDate nextFriday = localDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));

        while (nextFriday.getDayOfMonth() != FRIDAY_THE_THIRTEENTH) {
            nextFriday = nextFriday.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }

        return nextFriday;

    }
}

