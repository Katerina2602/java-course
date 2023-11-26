package edu.hw7.task2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FactorialTest {
    @Test
    void willReturnCorrectResult(){

        assertEquals(120,Factorial.getFactorial(5));
        assertEquals(1,Factorial.getFactorial(0));
        assertEquals(3628800,Factorial.getFactorial(10));
    }

}
