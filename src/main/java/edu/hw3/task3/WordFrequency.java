package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordFrequency {
    private WordFrequency() {

    }

    public static Map<Object, Integer> countWord(List<Object> listOfObjects) {
        Map<Object, Integer> frequencyDictionary = new HashMap<>();
        for (int i = 0; i < listOfObjects.size(); i++) {
            frequencyDictionary.merge(listOfObjects.get(i), 1, Integer::sum);
        }
        return frequencyDictionary;
    }
}
