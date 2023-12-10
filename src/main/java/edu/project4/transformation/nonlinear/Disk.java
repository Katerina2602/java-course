package edu.project4.transformation.nonlinear;

import edu.project4.Point;

public class Disk implements NonLinearTransformation {
    @Override
    public Point apply(Point point) {
        double x =
            (1 / Math.PI) * Math.atan(point.y() / point.x()) * Math.sin(Math.PI * Math.sqrt(Math.pow(point.x(), 2)
                + Math.pow(point.y(), 2)));
        double y =
            (1 / Math.PI) * Math.atan(point.y() / point.x()) * Math.cos(Math.PI * Math.sqrt(Math.pow(point.x(), 2)
                + Math.pow(point.y(), 2)));
        return new Point(x, y);
    }
}
