package Raytracing.Geometry;

import MathFunc.Point3;
import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.Ray;

/**
 * Created by Mio on 31/10/2015.
 */
public class Sphere extends Geometry {

    public final Point3 c;
    public final double r;

    public Sphere(Color color, Point3 c, double r) {
        super(color);
        this.c = c;
        this.r = r;
    }

    @Override
    public Hit hit(Ray r) {
        return null;
    }
}
