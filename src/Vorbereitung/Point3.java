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
