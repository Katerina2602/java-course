package edu.hw2.task1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdditionTest {
    @Test
    void willDoAddition() {
        double a = 5.0;
        double b = 8.0;
        Constant constant1 = new Constant(a);
        Constant constant2 = new Constant(b);
        assertEquals(a + b, new Addition(constant1, constant2).evaluate());
    }

}
