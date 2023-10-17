package edu.hw2.task1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExponentTest {
    @Test
    void willDoExponent() {
        double a = 2.0;
        double b = 5.0;

        Constant constant = new Constant(a);
        assertEquals(Math.pow(a, b), new Exponent(constant, b).evaluate());
    }

}
