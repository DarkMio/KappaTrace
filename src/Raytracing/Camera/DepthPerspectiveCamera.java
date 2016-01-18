package Raytracing.Camera;

/**
 * PerspectiveCamera represents class for perspective camera objects
 */

import MathFunc.Normal3;
import MathFunc.Point2;
import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Ray;
import Raytracing.Sampling.SamplingPattern;

import java.util.ArrayList;

public class DepthPerspectiveCamera extends Camera {

    /**
     * double angle determining the opening angle of the camera
     */
    private final double angle;
    private final double lensAngle;
    private final double focalPoint;

    /**
     * constructor for perspective camera objects
     */
    public DepthPerspectiveCamera(final Point3 e, final Vector3 g, final Vector3 t, final double angle, SamplingPattern pattern, double lensAngle, double focalPoint) {
        super(e, g, t, pattern);
        this.angle = angle / 2;
        this.lensAngle = lensAngle;
        this.focalPoint = focalPoint;
    }


    @Override
    public Ray[] rayFor(int w, int h, int x, int y) {
        // Get pattern
        Point2[] samples = pattern.generatePattern();
        // Rays should be pattern^2 -> focus on specific point -> pattern again
        Ray[] rays = new Ray[samples.length * samples.length];
        //from perspective camera:
        double uOff = (w - 1.0) / 2.0;               // (py-y) * v
        double vOff = (h - 1.0) / 2.0;              // (px-x) * u
        double wOff = (h / 2.0) / Math.tan(angle); // z * w
        for(int i = 0; i < samples.length; i++) {
            Point2 samplePoint = samples[i];
            // New Camera ray position
            Point2 rayPos = new Point2(x - uOff + samplePoint.x, y - vOff + samplePoint.y);
            // Point at focal:
            Point2 focalPos = new Point2(rayPos.x * focalPoint / wOff, rayPos.y * focalPoint / wOff);
            // same as perspective camera
            Vector3 tempX = u.mul(focalPos.x);
            Vector3 tempY = v.mul(focalPos.y);
            Vector3 tempZ = this.w.mul(-1.0).mul(focalPoint);
            // Rays should now point at the focal point
            Point3 d = e.add(tempZ.add(tempY).add(tempX));
            // now we need to sample this again?
            for(int j = 0; j < samples.length; j++) {
                Point2 secondSample = samples[i];
                Vector3 newU = u.mul(secondSample.x * lensAngle);
                Vector3 newV = v.mul(secondSample.y * lensAngle);
                // new origin, direction
                Point3 o = this.e.add(newU).add(newV);
                Vector3 newD = d.sub(o).normalized();
                rays[i*samples.length+j] = new Ray(o, newD);
            }
        }
        return rays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DepthPerspectiveCamera that = (DepthPerspectiveCamera) o;

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