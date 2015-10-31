package Raytracing.Camera;

import Raytracing.Ray;
import MathFunc.Point3;
import MathFunc.Vector3;

public class PerspectiveCamera extends Camera {

    private final double angle;

    public PerspectiveCamera(Point3 e, Vector3 g, Vector3 t, double angle) {
        super(e, g, t);
        this.angle = angle;
    }

    public Ray rayFor(int w, int h, int x, int y) {
        w -= 1;
        Vector3 tempX = u.mul(x - w/2);
        Vector3 tempY = v.mul(y - w/2);
        Vector3 tempZ = this.w.mul(h/2 / Math.tan(angle));
        Vector3 d = (tempZ.add(tempY).add(tempX)).normalized();
        return new Ray(e, d);
    }
}
