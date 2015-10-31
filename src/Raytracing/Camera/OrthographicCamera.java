package Raytracing.Camera;

import Raytracing.Ray;
import Vorbereitung.Point3;
import Vorbereitung.Vector3;

public class OrthographicCamera extends Camera {

    private final double s;

    public OrthographicCamera(Point3 e, Vector3 g, Vector3 t, double s) {
        super(e, g, t);
        this.s = s;
    }

    public Ray rayFor(int w, int h, int x, int y) {
        w = w-1;
        final double wHalf = w/2;
        final double a = w/h;
        final double tempX = (x-wHalf) / w;
        final double tempY = (y-wHalf) / w;
        Vector3 posX = u.mul(a*s*tempX);
        Vector3 posY = v.mul(s*tempY);
        return new Ray(e.add(posX).add(posY), g);
    }
}
