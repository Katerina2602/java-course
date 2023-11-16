package edu.hw4;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {
    public static final int FOUR = 4;
    public static final int TWO = 2;
    public static final int EIGHT = 8;
    public static final int ZERO = 0;

    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    enum Sex {
        M, F
    }

    public int paws() {
        return switch (type) {
            case CAT, DOG -> FOUR;
            case BIRD -> TWO;
            case FISH -> ZERO;
            case SPIDER -> EIGHT;
        };
    }
}
