package edu.project4.transformation.nonlinear;

import edu.project4.Point;

public class Spherical implements NonLinearTransformation {
    @Override
    public Point apply(Point point) {
        double x = point.x() / (Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        double y = point.y() / (Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        return new Point(x, y);
    }
}
