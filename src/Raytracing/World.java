package Raytracing;

/**
 * class used for world objects
 */

import Raytracing.Geometry.Geometry;
import Raytracing.Light.Light;

import java.util.ArrayList;

public class World {

    /** Color determining the background color of world */
    public final Color backgroundColor;
    /** an ArrayList consisting of Geometry objects */
    public final ArrayList<Geometry> geometry;
    /** an directional light for basic global lightning */
    public final Color ambientLight;

    public final ArrayList<Light> lights;

    /** constructor used to create a world with a background color and a scene */
    public World(Color backgroundColor, ArrayList<Geometry> geometry, Color ambientLight, ArrayList<Light> lights) {
        this.lights = lights;
        this.backgroundColor = backgroundColor;
        this.geometry = geometry;
        this.ambientLight = ambientLight;
    }

    /** function used to calculate the nearest ray hit */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        World world = (World) o;

        if (backgroundColor != null ? !backgroundColor.equals(world.backgroundColor) : world.backgroundColor != null)
            return false;
        return !(geometry != null ? !geometry.equals(world.geometry) : world.geometry != null);

    }

    @Override
    public int hashCode() {
        int result = backgroundColor != null ? backgroundColor.hashCode() : 0;
        result = 31 * result + (geometry != null ? geometry.hashCode() : 0);
        return result;
    }
}
