package edu.hw2.task1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NegateTest {
    @Test
    void willDoNegative() {
        double expectedValue = 5.0;
        Constant constant = new Constant(expectedValue);
        assertEquals(-expectedValue,  new Negate(constant).evaluate());
    }
}
