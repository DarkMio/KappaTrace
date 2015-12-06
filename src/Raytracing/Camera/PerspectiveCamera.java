package Raytracing.Camera;

/**
 * PerspectiveCamera represents class for perspective camera objects
 */
import Raytracing.Ray;
import MathFunc.Point3;
import MathFunc.Vector3;

public class PerspectiveCamera extends Camera {

    /** double angle determining the opening angle of the camera */
    private final double angle;

    /** constructor for perspective camera objects */
    public PerspectiveCamera(final Point3 e, final Vector3 g, final Vector3 t, final double angle) {
        super(e, g, t);
        this.angle = angle;
    }

    @Override
    public Ray rayFor(final int w, final int h, final int x, final int y) {
        Vector3 tempX = u.mul(x - (w-1.0)/2.0);
        Vector3 tempY = v.mul(y - (h-1.0)/2.0);
        Vector3 tempZ = this.w.mul(-1.0).mul((h/2.0) / Math.tan(angle));
        Vector3 d = (tempZ.add(tempY).add(tempX)).normalized();
        return new Ray(e, d);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PerspectiveCamera that = (PerspectiveCamera) o;

        return Double.compare(that.angle, angle) == 0;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(angle);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
