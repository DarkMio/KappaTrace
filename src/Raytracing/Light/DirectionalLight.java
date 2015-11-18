package Raytracing.Light;

import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Color;

public class DirectionalLight extends Light {

    public final Vector3 direction;

    public DirectionalLight(Vector3 direction, Color color) {
        super(color);
        this.direction = direction;
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
