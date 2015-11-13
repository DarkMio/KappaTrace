package Raytracing.Geometry;

import MathFunc.Normal3;
import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.Ray;

public class Plane extends Geometry {

    public final Point3 a;
    public final Normal3 n;

    public Plane(Color color, Point3 a, Normal3 n) {
        super(color);
        this.a = a;
        this.n = n;
    }

    @Override
    public Hit hit(Ray r) {
        double lower = r.d.dot(n); // was unter dem Bruch steht
        if(lower < 0.0001 ) return null;
        double upper = new Vector3(a.x - r.o.x, a.y - r.o.y, a.z - r.o.z).dot(n); // Was �ber dem Bruch steht
        double t = upper/lower;
        return new Hit(t, r, this);
    }
}
