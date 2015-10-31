package Raytracing;

import MathFunc.Point3;
import MathFunc.Vector3;

public class Ray {

    final private Point3 o;
    final private Vector3 d;

    public Ray(Point3 o, Vector3 d){ // origin, direction
        this.o = o;
        this.d = d;
    }

    public Point3 at(double t) {
        return o.add(d.mul(t));
    }

    public double tOf(Point3 p) {
        return (p.x - o.x) / d.x;
    }
}
