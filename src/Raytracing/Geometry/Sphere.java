package Raytracing.Geometry;

import MathFunc.Point3;
import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.Ray;

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
        double a = r.d.dot(r.d);
        double b = r.o.sub(c).mul(2).dot(r.d);
        double c = r.o.sub(this.c).dot(r.o.sub(this.c)) - this.r*this.r;
        double d = b*b - 4*a*c;
        double precision = precisionFor(b, d);
        if (d - precision < 0) return null;
        if (d == 0) return new Hit(-b/(2*a), r, this);
        if (-b + precision > d - precision) return new Hit((-b - d)/2*a, r, this);
        return new Hit((-b+d)/2*a, r, this);
    }
}
