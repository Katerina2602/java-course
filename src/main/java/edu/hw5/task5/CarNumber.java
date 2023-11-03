package edu.hw5.task5;

import java.util.regex.Pattern;

public class CarNumber {

    private CarNumber() {

    }

    private static final Pattern LICENSE_PLATE_PATTERN =
        Pattern.compile("^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}$");

    public static boolean checksCarNumber(String number) {
        return LICENSE_PLATE_PATTERN.matcher(number).matches();
    }
}
