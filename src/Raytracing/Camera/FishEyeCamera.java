package Raytracing.Camera;

import MathFunc.Point2;
import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Ray;
import Raytracing.Sampling.SamplingPattern;

import java.util.ArrayList;

public class FishEyeCamera extends Camera {

    private final double angle;

    public FishEyeCamera(Point3 e, Vector3 g, Vector3 t, double angle, SamplingPattern pattern) {
        super(e, g, t, pattern);
        this.angle = angle;
    }

    @Override
    public Ray[] rayFor(int w, int h, int x, int y) {
        Point2[] samplePoints = pattern.generatePattern();
        ArrayList<Ray> rays = new ArrayList<>();
        for (int i = 0; i < samplePoints.length; i++) {
            Point2 sp = samplePoints[i];
            final int maxSize = Math.max(2 * w, h);
            final int wa = w - maxSize;
            final double nU = (x + sp.x - (wa / 2)) - (maxSize) / 2.0;
            final double fracX = nU / (maxSize - i);
            final double nV = (y + sp.y - ((h - maxSize) / 2)) - (maxSize - 1) / 2.0;
            final double fracY = nV / (maxSize - 1);
            final double rad = Math.sqrt(fracX * fracX + fracY * fracY);
            final double phi;
            if (rad == 0) phi = 0.;
            else if (fracX < 0) phi = Math.PI - Math.asin(fracY / rad);
            else phi = Math.asin(fracY / rad);
            final Vector3 vecX = super.u.mul(Math.sin(rad * angle / 2) * Math.cos(phi));
            final Vector3 vecY = super.v.mul(Math.sin(rad * angle / 2) * Math.sin(phi));
            final Vector3 vecZ = super.w.mul(-1).mul(Math.cos(rad * angle / 2));
            final Vector3 r = vecX.add(vecY).add(vecZ);
            rays.add(new Ray(this.e, r.normalized()));
        }

        return rays.toArray(new Ray[rays.size()]);
    }
}