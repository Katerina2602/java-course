package edu.hw5.task8;

import java.util.regex.Pattern;

public class RegularExpressions {

    //нечетной длины
    private static final Pattern ODD_LENGTH = Pattern.compile("^[01]([01][01])*$");
    // начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
    private static final Pattern ZERO_ODD_ONE_EVEN = Pattern.compile("^([0]([01][01])*)|([1][01]([01][01])*)$");
    // каждый нечетный символ равен 1
    private static final Pattern EVERY_ODD_ONE = Pattern.compile("^([1](([01]$)|([01][1]))*)$");
    //  количество 0 кратно 3
    private static final Pattern COUNT_ZERO_IS_MULTIPLE_OF_THREE = Pattern.compile("^(1*(01*01*01*)*)*$");
    //любая строка, кроме 11 или 111
    private static final Pattern NOT_TWO_OR_THREE_UNITS = Pattern.compile("^(?!11$)(?!111$)[01]+$");
    //содержит не менее двух 0 и не более одной 1
    private static final Pattern MORE_TWO_ZEROS_AND_LESS_ONE = Pattern.compile("^(?!.*1.*1)[01]*0[01]*0[01]*$");
    //нет последовательных 1
    private static final Pattern NO_CONSECUTIVE_UNITS = Pattern.compile("^(?!.*11)[01]+$");

    private RegularExpressions() {
    }

    public static boolean isOddLength(String input) {
        return ODD_LENGTH.matcher(input).matches();
    }

    public static boolean isOddIfZeroAndEvenIfOne(String input) {
        return ZERO_ODD_ONE_EVEN.matcher(input).matches();
    }

    public static boolean isEveryOddOne(String input) {
        return EVERY_ODD_ONE.matcher(input).matches();
    }

    public static boolean isCountOfZerosIsAMultipleOfThree(String input) {
        return COUNT_ZERO_IS_MULTIPLE_OF_THREE.matcher(input).matches();
    }

    public static boolean isNotTwoOrThreeUnits(String input) {
        return NOT_TWO_OR_THREE_UNITS.matcher(input).matches();
    }

    public static boolean isMoreThatTwoZerosAndLessThanOneUnits(String input) {
        return MORE_TWO_ZEROS_AND_LESS_ONE.matcher(input).matches();
    }

    public static boolean isNoConsecutiveUnits(String input) {
        return NO_CONSECUTIVE_UNITS.matcher(input).matches();
    }

}
