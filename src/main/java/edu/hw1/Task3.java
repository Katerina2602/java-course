package edu.hw1;

public class Task3 {

    private Task3() {
    }

    public static boolean isNestable(int[] array1, int[] array2) {
        try {
            int max1 = array1[0];
            int min1 = array1[0];
            int max2 = array2[0];
            int min2 = array2[0];
            for (int i = 1; i < array1.length; i++) {
                if (array1[i] > max1) {
                    max1 = array1[i];
                }
                if (array1[i] < min1) {
                    min1 = array1[i];
                }
            }
            for (int j = 1; j < array2.length; j++) {
                if (array2[j] > max2) {
                    max2 = array2[j];
                }
                if (array2[j] < min2) {
                    min2 = array2[j];
                }
            }
            return min1 > min2 && max1 < max2;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Incorrect input arrays");
        }
    }

}
