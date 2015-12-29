package Raytracing.Geometry;

import MathFunc.Vector3;
import Raytracing.Hit;
import Raytracing.Material.Material;
import Raytracing.Ray;
import Raytracing.Transform;

import java.util.List;

public class Node extends Geometry {

    private final Transform transform;
    private final List<Geometry> geometries;

    public Node(Material material, Transform transform, List<Geometry> geometries) {
        super(material);
        this.transform = transform;
        this.geometries = geometries;
    }


    @Override
    public Hit hit(Ray r) {
        Ray ray = new Ray(transform.i.mul(r.o), transform.i.mul(r.d));
        double t = Double.MAX_VALUE;
        Hit hit = null;
        for (Geometry geo: geometries) {
            Hit h = geo.hit(ray);
            if (h != null && h.t >= 0 && h.t < t) {
                hit = h;
                t = h.t;
            }
        }
        if (hit == null) return null;
        return new Hit(hit.t, r, hit.geo, transform.i.transpose().mul(new Vector3(hit.n.x, hit.n.y, hit.n.z)).asNormal());
    }
}
