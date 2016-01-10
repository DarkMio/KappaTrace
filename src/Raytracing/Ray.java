package Raytracing;

import MathFunc.Point3;
import MathFunc.Vector3;

public class Ray {

    /**
     * Point3 representing the origin of a ray
     */
    final public Point3 o;
    /**
     * Vector3 representing the direction of a ray
     */
    final public Vector3 d;

    /**
     * constructor used for a ray object with Point3 o and Vector3 d
     */
    public Ray(final Point3 o, final Vector3 d) {
        this.o = o;
        this.d = d;
    }

    /**
     * Subtracts double t from Vector3 d and adds it to Point3, returning the position
     */
    public Point3 at(final double t) {
        return o.add(d.mul(t));
    }

    /**
     * Returns ray at position p
     */
    public double tOf(final Point3 p) {
        return (p.x - o.x) / d.x;
    }

    @Override
    public boolean equals(Object o1) {
        if (this == o1) return true;
        if (o1 == null || getClass() != o1.getClass()) return false;

        Ray ray = (Ray) o1;

        if (o != null ? !o.equals(ray.o) : ray.o != null) return false;
        return !(d != null ? !d.equals(ray.d) : ray.d != null);

    }

    @Override
    public int hashCode() {
        int result = o != null ? o.hashCode() : 0;
        result = 31 * result + (d != null ? d.hashCode() : 0);
        return result;
    }
}
