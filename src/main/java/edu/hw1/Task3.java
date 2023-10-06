package edu.hw1;

import java.util.Arrays;

public class Task3 {

    private Task3() {
    }

    public static boolean isNestable(int[] array1, int[] array2) {
        int max1 = Arrays.stream(array1).max().getAsInt();
        int max2 = Arrays.stream(array2).max().getAsInt();
        int min1 = Arrays.stream(array1).min().getAsInt();
        int min2 = Arrays.stream(array2).min().getAsInt();

        return min1 > min2 && max1 < max2;
    }
}
