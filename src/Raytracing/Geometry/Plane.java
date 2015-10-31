package Raytracing.Geometry;

import MathFunc.Normal3;
import MathFunc.Point3;
import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.Ray;

public class Plane extends Geometry {

    public final Point3 a;
    public final Normal3 n;

    public Plane(Color color, Point3 a, Normal3 n) {
        super(color);
        this.a = a;
        this.n = n;
    }

    @Override
    public Hit hit(Ray r) {
        return null;
    }
}
