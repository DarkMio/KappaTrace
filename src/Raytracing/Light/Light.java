package Raytracing.Light;
/**
 * Light represents abstract class for all light objects
 */
import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Color;

public abstract class Light {

    /** Color representing the color of light*/
    final public Color color;

    /** Constructor used to create Light
     * @param color Color for light - must not be null */
    public Light(final Color color) {
        if (color == null) throw new IllegalArgumentException("must not be null");
        this.color = color;
    }

    /** Abstract constructor for light illumination with a Point3 p*/
    public abstract boolean illuminates(Point3 p);
    /** Abstract constructor used for determination of light direction with a Point3 p */
    public abstract Vector3 directionFrom(Point3 p);
}
