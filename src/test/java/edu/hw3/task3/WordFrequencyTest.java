package edu.hw3.task3;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WordFrequencyTest {
    @Test
    void willReturnFrequencyOfRepetitionOfEachWordLatinAlphabet() {
        Map<Object, Integer> map = WordFrequency.countWord(List.of("cat", "dog", "cat"));
        assertEquals(2, map.get("cat"));
        assertEquals(1, map.get("dog"));
    }

    @Test
    void willReturnFrequencyOfRepetitionOfEachWordRussianAlphabet() {
        Map<Object, Integer> map = WordFrequency.countWord(List.of("код", "код", "код", "сон"));
        assertEquals(3, map.get("код"));
        assertEquals(1, map.get("сон"));
    }

    @Test
    void willReturnFrequencyOfRepetitionOfEachNumber() {
        Map<Object, Integer> map = WordFrequency.countWord(List.of(1, 1, 2, 2, 100));
        assertEquals(2, map.get(1));
        assertEquals(2, map.get(2));
        assertEquals(1, map.get(100));
    }
}
