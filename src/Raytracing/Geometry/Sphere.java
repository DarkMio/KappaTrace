package Raytracing.Geometry;

/**
 * class for Sphere objects
 */

import MathFunc.Normal3;
import MathFunc.Point3;
import Raytracing.Epsilon;
import Raytracing.Hit;
import Raytracing.Material.Material;
import Raytracing.Material.Texturing.TexturePosition;
import Raytracing.Ray;

public class Sphere extends Geometry {

    /**
     * Point3 determining the center of a sphere
     */
    public final Point3 c;
    /**
     * double representing the radius of a sphere
     */
    public final double r;

    /**
     * Construct a Sphere
     *
     * @param material Material for color
     * @param c        Point3 for Sphere construction - must not be null
     * @param r        double for plane Sphere - must not be null
     */
    public Sphere(final Material material, final Point3 c, final double r) {
        super(material);
        if (c == null) throw new IllegalArgumentException("must not be null");
        if (r < 0) throw new IllegalArgumentException("must be greater or equals 0");
        this.c = c;
        this.r = r;
    }

    @Override
    public Hit hit(final Ray r) {
        double a = r.d.dot(r.d);
        double b = r.d.dot(r.o.sub(c).mul(2));
        double c = r.o.sub(this.c).dot(r.o.sub(this.c)) - this.r * this.r;
        double d = b * b - 4 * a * c;
        double precision = Epsilon.precisionFor(b, c, a, d);
        if (d == 0) {
            double t = -b / 2 * a;
            Point3 sp = r.at(t);
            double u = 0.5 + Math.atan2(sp.z, sp.x)/(2*Math.PI);
            double v = 0.5 + Math.asin(sp.y) / Math.PI;
            TexturePosition tp =new TexturePosition(u, v);
            return new Hit(t, r, this, normalToRay(r, t), tp);
        }
        if (d + precision > 0) {
            double sqrt = Math.sqrt(d);
            double t = Math.min((-b + sqrt) / (2 * a), (-b - sqrt) / (2 * a));
            if (t < 0)
                return null;

            // https://en.wikipedia.org/wiki/UV_mapping
            Point3 sp = r.at(t);
            Normal3 n = new Normal3(sp.x, sp.y, sp.z);
            double u = 0.5 + Math.atan2(n.z, n.x) / (2*Math.PI);
            double v = 0.5 - Math.asin(n.y) / Math.PI;
            // System.out.println(sp.y);
            TexturePosition tp =new TexturePosition(u, v);
            return new Hit(t, r, this, normalToRay(r, t), tp);
        }
        return null;
    }

    /**
     * @param r Ray
     * @param t double - must not be null
     * @return Normal3 of a point
     */
    protected Normal3 normalToRay(final Ray r, final double t) {
        if (r == null) throw new IllegalArgumentException("must not be null");
        return r.at(t).sub(c).asNormal();
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
