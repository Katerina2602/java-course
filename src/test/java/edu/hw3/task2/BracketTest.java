package edu.hw3.task2;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BracketTest {
    @Test
    void willReturnCorrectResultWhenLineBalanced() {

        assertEquals(List.of("()", "()"), Bracket.clusteringBracket("()()"));
        assertEquals(List.of("((()))"), Bracket.clusteringBracket("((()))"));
        assertEquals(List.of("((()))", "(())", "()", "()"), Bracket.clusteringBracket("((()))(())()()"));
        assertEquals(List.of("(())", "()"), Bracket.clusteringBracket("(())()"));
    }

    @Test
    void willReturnErrorWhenLineIsNotBalanced() {
        assertThrows(IllegalArgumentException.class, () -> Bracket.clusteringBracket("())"));
        assertThrows(IllegalArgumentException.class, () -> Bracket.clusteringBracket("("));
        assertThrows(IllegalArgumentException.class, () -> Bracket.clusteringBracket("())(()"));
        assertThrows(IllegalArgumentException.class, () -> Bracket.clusteringBracket(")("));

    }
}
