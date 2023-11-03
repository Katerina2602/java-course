package edu.hw2.task1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MixedOperationsTest {
    @Test
    void willCalculateAllExpressions() {
        var a = 2.0;
        var b = 4.0;
        var c = 1.0;
        var two = new Constant(a);
        var four = new Constant(b);
        var negOne = new Negate(new Constant(c));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, a);
        var res = new Addition(exp, new Constant(c));
        assertEquals(a, two.evaluate());
        assertEquals(b, four.evaluate());
        assertEquals(-c, negOne.evaluate());
        assertEquals(a + b, sumTwoFour.evaluate());
        assertEquals((a + b) * -c, mult.evaluate());
        assertEquals(Math.pow((a + b) * -c, a), exp.evaluate());
        assertEquals(Math.pow((a + b) * -c, a) + c, res.evaluate());
    }
}
