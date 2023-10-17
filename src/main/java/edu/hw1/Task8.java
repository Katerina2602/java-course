package edu.hw1;

//@SuppressWarnings({"MagicNumber"})
//"CyclomaticComplexity", "ReturnCount"
public class Task8 {

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 1) {
                    if ((i - 2) >= 0 && (j - 1) >= 0 && matrix[i - 2][j - 1] == 1
                        || (i - 1) >= 0 && (j - 2) >= 0 && matrix[i - 1][j - 2] == 1
                        || (i + 1) < matrix.length && (j - 2) >= 0 && matrix[i + 1][j - 2] == 1
                        || (i + 2) < matrix.length && (j - 1) >= 0 && matrix[i + 2][j - 1] == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
