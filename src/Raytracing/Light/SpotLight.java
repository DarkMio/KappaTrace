package Raytracing.Light;
/**
 * SpotLight represents class for creating SpotLight objects in a Raytracer
 */
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


    /** Constructor used to create SpotLight objects
     * @param position Point3 for the position of spotlight objects - must not be null
     * @param direction Vector3 for the direction of spotlight - must not be null
     * @param halfAngle double for the angle of the spotlight - must not be zero
     * @param color Color
     * */
    public SpotLight(final Point3 position, final Vector3 direction, final double halfAngle, final Color color) {
        super(color);
        if (position == null) throw new IllegalArgumentException("must not be null");
        if (direction == null) throw new IllegalArgumentException("must not be null");
        if (halfAngle == 0) throw new IllegalArgumentException("must not be 0");
        this.position = position;
        this.direction = direction;
        this.halfAngle = halfAngle;
    }

    @Override
    public boolean illuminates(final Point3 p) {
        Vector3 v = p.sub(position);
        double a = v.dot(direction) / (v.magnitude * direction.magnitude);
        return Math.acos(a) <= halfAngle;
    }

    @Override
    public Vector3 directionFrom(final Point3 p) {
        return position.sub(p);
    }
}
