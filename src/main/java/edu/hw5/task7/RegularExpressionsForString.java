package edu.hw5.task7;

import java.util.regex.Pattern;

public class RegularExpressionsForString {
    private static final Pattern SAME_BEGINNING_AND_END = Pattern.compile("^(0|1).*\\1$");
    private static final Pattern LEAST_THREE_CHARS = Pattern.compile("^[0|1]{2}0[0|1]*$");
    private static final Pattern FROM_ONE_TO_THREE = Pattern.compile("^[01]{1,3}$");

    private RegularExpressionsForString() {
    }

    public static boolean isSameBeginningAndEnd(String input) {
        return SAME_BEGINNING_AND_END.matcher(input).matches();
    }

    public static boolean isLeastThreeChars(String input) {

        return LEAST_THREE_CHARS.matcher(input).matches();
    }

    public static boolean isFromOneToThree(String input) {

        return FROM_ONE_TO_THREE.matcher(input).matches();
    }

}
