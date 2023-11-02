package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordFrequency {
    private WordFrequency() {

    }

    public static <T> Map<T, Integer> countWord(List<T> listOfObjects) {
        Map<T, Integer> frequencyDictionary = new HashMap<>();

        for (T listOfObject : listOfObjects) {
            frequencyDictionary.merge(listOfObject, 1, Integer::sum);
        }

        return frequencyDictionary;
    }
}
