package Vorbereitung;

final public class Vector3 {

    public final double x, y, z, magnitude;

    public Vector3(double x, double y, double z) {
        this(x, y, z, Math.sqrt(x*x + y*y + z*z));
    }

    public Vector3(double x, double y, double z, double magnitude) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.magnitude = magnitude;
    }

    public Vector3 add(Vector3 v) {
        final double x, y, z;
        x = this.x + v.x;
        y = this.y + v.y;
        z = this.z + v.z;
        return new Vector3(x, y, z);
    }

    public Vector3 add(Normal3 n) {
        final double x, y, z;
        x = this.x + n.x;
        y = this.y + n.y;
        z = this.z + n.z;
        return new Vector3(x, y, z);
    }

    public Vector3 sub(Normal3 n) {
        final double x, y, z;
        x = this.x - n.x;
        y = this.y - n.y;
        z = this.z - n.z;
        return new Vector3(x, y, z);
    }

    public Vector3 mul(double c) {
        return new Vector3(c*x, c*y, c*z);
    }

    public double dot(Vector3 v) {
        return x*v.x + y*v.y + z*v.z;
    }

    public double dot(Normal3 n) {
        return x*n.x + y*n.y + z*n.z;
    }

    public Vector3 normalized() {
        return mul(1/magnitude);
    }

    public Normal3 asNormal() {
        return new Normal3(x, y, z);
    }

    /** https://asalga.wordpress.com/2012/09/23/understanding-vector-reflection-visually/
     *
     * @param n Normal to be reflected on
     * @return Reflected Vector on Normal
     */
    public Vector3 reflectedOn(Normal3 n) {
        double x = 2* n.dot(this);
        Normal3 y = n.mul(x);
        return y.sub(this);
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
