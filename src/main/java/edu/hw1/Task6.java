package edu.hw1;

import java.util.Arrays;

public class Task6 {
    private static final int TEN = 10;
    private static final int HUNDRED = 100;
    private static final int THOUSAND = 1000;
    private static final int KAPREKARA_NUMBER = 6174;
    private static final int MIN_AVAILABLE = 1000;
    private static final int MAX_AVAILABLE = 9999;
    private static final int DIGIT_COUNT = 4;
    private static final int FIRST_ELEMENT_ARRAY = 0;
    private static final int SECOND_ELEMENT_ARRAY = 1;
    private static final int THIRD_ELEMENT_ARRAY = 2;
    private static final int FOURTH_ELEMENT_ARRAY = 3;

    private Task6() {
    }

    public static int countSteps(int number) {
        return countSteps(number, 0);
    }

    public static int countSteps(int sourceNumber, int steps) {

        if (sourceNumber <= MIN_AVAILABLE || sourceNumber > MAX_AVAILABLE) {
            return -1;
        }
        if (sourceNumber == KAPREKARA_NUMBER) {
            return steps;
        }
        int newNumber = sourceNumber;
        int[] digitNumber = new int[DIGIT_COUNT];

        for (int i = 0; i < DIGIT_COUNT; i++) {
            digitNumber[i] = newNumber % TEN;
            newNumber = newNumber / TEN;
        }

        if (digitNumber[FIRST_ELEMENT_ARRAY] == digitNumber[SECOND_ELEMENT_ARRAY]
            && digitNumber[SECOND_ELEMENT_ARRAY] == digitNumber[THIRD_ELEMENT_ARRAY]
            && digitNumber[THIRD_ELEMENT_ARRAY] == digitNumber[FOURTH_ELEMENT_ARRAY]) {
            return -1;
        }

        Arrays.sort(digitNumber);

        int numberMax = getMaxNumberFromArray(digitNumber);
        int numberMin = getMinNumberFromArray(digitNumber);
        int delta = numberMax - numberMin;
        if (delta < THOUSAND) {
            delta = delta * TEN;
        }

        return countSteps(delta, steps + 1);
    }

    private static int getMaxNumberFromArray(int[] array) {
        return array[FIRST_ELEMENT_ARRAY] + array[SECOND_ELEMENT_ARRAY] * TEN
            + array[THIRD_ELEMENT_ARRAY] * HUNDRED + array[FOURTH_ELEMENT_ARRAY] * THOUSAND;
    }

    private static int getMinNumberFromArray(int[] array) {
        return array[FOURTH_ELEMENT_ARRAY] + array[THIRD_ELEMENT_ARRAY] * TEN
            + array[SECOND_ELEMENT_ARRAY] * HUNDRED + array[FIRST_ELEMENT_ARRAY] * THOUSAND;
    }
}
