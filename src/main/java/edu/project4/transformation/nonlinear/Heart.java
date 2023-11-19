package edu.project4.transformation.nonlinear;

import edu.project4.Point;

public class Heart implements NonLinearTransformation {
    @Override
    public Point apply(Point point) {
        double x =
            Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2)) * Math.sin(Math.sqrt(Math.pow(point.x(), 2)
                + Math.pow(point.y(), 2)) * Math.atan(point.y() / point.x()));
        double y =
            -Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2)) * Math.cos(Math.sqrt(Math.pow(point.x(), 2)
                + Math.pow(point.y(), 2)) * Math.atan(point.y() / point.x()));
        return new Point(x, y);
    }
}
