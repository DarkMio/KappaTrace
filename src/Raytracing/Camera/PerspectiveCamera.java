package Raytracing.Camera;

/**
 * PerspectiveCamera represents class for perspective camera objects
 */
import MathFunc.Point2;
import Raytracing.Ray;
import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Sampling.SamplingPattern;

public class PerspectiveCamera extends Camera {

    /** double angle determining the opening angle of the camera */
    private final double angle;

    /** constructor for perspective camera objects */
    public PerspectiveCamera(final Point3 e, final Vector3 g, final Vector3 t, final double angle, SamplingPattern pattern) {
        super(e, g, t, pattern);
        this.angle = angle/2;
    }

    @Override
    public Ray[] rayFor(final int w, final int h, final int x, final int y) {
        Ray[] rays = new Ray[pattern.sampleResolution * pattern.sampleResolution];
        Point2[] samplePoints = pattern.generatePattern();
        for(int i = 0; i < rays.length; i++) {
            Point2 offset = samplePoints[i];
            Vector3 tempX = u.mul((x + offset.x) - (w-1.0)/2.0);
            Vector3 tempY = v.mul((y + offset.y) - (h-1.0)/2.0);
            Vector3 tempZ = this.w.mul(-1.0).mul((h/2.0) / Math.tan(angle));
            Vector3 d = (tempZ.add(tempY).add(tempX)).normalized();
            rays[i] = new Ray(e, d);
        }
        return rays;
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
