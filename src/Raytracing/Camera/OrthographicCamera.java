package Raytracing.Camera;

/**
 * OrthographicCamera represents class for orthographic camera objects
 */
import Raytracing.Ray;
import MathFunc.Point3;
import MathFunc.Vector3;

public class OrthographicCamera extends Camera {

    /** double s is used for scaling */
    private final double s;

    /** constructor for orthographic camera objects */
    public OrthographicCamera(Point3 e, Vector3 g, Vector3 t, double s) {
        super(e, g, t);
        this.s = s;
    }

    @Override
    public Ray rayFor(int w, int h, int x, int y) {
        w -= 1;
        h -= 1;
        final double wHalf = w/2.0;
        final double hHalf = h/2.0;
        final double a = ((double)w)/h;
        final double tempX = (x-wHalf) / w;
        final double tempY = (y-hHalf) / h;
        Vector3 posX = u.mul(a*s*tempX);
        Vector3 posY = v.mul(s*tempY);
        return new Ray(e.add(posX).add(posY), g);
    }

    /*
        @Override
    public Ray rayFor(int w, int h, int x, int y) {
        final double a = w/h;
        final double tempU = a * s * ((x-(h-1)/2)/(h-1));
        final Vector3 tempUV = u.mul(tempU);
        final double tempV = s*((y-(w-1)/2)/(w-1));
        final Vector3 tempVV = v.mul(tempV);
        final Point3 o = e.add(tempUV).add(tempVV);

        return new Ray(o, this.w.mul(-1));
    }
     */

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
