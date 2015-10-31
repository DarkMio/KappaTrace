package Raytracing.Camera;

import Raytracing.Ray;
import Vorbereitung.Point3;
import Vorbereitung.Vector3;

public class PerspectiveCamera extends Camera {

    private final double angle;

    public PerspectiveCamera(Point3 e, Vector3 g, Vector3 t, double angle) {
        super(e, g, t);
        this.angle = angle;
    }

    public Ray rayFor(int w, int h, int x, int y) {

    }
}
