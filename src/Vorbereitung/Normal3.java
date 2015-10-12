package Vorbereitung;


final public class Normal3 {

    /** x, y, z describing the normals position
     */
    public final double x, y, z;

    /**
     * Standard Constructor of a Normaö
     * @param x X-Coordinate
     * @param y Y-Coordinate
     * @param z Z-Coordinate
     */
    public Normal3 (double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Multiply a normal with n
     * @param n Multiplier
     * @return New Normal with new x, y, z values
     */
    public Normal3 mul(double n) {
        return new Normal3(x*n, y*n, z*n);
    }

    /**
     * Add a second Normal3 to the original Normal3
     * @param n Normal3 to add to
     * @return New Normal3 that has been added
     */
    public Normal3 add(Normal3 n) {
        return new Normal3(x+n.x, y+n.y, z+n.z);
    }

    /**
     * Substract Vector3 from this Normal3
     * @param v Vector3 to subtract
     * @return new Vector3 that has been subtracted
     */
    public Vector3 sub(Vector3 v) {
        final double x, y, z;
        x = this.x - v.x;
        y = this.y - v.y;
        z = this.z - v.z;
        return new Vector3(x, y, z);
    }

    /**
     * Scalar product of this Normal3 with a Vector3
     * @param v Vector3
     * @return Scalar value from Normal3 and Vector3
     */
    public double dot(Vector3 v) {
        return x*v.x + y*v.y + z*v.z;
    }

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
