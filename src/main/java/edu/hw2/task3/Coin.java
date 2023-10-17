package edu.hw2.task3;

import java.util.Random;

public class Coin {
    private static final Random RANDOM = new Random();

    public boolean isEagle() {
        return RANDOM.nextBoolean();
    }
}
