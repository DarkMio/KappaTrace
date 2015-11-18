package Raytracing.Light;

import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Color;

public class SpotLight extends Light {

    public final Point3 position;
    public final Vector3 direction;
    public final double halfAngle;


    public SpotLight(Point3 position, Vector3 direction, double halfAngle, Color color) {
        super(color);
        this.position = position;
        this.direction = direction;
        this.halfAngle = halfAngle;
    }

    @Override
    public boolean illuminate(Point3 p) {
        return false;
    }

    @Override
    public Vector3 directionFrom(Point3 p) {
        return null;
    }
}
