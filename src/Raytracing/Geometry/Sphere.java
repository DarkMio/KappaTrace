package Raytracing.Geometry;

/**
 * class for Sphere objects
 */
import MathFunc.Point3;
import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.Ray;

public class Sphere extends Geometry {

    /** Point3 determining the center of a sphere */
    public final Point3 c;
    /** double representing the radius of a sphere */
    public final double r;

    /** constructor used to create sphere objects with a Color color, a Point3 c and a double r */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Sphere sphere = (Sphere) o;

        if (Double.compare(sphere.r, r) != 0) return false;
        return !(c != null ? !c.equals(sphere.c) : sphere.c != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (c != null ? c.hashCode() : 0);
        temp = Double.doubleToLongBits(r);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
