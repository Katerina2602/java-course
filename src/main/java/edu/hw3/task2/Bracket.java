package edu.hw3.task2;

import java.util.ArrayList;
import java.util.List;

public class Bracket {
    private Bracket() {

    }

    public static List<String> clusteringBracket(String str) {
        ArrayList<String> cluster = new ArrayList<>();
        int openBracket = 0;
        int closBracket = 0;
        int startIndexCluster = 0;
        int i = 0;

        while (i < str.length()) {
            do {
                if (str.charAt(i) == '(') {
                    openBracket++;
                }
                if (str.charAt(i) == ')') {
                    closBracket++;
                }
                i++;
            } while (openBracket != closBracket && i < str.length() && closBracket < openBracket);
            if (closBracket != openBracket) {
                throw new IllegalArgumentException("String is not balanced");
            }
            cluster.add(str.substring(startIndexCluster, i));
            if (i != str.length() - 1) {
                startIndexCluster = i;
            }

            openBracket = 0;
            closBracket = 0;

        }
        return cluster;
    }
}
