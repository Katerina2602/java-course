package edu.hw1;

public class Task1 {
    private static final int MAX_SECONDS = 60;

    private Task1() {
    }

    public static long convertTimeToSeconds(String time) {
        try {
            String[] minutesAndSeconds = time.split(":");
            if (!isValid(minutesAndSeconds)) {
                return -1;
            }
            return Long.parseLong(minutesAndSeconds[0]) * MAX_SECONDS + Long.parseLong(minutesAndSeconds[1]);
        } catch (Exception ex) {
            return -1;
        }
    }

    private static boolean isValid(String[] arrayStr) {
        return arrayStr.length <= 2 && Long.parseLong(arrayStr[1]) < MAX_SECONDS;
    }

}
