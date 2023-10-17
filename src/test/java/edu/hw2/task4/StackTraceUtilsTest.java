package edu.hw2.task4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StackTraceUtilsTest {
    @Test
    void willGetCallingInfoCorrect() {
        try {
            throwableMethod();
        } catch (Exception ex) {
            CallingInfo info = StackTraceUtils.callingInfo(ex);
            assertEquals("StackTraceUtilsTest", info.className());
            assertEquals("throwableMethod", info.methodName());
        }
    }

    private void throwableMethod() {
        throw new RuntimeException();
    }
}
