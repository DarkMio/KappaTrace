package Raytracing.Geometry;

import MathFunc.Normal3;
import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Color;
import Raytracing.Constants.Materials;
import Raytracing.Epsilon;
import Raytracing.Hit;
import Raytracing.Material.LambertMaterial;
import Raytracing.Material.Material;
import Raytracing.Material.SingleColorMaterial;
import Raytracing.Ray;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

import java.util.List;

/**
 * Created by Mio on 07.01.2016.
 */
public class BoundingBox extends Geometry {

    public final List<Geometry> geometries;
    public final Point3 lbf, run;
    public final NewBox box;

    public BoundingBox(List<Geometry> geometries) {
        super(new SingleColorMaterial(new Color(1, 1, 1)));
        this.geometries = geometries;
        Point3[] boundingPoints = findBoundaries(geometries);
        lbf = boundingPoints[0].add(new Vector3(-0.1, -0.1, -0.1));
        run = boundingPoints[1].add(new Vector3(0.1, 0.1, 0.1));
        box = new NewBox(lbf, run);
    }

    private Point3[] findBoundaries(List<Geometry> geos) {
        double[] boundaries;
        double minX = 0, minY = 0, minZ = 0;
        double maxX = 0, maxY = 0, maxZ = 0;
        for (Geometry g : geos) {
            if (g.getClass().equals(AxisAlignedBox.class)) {
                boundaries = getBoundariesAAB((AxisAlignedBox) g);
            } else if (g.getClass().equals(Sphere.class)) {
                boundaries = getBoundariesSphere((Sphere) g);
            } else if (g.getClass().equals(Triangle.class)) {
                boundaries = getBoundariesTriangle((Triangle) g);
            } else if (g.getClass().equals(ShapeFromFile.class)) {
                ShapeFromFile geo = (ShapeFromFile) g;
                List<Geometry> things = geo.objects;
                Point3[] stuff = findBoundaries(things);
                boundaries = new double[]{stuff[0].x, stuff[0].y, stuff[0].z, stuff[1].x, stuff[1].y, stuff[1].z};
            } else {
                System.err.println("Boundaries cannot be resolved with " + g.getClass().getSimpleName());
                continue;
            }
            minX = Math.min(minX, boundaries[0]);
            minY = Math.min(minY, boundaries[1]);
            minZ = Math.min(minZ, boundaries[2]);
            maxX = Math.max(maxX, boundaries[3]);
            maxY = Math.max(maxY, boundaries[4]);
            maxZ = Math.max(maxZ, boundaries[5]);
        }
        System.err.println("Generated Bounding Box at: min(x=" + minX + " y=" + minY + " z=" + minZ + ") max(x=" + maxX + " y=" + maxY + " z=" + maxZ);
        return new Point3[]{new Point3(minX, minY, minZ), new Point3(maxX, maxY, maxZ)};
    }

    private double[] getBoundariesAAB(AxisAlignedBox b) {
        double x1, x2, y1, y2, z1, z2;
        x1 = b.lbf.x;
        y1 = b.lbf.y;
        z1 = b.lbf.z;
        x2 = b.run.x;
        y2 = b.run.y;
        z2 = b.run.z;
        x1 = Math.min(x1, x2);
        y1 = Math.min(y1, y2);
        z1 = Math.min(z1, z2);
        x2 = Math.max(x1, x2);
        y2 = Math.max(y1, y2);
        z2 = Math.max(z1, z2);
        return new double[]{x1, y1, z1, x2, y2, z2};
    }

    private double[] getBoundariesSphere(Sphere s) {
        Point3 min = s.c.sub(new Vector3(s.r, s.r, s.r));
        Point3 max = s.c.add(new Vector3(s.r, s.r, s.r));
        return new double[]{min.x, min.y, min.z, max.x, max.y, max.z};
    }

    private double[] getBoundariesTriangle(Triangle t) {
        double minX = Math.min(Math.min(t.a.x, t.b.x), t.c.x);
        double minY = Math.min(Math.min(t.a.y, t.b.y), t.c.y);
        double minZ = Math.min(Math.min(t.a.z, t.b.z), t.c.z);
        double maxX = Math.max(Math.max(t.a.x, t.b.x), t.c.x);
        double maxY = Math.max(Math.max(t.a.y, t.b.y), t.c.y);
        double maxZ = Math.max(Math.max(t.a.z, t.b.z), t.c.z);
        return new double[]{minX, minY, minZ, maxX, maxY, maxZ};
    }

    @Override
    public Hit hit(Ray r) {
        Hit init = box.xHit(r);
        Point3 initPos = null;
        if (init != null) {
            initPos = r.at(init.t);
        }
        final double t = Double.MAX_VALUE;
        double c = t;
        Hit h = null;
        if(inRange(lbf, initPos, run)) {
            for (Geometry g : geometries) {
                final Hit hit = g.hit(r);
                if (hit != null) {
                    final Point3 pos = r.at(hit.t);
                    if (inRange(lbf, pos, run)) {
                        if (hit.t < c && hit.t > Epsilon.PRECISION) {
                            c = hit.t;
                            h = hit;
                        }
                    }
                } else {
                   //  h = init;
                }
            }
        }
        return h;
    }

    private static boolean inRange(Point3 lbf, Point3 value, Point3 run) {
        if (value == null) return false;
        return inRange(lbf.x, value.x, run.x) && inRange(lbf.y, value.y, run.y) && inRange(lbf.z, value.z, run.z);
    }

    private static boolean inRange(double lower, double value, double upper) {
        double precision = Epsilon.precisionFor(lower, value, upper);
        return lower - precision <= value && value - precision <= upper;
    }

    class NewBox extends AxisAlignedBox {

        public NewBox(Point3 lbf, Point3 run) {
            super(Materials.WHITE_LAMBERT, lbf, run);
        }

        @Override
        public Hit hit(Ray r) {
            return null;
        }

        public Hit xHit(Ray r) {

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
    }
}
