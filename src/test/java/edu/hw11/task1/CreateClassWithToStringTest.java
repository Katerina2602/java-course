package edu.hw11.task1;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateClassWithToStringTest {
    @Test
    void willCreateClassWithToString() throws Exception {
        String expectedMessage = "Hello, ByteBuddy!";

        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value(expectedMessage))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();

        assertEquals(expectedMessage, dynamicType.newInstance().toString());
    }
}
