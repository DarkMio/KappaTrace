package Raytracing.Geometry;

/**
 * class for AxisAlignedBox objects
 */

import MathFunc.Normal3;
import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Epsilon;
import Raytracing.Hit;
import Raytracing.Material.Material;
import Raytracing.Ray;
import Raytracing.Transform;

import java.util.ArrayList;

public class AxisAlignedBox extends Geometry {

    public Point3 lbf = new Point3(0, 0, 0);
    public Point3 run = new Point3(1, 1, 1);

    public Node right;
    public Node top;
    public Node front;
    public Node left;
    public Node bottom;
    public Node back;


    public AxisAlignedBox(Material material) {

        super(material);

        ArrayList<Geometry> geometryList = new ArrayList<Geometry>();
        geometryList.add(new Plane(material, new Point3(0, 0, 0), new Normal3(0, 1, 0)));
        left = new Node(geometryList, new Transform().translate(new Vector3(this.lbf.x, this.lbf.y, this.lbf.z)).rotateZ(Math.PI/2));
        bottom =  new Node(geometryList, new Transform().translate(new Vector3(this.lbf.x, this.lbf.y, this.lbf.z)).rotateX(Math.PI));
        back = new Node(geometryList, new Transform().translate(new Vector3(this.lbf.x, this.lbf.y, this.lbf.z)).rotateZ(Math.PI).rotateX(-Math.PI/2));
        right = new Node(geometryList, new Transform().translate(new Vector3(this.run.x, this.run.y, this.run.z)).rotateZ(-Math.PI/2));
        top =  new Node(geometryList, new Transform().translate(new Vector3(this.run.x, this.run.y, this.run.z)));
        front = new Node(geometryList, new Transform().translate(new Vector3(this.run.x, this.run.y, this.run.z)).rotateZ(Math.PI).rotateX(Math.PI/2));

    }
    public AxisAlignedBox(Material material, Point3 lbf, Point3 run) {
        super(material);

        this.lbf = lbf;
        this.run = run;

        ArrayList<Geometry> geometryList = new ArrayList<Geometry>();
        geometryList.add(new Plane(material, new Point3(0, 0, 0), new Normal3(0, 1, 0)));
        left = new Node(geometryList, new Transform().rotateZ(Math.PI/2).translate(new Vector3(this.lbf.x, this.lbf.y, this.lbf.z)));
        bottom =  new Node(geometryList, new Transform().rotateX(Math.PI).translate(new Vector3(this.lbf.x, this.lbf.y, this.lbf.z)));
        back = new Node(geometryList, new Transform().rotateZ(Math.PI).rotateX(-Math.PI/2).translate(new Vector3(this.lbf.x, this.lbf.y, this.lbf.z)));
        right = new Node(geometryList, new Transform().rotateZ(-Math.PI/2).translate(new Vector3(this.run.x, this.run.y, this.run.z)));
        top =  new Node(geometryList, new Transform().translate(new Vector3(this.run.x, this.run.y, this.run.z)));
        front = new Node(geometryList, new Transform().rotateZ(Math.PI).rotateX(Math.PI/2).translate(new Vector3(this.run.x, this.run.y, this.run.z)));
    }

    @Override
    public Hit hit(final Ray r) {
        Hit topHit = top.hit(r);
        Hit bottomHit = bottom.hit(r);
        Hit leftHit = left.hit(r);
        Hit rightHit = right.hit(r);
        Hit frontHit = front.hit(r);
        Hit backHit = back.hit(r);
    /*
    if (near == back) {
        System.out.println("BACK");
    } else {
        System.out.println("FRONT");
    }
    if (side == right) {
        System.out.println("RIGHT");
    } else {
        System.out.println("LEFT");
    }
    if (upper == bottom) {
        System.out.println("BOTTOM");
    } else {
        System.out.println("TOP");
    } */
        Point3 topPoint = null, leftPoint = null, frontPoint = null;
        Point3 bottomPoint = null, rightPoint = null, backPoint = null;
        if (topHit != null) topPoint = r.at(topHit.t);
        if (bottomHit != null) bottomPoint = r.at(bottomHit.t);

        if (leftHit != null) leftPoint = r.at(leftHit.t);
        if (rightHit != null) rightPoint = r.at(rightHit.t);

        if (frontHit != null) frontPoint = r.at(frontHit.t);
        if (backHit != null) backPoint = r.at(backHit.t);

        if (!grAndSmXY(frontPoint)) frontHit = null;
        if (!grAndSmXY(backPoint)) backHit = null;

        if (!grAndSmYZ(leftPoint)) leftHit = null;
        if (!grAndSmYZ(rightPoint)) rightHit = null;

        if (!grAndSmXZ(topPoint)) topHit = null;
        if (!grAndSmXZ(bottomPoint)) bottomHit = null;

        return nearer(nearer(nearer(nearer(nearer(frontHit, backHit), leftHit), rightHit), topHit), bottomHit);
/*
        Plane near = front.hit(r).t > back.hit(r).t ? front : back;
        Plane side = left.hit(r).t > right.hit(r).t ? left : right;
        Plane upper = top.hit(r).t > bottom.hit(r).t ? top : bottom;
*/
    }

    protected Hit nearer(Hit a, Hit b) {
        if (a == null) return b;
        if (b == null) return a;
        if (b.t < a.t) return b;
        return a;
    }

    /**
     * Shortcut method to check if ray position is within x/y boundaries of the AxisAlignedBox
     */
    protected boolean grAndSmXY(Point3 hit) {
        if (hit == null) return false;
        return grAndSm(lbf.x, hit.x, run.x) && grAndSm(lbf.y, hit.y, run.y);
    }

    /**
     * Shortcut method to check if ray position is within y/z boundaries of the AxisAlignedBox
     */
    protected boolean grAndSmYZ(Point3 hit) {
        if (hit == null) return false;
        return grAndSm(lbf.y, hit.y, run.y) && grAndSm(lbf.z, hit.z, run.z);
    }

    /**
     * Shortcut method to check if ray position is within x/z boundaries of the AxisAlignedBox
     */
    protected boolean grAndSmXZ(Point3 hit) {
        if (hit == null) return false;
        return grAndSm(lbf.x, hit.x, run.x) && grAndSm(lbf.z, hit.z, run.z);
    }

    /**
     * Shortcut method to check if a value is within the boundaries of a min and max coordinate of the AxisAlignedBox
     */
    protected static boolean grAndSm(double a, double p, double b) {
        double precision = Epsilon.precisionFor(a, b);
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
