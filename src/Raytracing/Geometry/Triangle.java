package Raytracing.Geometry;

import MathFunc.Point3;
import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.Ray;

/**
 * Created by Mio on 31/10/2015.
 */
public class Triangle extends Geometry {

    public final Point3 a, b, c;

    public Triangle(Color color, Point3 a, Point3 b, Point3 c) {
        super(color);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public Hit hit(Ray r) {
        return null;
    }
}
