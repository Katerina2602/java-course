package edu.hw1;

import java.util.Arrays;

@SuppressWarnings("MagicNumber")
public class Task6 {

    private Task6() {
    }

    public static int countK(int number) {
        return countK(number, 0);
    }

    public static int countK(int num, int cnt) {
        int number = num;
        int count = cnt;

        if (number <= 1000 || number > 9999) {
            return -1;
        }
        if (number == 6174) {
            return count;
        }
        int[] arrayNumber = new int[4];
        arrayNumber[0] = number % 10;
        number = number / 10;
        arrayNumber[1] = number % 10;
        number = number / 10;
        arrayNumber[2] = number % 10;
        number = number / 10;
        arrayNumber[3] = number % 10;

        if (arrayNumber[0] == arrayNumber[1] && arrayNumber[1] == arrayNumber[2] && arrayNumber[2] == arrayNumber[3]) {
            return -1;
        }

        Arrays.sort(arrayNumber);

        int numberMax = arrayNumber[0] + arrayNumber[1] * 10 + arrayNumber[2] * 100 + arrayNumber[3] * 1000;
        int numberMin = arrayNumber[3] + arrayNumber[2] * 10 + arrayNumber[1] * 100 + arrayNumber[0] * 1000;
        number = numberMax - numberMin;
        count++;
        count = countK(number, count);

        return count;
    }
}
