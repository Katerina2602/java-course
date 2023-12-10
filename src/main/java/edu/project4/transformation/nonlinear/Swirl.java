package edu.project4.transformation.nonlinear;

import edu.project4.Point;

public class Swirl implements NonLinearTransformation {
    @Override
    public Point apply(Point point) {
        double r = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        double x = point.x() * Math.sin(Math.pow(r, 2)) - point.y() * Math.cos(Math.pow(r, 2));
        double y = point.x() * Math.sin(Math.pow(r, 2)) + point.x() * Math.cos(Math.pow(r, 2));
        return new Point(x, y);
    }
}
