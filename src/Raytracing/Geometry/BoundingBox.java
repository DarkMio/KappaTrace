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
    public final AxisAlignedBox box;

    public BoundingBox(List<Geometry> geometries) {
        super(new SingleColorMaterial(new Color(1, 1, 1)));
        this.geometries = geometries;
        Point3[] boundingPoints = findBoundaries(geometries);
        lbf = boundingPoints[0];
        run = boundingPoints[1];
        box = new AxisAlignedBox(Materials.WHITE_LAMBERT, lbf, run);
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
        Hit init = box.hit(r);
        final double t = Double.MAX_VALUE;
        double c = t;
        Hit h = null;
        for (Geometry g : geometries) {
            final Hit hit = g.hit(r);
            if (hit != null ) {
                final Point3 pos = r.at(hit.t);
                if(inRange(lbf, pos, run)) {
                    if (hit.t < c && hit.t > Epsilon.PRECISION) {
                        c = hit.t;
                        h = hit;
                    }
                }
            }
        }
        if (h != null)
            return new Hit(c, h.ray, h.geo, h.n);
        else return init;
    }

    private static boolean inRange(Point3 lbf, Point3 value, Point3 run) {
        return inRange(lbf.x, value.x, run.x) && inRange(lbf.y, value.y, run.y) && inRange(lbf.z, value.z, run.z);
    }

    private static boolean inRange(double lower, double value, double upper) {
        return lower <= value && value <= upper;
    }
}
