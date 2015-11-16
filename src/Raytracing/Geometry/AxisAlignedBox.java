package Raytracing.Geometry;

/**
 * class for AxisAlignedBox objects
 */

import MathFunc.Normal3;
import MathFunc.Point3;
import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.Ray;

public class AxisAlignedBox extends Geometry{

    /** Point3 determining the left bottom far of the AAB*/
    public final Point3 lbf;
    /** Point3 determining the right upper near of the AAB*/
    public final Point3 run;
    /** Plane determining the top side of the ABB*/
    protected final Plane top;
    /** Plane determining the bottom side of the ABB*/
    protected final Plane bottom;
    /** Plane determining the left side of the ABB*/
    protected final Plane left;
    /** Plane determining the right side of the ABB*/
    protected final Plane right;
    /** Plane determining the front side of the ABB*/
    protected final Plane  front;
    /** Plane determining the back side of the ABB*/
    protected final Plane back;
    /** Normal3 is used to span a plane to top */
    protected final static Normal3 toTop = new Normal3(0, 1, 0);
    /** Normal3 is used to span a plane to right */
    protected final static Normal3 toRight = new Normal3(1, 0, 0);
    /** Normal3 is used to span a plane to front */
    protected final static Normal3 toFront = new Normal3(0, 0, 1);
    /** Normal3 is used to span a plane to bot */
    protected final static Normal3 toBot = new Normal3(0, -1, 0);
    /** Normal3 is used to span a plane to left */
    protected final static Normal3 toLeft = new Normal3(-1, 0, 0);
    /** Normal3 is used to span a plane to back */
    protected final static Normal3 toBack = new Normal3(0, 0, -1);

    /** constructor for AAB objects with a Color c, a Point3 lbf and a Point3 run*/
    public AxisAlignedBox(Color c, Point3 lbf, Point3 run) {
        super(c);
        this.lbf = lbf;
        this.run = run;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AxisAlignedBox that = (AxisAlignedBox) o;

        if (lbf != null ? !lbf.equals(that.lbf) : that.lbf != null) return false;
        if (run != null ? !run.equals(that.run) : that.run != null) return false;
        if (top != null ? !top.equals(that.top) : that.top != null) return false;
        if (bottom != null ? !bottom.equals(that.bottom) : that.bottom != null) return false;
        if (left != null ? !left.equals(that.left) : that.left != null) return false;
        if (right != null ? !right.equals(that.right) : that.right != null) return false;
        if (front != null ? !front.equals(that.front) : that.front != null) return false;
        return !(back != null ? !back.equals(that.back) : that.back != null);

    }

    @Override
    public int hashCode() {
        int result = lbf != null ? lbf.hashCode() : 0;
        result = 31 * result + (run != null ? run.hashCode() : 0);
        result = 31 * result + (top != null ? top.hashCode() : 0);
        result = 31 * result + (bottom != null ? bottom.hashCode() : 0);
        result = 31 * result + (left != null ? left.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        result = 31 * result + (front != null ? front.hashCode() : 0);
        result = 31 * result + (back != null ? back.hashCode() : 0);
        return result;
    }
}
