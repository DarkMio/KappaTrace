package Raytracing.Camera;

import MathFunc.Mat3x3;
import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Ray;
import Raytracing.Sampling.SamplingPattern;

/**
 * Created by Mio on 30/12/2015.
 */
public class FishEyeCamera extends Camera {

    public final double angle;

    public FishEyeCamera(Point3 e, Vector3 g, Vector3 t, double angle, SamplingPattern pattern) {
        super(e, g, t, pattern);
        this.angle = angle;
    }

    @Override
    public Ray[] rayFor(int w, int h, int x, int y) {
        final int a = Integer.min(w, h);
        if (x >= (w - a) / 2 && x <= (w - (w - a - 1) / 2) && y >= (h - a) / 2 && y <= (h - (h - a - 1) / 2)) {
            final double denominatorU = ((double) (a - 1));
            final double numeratorU = (double) (x - ((w - a) / 2)) - denominatorU / 2.0;
            final double newX = numeratorU / denominatorU;

            final double denominatorV = ((double) (a - 1));
            final double numeratorV = (double) (y - ((h - a) / 2)) - denominatorV / 2.0;
            final double newY = numeratorV / denominatorV;

            //final double newX = ((double)(2 * x)) / ((double)(w - 1));
            //final double newY = ((double)(2 * y)) / ((double)(h - 1));
            final double rad = Math.sqrt(newX * newX + newY * newY);
            if (rad >= 0.0 && rad <= 0.5) {

                final double phi;
                if (rad == 0.0) {
                    phi = 0.0;
                } else if (newX < 0.0) {
                    phi = Math.PI - Math.asin(newY / rad);
                } else {
                    phi = Math.asin(newY / rad);
                }
                final double theta = rad * angle / 2.0;

                final Vector3 vecX = super.u.mul(Math.sin(theta) * Math.cos(phi));
                final Vector3 vecY = super.v.mul(Math.sin(theta) * Math.sin(phi));
                final Vector3 vecZ = super.w.mul(-1).mul(Math.cos(theta));
                //final Vector3 r = new Vector3(Math.sin(theta) * Math.cos(phi), Math.sin(theta) * Math.sin(phi), Math.cos(theta));
                final Vector3 r = vecX.add(vecY).add(vecZ);
                return new Ray[]{new Ray(this.e, r.normalized())};
            } else {
                return new Ray[0];
            }
        } else {
            return new Ray[0];
        }
    }
}
