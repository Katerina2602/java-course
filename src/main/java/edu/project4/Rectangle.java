package edu.project4;

import java.util.Random;

public record Rectangle(double xMin, double xMax, double yMin, double yMax) {
    private static final Random RANDOM = new Random();

    public Point random() {
        return new Point(
            RANDOM.nextDouble(xMin, xMax),
            RANDOM.nextDouble(yMin, yMax)
        );
    }
}
