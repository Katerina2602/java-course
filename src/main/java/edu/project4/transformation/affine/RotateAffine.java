package edu.project4.transformation.affine;

import edu.project4.Point;
import java.util.Random;

public class RotateAffine implements AffineTransformation {
    private static final Random RANDOM = new Random();
    private static final int MAX_VALUE = 255;
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;
    private int red;
    private int green;
    private int blue;
    private final int theta = 180;

    public RotateAffine() {
        this.a = Math.cos(theta);
        this.b = -Math.sin(theta);
        this.c = 0;
        this.d = Math.sin(theta);
        this.e = Math.cos(theta);
        this.f = 0;
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
