package edu.project4.transformation.nonlinear;

import edu.project4.Point;

public class Sinusoidal implements NonLinearTransformation {

    @Override
    public Point apply(Point point) {
        double x = Math.sin(point.x());
        double y = Math.sin(point.y());
        return new Point(x, y);
    }
}
