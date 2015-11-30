package Raytracing.Light;

import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Color;

public abstract class Light {

    /** Color representing the color of light*/
    final public Color color;

    /** Constructor used to create Light with a Color color */
    public Light(Color color) {
        this.color = color;
    }

    /** Abstract constructor for light illumination with a Point3 p*/
    public abstract boolean illuminates(Point3 p);
    /** Abstract constructor used for determination of light direction with a Point3 p */
    public abstract Vector3 directionFrom(Point3 p);
}
