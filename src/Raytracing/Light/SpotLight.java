package Raytracing.Light;

import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Color;

import java.util.Vector;

public class SpotLight extends Light {

    /** Point3 used to determine the position of SpotLight */
    public final Point3 position;
    /** Vector3 used to determine the direction of SpotLight */
    public final Vector3 direction;
    /** Double used to determine the angle of SpotLight  */
    public final double halfAngle;


    /** Constructor used to create SpotLight with a Point3 position, a Vector3 direction, a double halfAngle and a Color color */
    public SpotLight(Point3 position, Vector3 direction, double halfAngle, Color color) {
        super(color);
        this.position = position;
        this.direction = direction;
        this.halfAngle = halfAngle;
    }

    @Override
    public boolean illuminates(Point3 p) {
        Vector3 v = p.sub(position);
        double a = v.dot(direction) / (v.magnitude * direction.magnitude);
        return Math.acos(a) <= halfAngle;
    }

    @Override
    public Vector3 directionFrom(Point3 p) {
        return position.sub(p);
    }
}
