package Vorbereitung;

final public class Point3 {

    public final double x, y, z;

    public Point3 (double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 sub(Point3 p) {
        final double x, y, z;
        x = this.x - p.x;
        y = this.y - p.y;
        z = this.z - p.z;
        return new Vector3(x, y, z);
    }

    /**
     * http://geomalgorithms.com/points_and_vectors.html#Vector-Addition
     * "The resulting point Q is considered to be the displacement,
     * or “translation”, of the point P in the direction of and by the magnitude of the vector"
     * @param v A vector to translate
     * @return A Point3 translated from v
     */
    public Point3 sub(Vector3 v) {
        final double x, y, z;
        x = this.x - v.x;
        y = this.y - v.y;
        z = this.z - v.z;
        return new Point3(x, y, z);
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

    public Point3 add(Vector3 v) {
        final double x, y, z;
        x = this.x + v.x;

        y = this.y + v.y;
        z = this.z + v.z;
        return new Point3(x, y, z);
    }

    public String toString() {
        return String.format("Point3:  (%8.2f, %8.2f, %8.2f)", x, y, z);
    }
}
