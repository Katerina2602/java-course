package edu.hw10.task1.generator;

import java.util.Random;

public interface Generator<T> {
    Random RANDOM = new Random();

    T generate(Restrictions restrictions);

    default boolean maybeReturnNull(Restrictions restrictions) {
        return !restrictions.notNull() && RANDOM.nextInt(2) == 1;
    }
}
