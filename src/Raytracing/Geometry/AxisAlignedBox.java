package Raytracing.Geometry;

import MathFunc.Normal3;
import MathFunc.Point3;
import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.Ray;

public class AxisAlignedBox extends Geometry{

    public final Point3 lbf, run;

    public AxisAlignedBox(Color c, Point3 lbf, Point3 run) {
        super(c);
        this.lbf = lbf; // left bottom far
        this.run = run; // right upper near
    }

    @Override
    public Hit hit(Ray r) {
        Normal3 toTop = new Normal3(0, 1, 0);
        Normal3 toRight = new Normal3(1, 0, 0);
        Normal3 toBack = new Normal3(0, 0, 1);
        // Those boys gotta be as object methods, right ... right?
        Plane top = new Plane(color, run, toTop);
        Plane bottom = new Plane(color, lbf, toTop);
        Plane left = new Plane(color, lbf, toRight);
        Plane right = new Plane(color, run, toRight);
        Plane front = new Plane(color, run, toBack);
        Plane back = new Plane(color, lbf, toBack);
        Plane near = r.o.sub(front.a).dot(front.n) > 0 ? front : back;
        Plane side = r.o.sub(left.a).dot(left.n) > 0 ? left : right;
        Plane upper = r.o.sub(top.a).dot(top.n) > 0 ? top : bottom;
        Hit nearHit = near.hit(r);
        Hit sideHit = side.hit(r);
        Hit upperHit = upper.hit(r);
        Point3 nearPoint = null, sidePoint = null, upperPoint = null;
        if (nearHit != null) nearPoint = r.at(nearHit.t);
        if (sideHit != null) sidePoint = r.at(sideHit.t);
        if (upperHit != null) upperPoint = r.at(upperHit.t);
        if (grAndSm(nearPoint)) return new Hit(nearHit.t, r, this);
        if (grAndSm(sidePoint)) return new Hit(sideHit.t, r, this);
        if (grAndSm(upperPoint)) return new Hit(upperHit.t, r, this);
        return null;
/*
        Plane near = front.hit(r).t > back.hit(r).t ? front : back;
        Plane side = left.hit(r).t > right.hit(r).t ? left : right;
        Plane upper = top.hit(r).t > bottom.hit(r).t ? top : bottom;
*/
    }

    protected boolean grAndSm(Point3 hit) {
        if (hit == null) return false;
        return grAndSm(lbf.x, hit.x, run.x) && grAndSm(lbf.y, hit.y, run.y) && grAndSm(lbf.z, hit.z, run.z);
    }

    protected static boolean grAndSm(double a, double p, double b) {
        return (a <= p && p <= b);
    }
}
