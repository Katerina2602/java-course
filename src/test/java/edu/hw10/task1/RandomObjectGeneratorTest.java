package edu.hw10.task1;

import edu.hw10.task1.generator.Generator;
import edu.hw10.task1.generator.IntegerGenerator;
import edu.hw10.task1.generator.StringGenerator;
import edu.hw10.task1.model.Cat;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomObjectGeneratorTest {
    private final Map<Class, Generator> generators = Map.of(
        String.class, new StringGenerator(),
        Integer.class, new IntegerGenerator()
    );
    private final RandomObjectGenerator generator = new RandomObjectGenerator(generators);

    @Test
    void willCreateViaConstructor() throws Exception {
        boolean ownerNameWasNull = false;
        boolean ownerNameWasNotNull = false;

        for (int i = 0; i < 50; i++) {
            Cat cat = generator.nextObject(Cat.class);

            if (cat.ownerName() == null) {
                ownerNameWasNull = true;
            } else {
                ownerNameWasNotNull = true;
            }

            assertTrue(cat.name().length() >= 1 && cat.name().length() <= 10);
            assertTrue(cat.age() >= 0 && cat.age() <= 20);
        }

        assertTrue(ownerNameWasNull);
        assertTrue(ownerNameWasNotNull);
    }

    @Test
    void willCreateViaFactoryMethod() throws Exception {
        boolean ownerNameWasNull = false;
        boolean ownerNameWasNotNull = false;

        for (int i = 0; i < 50; i++) {
            Cat cat = generator.nextObject(Cat.class, "create");

            if (cat.ownerName() == null) {
                ownerNameWasNull = true;
            } else {
                ownerNameWasNotNull = true;
            }

            assertTrue(cat.name().length() >= 1 && cat.name().length() <= 10);
            assertTrue(cat.age() >= 0 && cat.age() <= 20);
        }

        assertTrue(ownerNameWasNull);
        assertTrue(ownerNameWasNotNull);
    }
}
