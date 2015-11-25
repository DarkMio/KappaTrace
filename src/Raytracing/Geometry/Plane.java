package Raytracing.Geometry;

/**
 * class for Plane objects
 */
import MathFunc.Normal3;
import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.Material.Material;
import Raytracing.Ray;

public class Plane extends Geometry {

    /** known Point3 on the plane */
    public final Point3 a;
    /** normal used to span plane */
    public final Normal3 n;

    /** constructor for plane objects with a Color color, a Point3 a and a Normal3 n*/
    public Plane(Material material, Point3 a, Normal3 n) {
        super(material);
        this.a = a;
        this.n = n;
    }

    @Override
    public Hit hit(Ray r) {
        double lower = r.d.dot(n); // was unter dem Bruch steht
        double upper = n.dot(new Vector3(a.x - r.o.x, a.y - r.o.y, a.z - r.o.z)); // Was �ber dem Bruch steht
        if (lower == 0) return null; // You cannot divide through zero, thanks.
        double t = upper/lower;
        if(t>= 0) return new Hit(t, r, this, n);
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
