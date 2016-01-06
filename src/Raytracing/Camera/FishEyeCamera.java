package Raytracing.Camera;

import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Ray;
import Raytracing.Sampling.SamplingPattern;

/**
 * Created by Mio on 30/12/2015.
 */
public class FishEyeCamera extends Camera {

    public final double s;
    public final double maxPsi;

    public FishEyeCamera(final Point3 e, final Vector3 g, final Vector3 t, final double s, final double maxPsi, final SamplingPattern pattern) {
        super(e, g, t, pattern);
        this.s = s;
        this.maxPsi = maxPsi;
    }


    @Override
    public Ray[] rayFor(int w, int h, int x, int y) {
        return new Ray[0];
        /*
        Vector3 d;
        double x_t = 2.0 / (s * h) * x;
        double y_t = 2.0 / (s * w) * y;
        double rSquared = x_t * x_t + y_t * y_t;
        if (rSquared <= 1) {
            double r = Math.sqrt(rSquared);
            double psi = r * maxPsi * Math.PI/180;
            double sinPsi = Math.sin(psi);
            double cosPsi = Math.cos(psi);
            double sinAlpha = y_t / r;
            double cosAlpha = x_t / r;
//            rd.direction = uvw.u.mult(sinPsi * cosAlpha).plus(uvw.v.mult(sinPsi * sinAlpha)).minus(uvw.w.mult(cosPsi));
            d = u.mul(sinPsi * cosAlpha).add(v.mul(sinPsi * sinAlpha)).add(this.w.mul(-1).mul(cosPsi));

//          rd.direction = uvw.pm(sinPsi * cosAlpha, sinPsi * sinAlpha, cosPsi);
//          rd.rSquared = rSquared;
        } else {
            d = new Vector3(0, 0, 0);
        }
        return new Ray(e, d);
        // return rd;
*/
    }
}
