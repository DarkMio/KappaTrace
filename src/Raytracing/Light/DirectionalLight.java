package Raytracing.Light;

import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Color;

public class DirectionalLight extends Light {

    /** Vector3 representing the direction of the DirectionalLight */
    public final Vector3 direction;

    /** Constructor used to create directional light with a Vector3 direction and a Color color */
    public DirectionalLight(Vector3 direction, Color color) {
        super(color);
        this.direction = direction;
    }

    @Override
    public boolean illuminates(Point3 p) {
        return true;
    }

    @Override
    public Vector3 directionFrom(Point3 p) {
        return direction.mul(-1);
    }
}
