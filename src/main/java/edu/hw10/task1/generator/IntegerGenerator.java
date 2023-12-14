package edu.hw10.task1.generator;

import java.util.Optional;

public class IntegerGenerator implements Generator<Integer> {
    @Override
    public Integer generate(Restrictions restrictions) {
        if (maybeReturnNull(restrictions)) {
            return null;
        }

        return RANDOM.nextInt(getMin(restrictions), getMax(restrictions) + 1);
    }

    private int getMin(Restrictions restrictions) {
        return Optional.ofNullable(restrictions.min())
            .orElse(Integer.MIN_VALUE);
    }

    private int getMax(Restrictions restrictions) {
        return Optional.ofNullable(restrictions.max())
            .orElse(Integer.MAX_VALUE);
    }
}
