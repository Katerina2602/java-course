package edu.project4.transformation.affine;

import edu.project4.Point;
import java.util.Random;

public class RandomCoefficientAffine implements AffineTransformation {
    private static final Random RANDOM = new Random();
    private static final int MAX_VALUE = 255;
    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double e;
    private final double f;

    private final int red;
    private final int green;
    private final int blue;

    public RandomCoefficientAffine() {
        this.a = RANDOM.nextDouble(-1, 1);
        this.b = RANDOM.nextDouble(-1, 1);
        this.c = RANDOM.nextDouble(-1, 1);
        this.d = RANDOM.nextDouble(-1, 1);
        this.e = RANDOM.nextDouble(-1, 1);
        this.f = RANDOM.nextDouble(-1, 1);
        this.red = RANDOM.nextInt(0, MAX_VALUE);
        this.green = RANDOM.nextInt(0, MAX_VALUE);
        this.blue = RANDOM.nextInt(0, MAX_VALUE);
    }

    @Override
    public int getRed() {
        return red;
    }

    @Override
    public int getGreen() {
        return green;
    }

    @Override
    public int getBlue() {
        return blue;
    }

    @Override
    public Point apply(Point point) {
        return new Point(
            a * point.x() + b * point.y() + c,
            d * point.x() + e * point.y() + f
        );
    }
}
