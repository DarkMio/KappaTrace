package Raytracing.Light;

import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Color;

public abstract class Light {

    final public Color color;

    public Light(Color color) {
        this.color = color;
    }

    public abstract boolean illuminates(Point3 p);
    public abstract Vector3 directionFrom(Point3 p);
}
