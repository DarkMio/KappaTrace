package Raytracing;

import Raytracing.Geometry.Geometry;
import java.util.ArrayList;

public class World {

    public final Color backgroundColor;
    public final ArrayList<Geometry> geometry;

    public World(Color backgroundColor, ArrayList<Geometry> geometry) {
        this.backgroundColor = backgroundColor;
        this.geometry = geometry;
    }

    public Hit hit(final Ray r) {
       double t = Double.MAX_VALUE;
        Hit h = null;
        for(Geometry g: geometry) {
            final Hit hit = g.hit(r);
            if(hit != null && hit.t < t) {
                t = hit.t;
                h = hit;
            }
        }
        return h;
    }
}
