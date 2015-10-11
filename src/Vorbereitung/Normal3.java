package Vorbereitung;


final public class Normal3 {

    public final double x, y, z;

    public Normal3 (double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Normal3 mul(double n) {
        return new Normal3(x*n, y*n, z*n);
    }

    public Normal3 add(Normal3 n) {
        return new Normal3(x+n.x, y+n.y, z+n.z);
    }

    public Vector3 sub(Vector3 v) {
        final double x, y, z;
        x = this.x - v.x;
        y = this.y - v.y;
        z = this.z - v.z;
        return new Vector3(x, y, z);
    }

    public double dot(Vector3 v) {
        return x*v.x + y*v.y + z*v.z;
    }

    public String toString() {
        return String.format("Normal3: (%8.2f, %8.2f, %8.2f)", x, y, z);
    }
}
