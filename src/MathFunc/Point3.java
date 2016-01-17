package MathFunc;

/**
 * Point3 represents a point in three dimensional space
 */
final public class Point3 {

    /**
     * x - describing the Point3s x coordinate
     */
    public final double x;
    /**
     * y - describing the Point3s z coordinate
     */
    public final double y;
    /**
     * z - describing the Point3s z coordinate
     */
    public final double z;

    /**
     * Standard constructor of Point3
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @param z z-coordinate
     */
    public Point3(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Subtract a Point3 from this Vector3
     *
     * @param p Point3 that should be subtracted - must not be null
     * @return resulting new Vector3
     */
    public Vector3 sub(final Point3 p) {
        if (p == null) throw new IllegalArgumentException("Must not be null");
        final double x, y, z;
        x = this.x - p.x;
        y = this.y - p.y;
        z = this.z - p.z;
        return new Vector3(x, y, z);
    }

    /**
     * http://geomalgorithms.com/points_and_vectors.html#Vector-Addition
     * "The resulting point Q is considered to be the displacement,
     * or �translation�, of the point P in the direction of and by
     * the magnitude of the vector"
     *
     * @param v A vector to translate - must not be null
     * @return A Point3 translated from v
     */
    public Point3 sub(final Vector3 v) {
        if (v == null) throw new IllegalArgumentException("Must not be null");
        final double x, y, z;
        x = this.x - v.x;
        y = this.y - v.y;
        z = this.z - v.z;
        return new Point3(x, y, z);
    }

    public Point3 x(final Normal3 v) {
        return new Point3(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point3 point3 = (Point3) o;

        if (Double.compare(point3.x, x) != 0) return false;
        if (Double.compare(point3.y, y) != 0) return false;
        return Double.compare(point3.z, z) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Subtract a Vector3 from this Point3
     *
     * @param v Vector3 that should be added - must not be null
     * @return resulting new Point3
     */
    public Point3 add(Vector3 v) {
        if (v == null) throw new IllegalArgumentException("Must not be null");
        final double x, y, z;
        x = this.x + v.x;
        y = this.y + v.y;
        z = this.z + v.z;
        return new Point3(x, y, z);
    }

    @Override
    public String toString() {
        return String.format("Point3:  (%8.2f, %8.2f, %8.2f)", x, y, z);
    }
}
