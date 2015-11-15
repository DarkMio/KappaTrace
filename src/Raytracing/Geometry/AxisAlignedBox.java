package Raytracing.Geometry;

import MathFunc.Normal3;
import MathFunc.Point3;
import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.Ray;

public class AxisAlignedBox extends Geometry{

    public final Point3 lbf, run;
    protected final Plane top, bottom, left, right, front, back;
    protected final static Normal3 toTop = new Normal3(0, 1, 0);
    protected final static Normal3 toRight = new Normal3(1, 0, 0);
    protected final static Normal3 toFront = new Normal3(0, 0, 1);
    protected final static Normal3 toBot = new Normal3(0, -1, 0);
    protected final static Normal3 toLeft = new Normal3(-1, 0, 0);
    protected final static Normal3 toBack = new Normal3(0, 0, -1);

    public AxisAlignedBox(Color c, Point3 lbf, Point3 run) {
        super(c);
        this.lbf = lbf; // left bottom far
        this.run = run; // right upper near
        top = new Plane(c, run, toTop);
        bottom = new Plane(c, lbf, toBot);
        left = new Plane(c, lbf, toLeft);
        right = new Plane(c, run, toRight);
        front = new Plane(c, run, toFront);
        back = new Plane(c, lbf, toBack);
    }


    @Override
    public Hit hit(Ray r) {
        Plane near = r.o.sub(front.a).dot(front.n) >= 0.0 ? front : back;
        Plane side = r.o.sub(left.a).dot(left.n) >= 0.0 ? left : right;
        Plane upper = r.o.sub(top.a).dot(top.n) >= 0.0 ? top : bottom;
        Hit nearHit = near.hit(r);
        Hit sideHit = side.hit(r);
        Hit upperHit = upper.hit(r);
        Point3 nearPoint = null, sidePoint = null, upperPoint = null;
        if (nearHit != null) nearPoint = r.at(nearHit.t);
        if (sideHit != null) sidePoint = r.at(sideHit.t);
        if (upperHit != null) upperPoint = r.at(upperHit.t);
        if (grAndSmXY(nearPoint)) return nearHit;
        if (grAndSmYZ(sidePoint)) return sideHit;
        if (grAndSmXZ(upperPoint)) return upperHit;
        return null;
/*
        Plane near = front.hit(r).t > back.hit(r).t ? front : back;
        Plane side = left.hit(r).t > right.hit(r).t ? left : right;
        Plane upper = top.hit(r).t > bottom.hit(r).t ? top : bottom;
*/
    }


    protected boolean grAndSmXY(Point3 hit) {
        if (hit == null) return false;
        return grAndSm(lbf.x, hit.x, run.x) && grAndSm(lbf.y, hit.y, run.y);
    }


    protected boolean grAndSmYZ(Point3 hit) {
        if (hit == null) return false;
        return grAndSm(lbf.y, hit.y, run.y) && grAndSm(lbf.z, hit.z, run.z);
    }


    protected boolean grAndSmXZ(Point3 hit) {
        if (hit == null) return false;
        return grAndSm(lbf.x, hit.x, run.x) && grAndSm(lbf.z, hit.z, run.z);
    }

    protected static boolean grAndSm(double a, double p, double b) {
        double precision = precisionFor(a, b);
        return (a - precision <= p && p <= b + precision);
    }
}
