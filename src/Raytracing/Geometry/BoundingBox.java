package Raytracing.Geometry;

import MathFunc.Normal3;
import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Color;
import Raytracing.Constants.Materials;
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
    public final AxisAlignedBox boundingBox;

    public BoundingBox(List<Geometry> geometries) {
        super(new SingleColorMaterial(new Color(1, 1, 1)));
        this.geometries = geometries;
        Point3[] boundingPoints = findBoundaries();
        boundingBox = new AxisAlignedBox(new LambertMaterial(new Color(0.8, 0.5, 0.8)),
                boundingPoints[0],
                boundingPoints[1]){
            private final static boolean invert = false;
        };
    }

    private Point3[] findBoundaries() {
        double[] boundaries;
        double minX = 0, minY = 0, minZ = 0;
        double maxX = 0, maxY = 0, maxZ = 0;
        for (Geometry g : geometries) {
            if (g.getClass().equals(AxisAlignedBox.class)) {
                boundaries = getBoundariesAAB((AxisAlignedBox) g);
            } else if (g.getClass().equals(Sphere.class)) {
                boundaries = getBoundariesSphere((Sphere) g);
            } else if (g.getClass().equals(Triangle.class)) {
                boundaries = getBoundariesTriangle((Triangle) g);
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
        double maxZ = Math.max(Math.min(t.a.z, t.b.z), t.c.z);
        return new double[]{minX, minY, minZ, maxX, maxY, maxZ};
    }

    @Override
    public Hit hit(Ray r) {
        Hit boundingHit = boundingBox.hit(r);
        double t = Double.MAX_VALUE;
        Hit h = null;
        if (boundingHit != null) {
            for (Geometry g : geometries) {
                final Hit hit = g.hit(r);
                if (hit != null && hit.t < t) {
                    t = hit.t;
                    h = hit;
                }
            }
        }
        return h;
    }
}
