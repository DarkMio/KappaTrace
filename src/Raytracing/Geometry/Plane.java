package Raytracing.Geometry;

/**
 * class for Plane objects
 */

import MathFunc.Normal3;
import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Epsilon;
import Raytracing.Hit;
import Raytracing.Material.Material;
import Raytracing.Material.Texturing.TexCoord2;
import Raytracing.Ray;

public class Plane extends Geometry {

    /**
     * known Point3 on the plane
     */
    public final Point3 a;
    /**
     * normal used to span plane
     */
    public final Normal3 n;

    /**
     * Construct a Plane
     *
     * @param material Material for color
     * @param a        Point3 for plane construction - must not be null
     * @param n        Normal3 for plane construction - must not be null
     */
    public Plane(final Material material, final Point3 a, final Normal3 n) {
        super(material);
        if (a == null) throw new IllegalArgumentException("must not be null");
        if (n == null) throw new IllegalArgumentException("must not be null");
        this.a = a;
        this.n = n;
    }

    @Override
    public Hit hit(final Ray r) {
        double lower = r.d.dot(n); // was unter dem Bruch steht
        double upper = n.dot(new Vector3(a.x - r.o.x, a.y - r.o.y, a.z - r.o.z)); // Was �ber dem Bruch steht
        if (lower == 0) return null; // You cannot divide through zero, thanks.
        double t = upper / lower;
        if (t < Epsilon.PRECISION) return null;
        Point3 pos = r.at(t);
        TexCoord2 tPos = new TexCoord2((1-n.x)*pos.x, (1-n.y)*pos.y - (1-n.z)*pos.z );
        return new Hit(t, r, this, n, tPos);
    }

    private TexCoord2 calcTexturePosition(double t, Ray r) {
        Point3 pos = r.at(t);
        Normal3 n = new Normal3(pos.x, pos.y, pos.z);
        TexCoord2 tPos = new TexCoord2(n.x, n.z);
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Plane plane = (Plane) o;

        if (a != null ? !a.equals(plane.a) : plane.a != null) return false;
        return !(n != null ? !n.equals(plane.n) : plane.n != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (a != null ? a.hashCode() : 0);
        result = 31 * result + (n != null ? n.hashCode() : 0);
        return result;
    }
}
