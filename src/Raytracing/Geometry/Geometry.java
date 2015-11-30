package Raytracing.Geometry;

/**
 * class for geometric functions
 */
import Raytracing.Hit;
import Raytracing.Material.Material;
import Raytracing.Ray;

public abstract class Geometry {

    /** epsilon precision of doubles */
    public final static double PRECISION = 0.0000001;
    /** Material representing material */
    public final Material material;

    /** abstract constructor for geometry */
    public Geometry(Material material) {
        this.material = material;
    }

    /** abstract constructor for a ray hit */
    public abstract Hit hit(Ray r);

    /** function calculation precision for 1 double */
    public static double precisionFor(double a) {
        return PRECISION * Math.abs(a);
    }

    /** function calculation precision for 2 doubles */
    public static double precisionFor(double a, double b) {
        return PRECISION * Math.max(Math.abs(a), Math.abs(b));
    }

    /** function calculation precision for an array of doubles */
    public static double precisionFor(double... values) {
        double max = 0;
        for(double value: values) {
            double abs = Math.abs(value);
            if (max < abs) max = abs;
        }
        return PRECISION * max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Geometry geometry = (Geometry) o;

        return !(material != null ? !material.equals(geometry.material) : geometry.material != null);

    }

    @Override
    public int hashCode() {
        return material != null ? material.hashCode() : 0;
    }
}
