package Raytracing.Light;

import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Color;

public class PointLight extends Light {

    /** Point3 used to determine the position of PointLight */
    public final Point3 position;

    /** Constructor used to create PointLight with a Point3 position and a Color color */
    public PointLight(Point3 position, Color color) {
        super(color);
        this.position = position;
    }

    @Override
    public boolean illuminates(Point3 p) {
        return true;
    }

    @Override
    public Vector3 directionFrom(Point3 p) {
        return position.sub(p).normalized();
    }
}
