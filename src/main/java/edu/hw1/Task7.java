package edu.hw1;

public class Task7 {

    private Task7() {
    }

    public static int rotateRight(int number, int shift) {
        String binaryNumber = Integer.toBinaryString(number);
        StringBuilder newBinaryNumber = new StringBuilder();

        newBinaryNumber.append("0".repeat(binaryNumber.length()));

        for (int i = 0; i < binaryNumber.length(); i++) {
            if (binaryNumber.charAt(i) == '1') {
                newBinaryNumber.setCharAt((i + shift) % binaryNumber.length(), '1');
            }
        }
        return Integer.parseInt(newBinaryNumber.toString(), 2);
    }

    public static int rotateLeft(int number, int shift) {
        String binaryNumber = Integer.toBinaryString(number);
        return rotateRight(number, binaryNumber.length() - shift % binaryNumber.length());
    }
}
