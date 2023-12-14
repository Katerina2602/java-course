package edu.hw10.task1.generator;

import java.util.Optional;

public class StringGenerator implements Generator<String> {
    private static final int DEFAULT_MIN_LENGTH = 5;
    private static final int DEFAULT_MAX_LENGTH = 20;

    @Override
    public String generate(Restrictions restrictions) {
        if (maybeReturnNull(restrictions)) {
            return null;
        }

        byte[] array = new byte[getLength(restrictions)];
        RANDOM.nextBytes(array);

        return new String(array);
    }

    private int getLength(Restrictions restrictions) {
        int min = Optional.ofNullable(restrictions.min()).orElse(DEFAULT_MIN_LENGTH);
        int max = Optional.ofNullable(restrictions.max()).orElse(DEFAULT_MAX_LENGTH);

        return RANDOM.nextInt(min, max + 1);
    }
}
