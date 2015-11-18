package Raytracing.Light;

import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Color;

public class PointLight extends Light {

    public final Point3 position;

    public PointLight(Point3 position, Color color) {
        super(color);
        this.position = position;
    }

    @Override
    public boolean illuminate(Point3 p) {
        return true;
    }

    @Override
    public Vector3 directionFrom(Point3 p) {
        return null;
    }
}
