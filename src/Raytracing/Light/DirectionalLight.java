package Raytracing.Light;
/**
 * DirectionalLight represents class for creating directional light in a Raytracer
 */

import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Color;
import Raytracing.Ray;
import Raytracing.World;

public class DirectionalLight extends Light {

    /** Vector3 representing the direction of the DirectionalLight */
    public final Vector3 direction;

    /**
     * Construct a Triangle
     * @param direction Vector3 for direction of light - must not be null
     * @param color Color for color of light
     * @param castShadows boolean for shadows
     */
        public DirectionalLight(final Vector3 direction, final Color color, final boolean castShadows) {
        super(color, castShadows);
        if(direction == null) throw new IllegalArgumentException("must not be null");
        this.direction = direction;
    }

    @Override
    public boolean illuminates(final Point3 p, final World w) {
        return !castShadows || w.hit(new Ray(p, directionFrom(p))) == null;
    }

    @Override
    public Vector3 directionFrom(final Point3 p) {
        return direction.mul(-1);
    }
}
