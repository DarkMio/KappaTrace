package Raytracing.Camera;

/**
 * OrthographicCamera represents class for orthographic camera objects
 */

import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Ray;
import Raytracing.Sampling.SamplingPattern;

public class OrthographicCamera extends Camera {

    /**
     * double s is used for scaling
     */
    private final double s;

    /**
     * constructor for orthographic camera objects
     */
    public OrthographicCamera(final Point3 e, final Vector3 g, final Vector3 t, final double s, final SamplingPattern pattern) {
        super(e, g, t, pattern);
        this.s = s;
    }

    @Override
    public Ray[] rayFor(int w, int h, final int x, final int y) {
        // Sampling doesn't work?!
        w -= 1;
        h -= 1;
        final double wHalf = w / 2.0;
        final double hHalf = h / 2.0;
        final double a = ((double) w) / h;
        final double tempX = (x - wHalf) / w;
        final double tempY = (y - hHalf) / h;
        Vector3 posX = u.mul(a * s * tempX);
        Vector3 posY = v.mul(s * tempY);
        return new Ray[]{new Ray(e.add(posX).add(posY), g)};
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OrthographicCamera that = (OrthographicCamera) o;

        return Double.compare(that.s, s) == 0;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(s);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
