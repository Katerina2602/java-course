package edu.project4.transformation.nonlinear;

import edu.project4.Point;

public class Horseshoe implements NonLinearTransformation {
    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        double x = (1 / r) * (point.x() - point.y()) * (point.x() - point.y());
        double y = 2 * point.x() * point.y();
        return new Point(x, y);
    }
}
