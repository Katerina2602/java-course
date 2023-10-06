package edu.hw1;

public class Task1 {
    private static final int MAX_SECONDS = 60;

    private Task1() {
    }

    public static long timeToSeconds(String time) {
        try {
            String[] minutesAndSeconds = time.split(":");
            if (Long.parseLong(minutesAndSeconds[1]) >= MAX_SECONDS) {
                return -1;
            }
            return Long.parseLong(minutesAndSeconds[0]) * MAX_SECONDS + Long.parseLong(minutesAndSeconds[1]);
        } catch (Exception ex) {
            return -1;
        }
    }
}
