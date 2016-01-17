package MathFunc;

/**
 * Normal3 represents normal in three dimensional space
 */
final public class Normal3 {

    /**
     * x - describing the normals x coordinate
     */
    public final double x;
    /**
     * z - describing the normals z coordinate
     */
    public final double y;
    /**
     * z - describing the normals z coordinate
     */
    public final double z;

    /**
     * Standard Constructor of a Normal3
     *
     * @param x X-Coordinate
     * @param y Y-Coordinate
     * @param z Z-Coordinate
     */
    public Normal3(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Multiply a normal with n
     *
     * @param n Multiplier
     * @return New Normal with new x, y, z values
     */
    public Normal3 mul(final double n) {
        return new Normal3(x * n, y * n, z * n);
    }

    /**
     * Add a second Normal3 to the original Normal3
     *
     * @param n Normal3 to add to - must not be null
     * @return New Normal3 that has been added
     */
    public Normal3 add(final Normal3 n) {
        if (n == null) throw new IllegalArgumentException("Must not be null");
        return new Normal3(x + n.x, y + n.y, z + n.z);
    }

    /**
     * Substract Vector3 from this Normal3
     *
     * @param v Vector3 to subtract - must not be null
     * @return new Vector3 that has been subtracted
     */
    public Vector3 sub(final Vector3 v) {
        if (v == null) throw new IllegalArgumentException("Must not be null");
        final double x = this.x - v.x;
        final double y = this.y - v.y;
        final double z = this.z - v.z;
        return new Vector3(x, y, z);
    }

    /**
     * Scalar product of this Normal3 with a Vector3
     *
     * @param v Vector3 - must not be null
     * @return Scalar value from Normal3 and Vector3
     */
    public double dot(final Vector3 v) {
        if (v == null) throw new IllegalArgumentException("Must not be null");
        return x * v.x + y * v.y + z * v.z;
    }

    @Override
    public String toString() {
        return String.format("Normal3: (%8.2f, %8.2f, %8.2f)", x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Normal3 normal3 = (Normal3) o;

        if (Double.compare(normal3.x, x) != 0) return false;
        if (Double.compare(normal3.y, y) != 0) return false;
        return Double.compare(normal3.z, z) == 0;

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
}
