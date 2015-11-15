package MathFunc;

final public class Vector3 {

    /**
     * x, y, z, magnitude of Vector3
     */
    public final double x, y, z, magnitude;

    /**
     * Constructor of Vector3 which calculates the magnitude
     * @param x x-coordinate
     * @param y y-coordinate
     * @param z z-coordinate
     */
    public Vector3(double x, double y, double z) {
        this(x, y, z, Math.sqrt(x*x + y*y + z*z));
    }

    /**
     * Standard Constructor of Vector3
     * @param x x-coordinate
     * @param y y-coordinate
     * @param z z-coordinate
     * @param magnitude magnitude of Vector3
     */
    public Vector3(double x, double y, double z, double magnitude) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.magnitude = magnitude;
    }

    /**
     * Add a Vector3 to this Vector3
     * @param v Vector3 to be added
     * @return resulting new Vector3
     */
    public Vector3 add(Vector3 v) {
        if (v == null) throw new IllegalArgumentException("v must not be null.");
        return new Vector3(x + v.x, y + v.y, z + v.z);
    }

    /**
     * Add a Normal3 to this Vector3
     * @param n Normal3 that should be added
     * @return resulting new Vector3
     */
    public Vector3 add(Normal3 n) {
        if (n == null) throw new IllegalArgumentException("n must not be null.");
        return new Vector3(x + n.x, y + n.y, z + n.z);
    }

    /**
     * Subtracting a Normal3 from this Vector3
     * @param n Normal3 to be subtracted
     * @return resulting new Vector3
     */
    public Vector3 sub(Normal3 n) {
        if (n == null) throw new IllegalArgumentException("n must not be null.");
        return new Vector3(x - n.x, y - n.y, z - n.z);
    }

    /**
     * Multiply with a constant
     * @param c Constant to be multiplied
     * @return resulting new Vector
     */
    public Vector3 mul(double c) {
        return new Vector3(x*c, y*c, z*c);
    }

    /**
     * Scalar product of this Vector3 with a Vector3
     * @param v Vector3
     * @return resulting scalar prodict
     */
    public double dot(Vector3 v) {
        if (v == null) throw new IllegalArgumentException("v must not be null.");
        return x*v.x + y*v.y + z*v.z;
    }

    /**
     * Scalar product of this Vector3 with a Normal3
     * @param n Normal3
     * @return resulting scalar product
     */
    public double dot(Normal3 n) {
        if (n == null) throw new IllegalArgumentException("n must not be null.");
        return x*n.x + y*n.y + z*n.z;
    }

    /**
     * Normalizes this vector to have a magnitude of 1
     * @return resulting new Vector3
     */
    public Vector3 normalized() {
        return mul(1/magnitude);
    }

    /**
     * This Vector3 as Normal3
     * @return resulting new Normal3
     */
    public Normal3 asNormal() {
        Vector3 temp = this.normalized();
        return new Normal3(temp.x, temp.y, temp.z);
    }

    /** https://asalga.wordpress.com/2012/09/23/understanding-vector-reflection-visually/
     *
     * @param n Normal to be reflected on
     * @return Reflected Vector on Normal
     */
    public Vector3 reflectedOn(Normal3 n) {
        if (n == null) throw new IllegalArgumentException("n must not be null.");
        double x = 2 * n.dot(this);
        Normal3 temp = n.mul(x);
        return temp.sub(this);
    }

    public Vector3 x(Vector3 v) {
        if (v == null) throw new IllegalArgumentException("v must not be null.");
        return new Vector3(y*v.z - z*v.y, z*v.x - x*v.z, x*v.y - y*v.x);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector3 vector3 = (Vector3) o;

        if (Double.compare(vector3.x, x) != 0) return false;
        if (Double.compare(vector3.y, y) != 0) return false;
        if (Double.compare(vector3.z, z) != 0) return false;
        return Double.compare(vector3.magnitude, magnitude) == 0;

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
        temp = Double.doubleToLongBits(magnitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public String toString() {
        return String.format("Vector3: (%8.2f, %8.2f, %8.2f) | mag %-8.2f", x, y, z, magnitude);
    }
}
