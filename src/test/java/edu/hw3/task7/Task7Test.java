package edu.hw3.task7;

import java.util.TreeMap;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task7Test {
    @Test
    void test() {
        TreeMap<String, String> tree = new TreeMap<>(new NullSafeComparator());
        tree.put(null, "test");

        assertThat(tree).containsKey(null);
    }
}
