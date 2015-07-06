package Simulator;

import javafx.geometry.Point3D;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Ryoto on 7/7/2015.
 */
public class Supply {
    private Point3D point;
    // protected Point2D point = new Point2D.Double();

    public Supply(double x, double y, double z) {
        point = new Point3D(x, y, z);
    }

    public void getInfo() {
        System.out.print("x:" + String.format("%6.3fm ", point.getX())
                + "y:" + String.format("%6.3fm ", point.getY()));
    }
    public double getX() {
        return point.getX();
    }
    public double getY() {
        return point.getY();
    }
    public Point3D getPoint() {
        return point;
    }
}
