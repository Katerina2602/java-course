package edu.hw1;

@SuppressWarnings({"MagicNumber", "ReturnCount", "CyclomaticComplexity"})
public class Task8 {

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] matrix) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (matrix[i][j] == 1) {
                    if ((i - 2) >= 0 & (j + 1) < 8) {
                        if (matrix[i - 2][j + 1] == 1) {
                            return false;
                        }
                    }
                    if ((i - 2) >= 0 & (j - 1) >= 0) {
                        if (matrix[i - 2][j - 1] == 1) {
                            return false;
                        }
                    }
                    if ((i - 1) >= 0 & (j - 2) >= 0) {
                        if (matrix[i - 1][j - 2] == 1) {
                            return false;
                        }
                    }
                    if ((i + 1) < 8 & (j - 2) >= 0) {
                        if (matrix[i + 1][j - 2] == 1) {
                            return false;
                        }
                    }
                    if ((i + 2) < 8 & (j - 1) >= 0) {
                        if (matrix[i + 2][j - 1] == 1) {
                            return false;
                        }
                    }
                    if ((i + 2) < 8 & (j + 1) < 8) {
                        if (matrix[i + 2][j + 1] == 1) {
                            return false;
                        }
                    }
                    if ((i + 1) < 8 & (j + 2) < 8) {
                        if (matrix[i + 1][j + 2] == 1) {
                            return false;
                        }
                    }
                    if ((i - 1) >= 0 & (j + 2) < 8) {
                        if (matrix[i - 1][j + 2] == 1) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
