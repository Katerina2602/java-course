package edu.hw3.task5;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListContactTest {
    @Test
    void willReturnSortedListInAscendingOrder() {
        String[] s = {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes", "Alex"};
        List<String> v = List.of("Alex", "Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke");
        assertEquals(v, ListContact.contact(s, "ASC"));
    }

    @Test
    void willReturnEmptyLineWhenTheListIsEmpty() {
        String[] s = {""};
        List<String> v = List.of("");
        assertEquals(v, ListContact.contact(s, "ASC"));
    }

    @Test
    void willReturnTrueIfInListNotIsElements() {
        String[] s = null;
        assertTrue(ListContact.contact(s, "ASC").isEmpty());
    }

    @Test
    void willReturnSortedListInDescendingOrder() {
        String[] s = {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes", "Alex"};
        List<String> v = List.of("John Locke", "David Hume", "Rene Descartes", "Thomas Aquinas", "Alex");
        assertEquals(v, ListContact.contact(s, "DESC"));
    }
}
