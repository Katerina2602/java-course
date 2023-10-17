package edu.hw2.task1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConstantTest {
    @Test
    void willDoConstant() {
        double expectedValue = 5.0;

        assertEquals(expectedValue, new Constant(expectedValue).evaluate());
    }

}
