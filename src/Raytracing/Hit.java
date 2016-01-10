package Raytracing;

/**
 * class used for ray hits
 */

import MathFunc.Normal3;
import Raytracing.Geometry.Geometry;

public class Hit {

    /**
     * double representing the smallest t, where the ray hits an object
     */
    public final double t;
    /**
     * Ray representing a ray object
     */
    public final Ray ray;
    /**
     * Geometry representing a geometry object
     */
    public final Geometry geo;

    /**
     * Normal3 represnting a Normal
     */
    public final Normal3 n;

    /**
     * constructor used for a ray hit with the parameters double t, Ray ray, Geometry geo and a Normal3 n
     */
    public Hit(final double t, final Ray ray, final Geometry geo, final Normal3 n) {
        this.n = n;
        this.t = t;
        this.ray = ray;
        this.geo = geo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hit hit = (Hit) o;

        if (Double.compare(hit.t, t) != 0) return false;
        if (ray != null ? !ray.equals(hit.ray) : hit.ray != null) return false;
        return !(geo != null ? !geo.equals(hit.geo) : hit.geo != null);

    }

    @Override
    public String toString() {
        return "Hit{" +
                "t=" + t +
                ", ray=" + ray +
                ", geo=" + geo +
                ", n=" + n +
                '}';
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(t);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (ray != null ? ray.hashCode() : 0);
        result = 31 * result + (geo != null ? geo.hashCode() : 0);
        return result;
    }
}
