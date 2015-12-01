package Raytracing.Material;

/**
 * LambertMaterial represents class for lambert material
 */

import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.Light.Light;
import Raytracing.World;

public class LambertMaterial extends Material {

    /** Color used to determine the color of LambertMaterial */
    public final Color color;

    /** constructor used to create LambertMaterial
     * @param color Color determining the color of lambert light - must not be null */
    public LambertMaterial(final Color color) {
        if (color == null) throw new IllegalArgumentException("must not be null");
        this.color = color;
    }

    @Override
    public Color colorFor(final Hit hit, final World world) {
        final  Color a = world.ambientLight.mul(color);
        Color b = null;
        final Point3 pos = hit.ray.o.add(hit.ray.d.mul(hit.t));
        for(Light light: world.lights) {
            if (light.illuminates(pos)) {
                Vector3 l = light.directionFrom(hit.ray.at(hit.t)).normalized();
                Color c = color.mul(light.color).mul(Math.max(0, hit.n.dot(l)));
                if (b == null) b = c;
                else b.add(b);
            }
        }


        return a.add(b);
    }
}
