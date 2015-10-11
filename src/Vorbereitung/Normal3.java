package Vorbereitung;


public class Normal3 {

    public final double x, y, z;

    public Normal3 (double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Normal3 mul() {
        return this;
    }
}
