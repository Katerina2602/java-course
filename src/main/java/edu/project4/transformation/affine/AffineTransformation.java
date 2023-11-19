package edu.project4.transformation.affine;

import edu.project4.Point;
import java.util.function.Function;

public interface AffineTransformation extends Function<Point, Point> {
    int getRed();

    int getGreen();

    int getBlue();
}
