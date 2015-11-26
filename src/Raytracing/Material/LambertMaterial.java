package Raytracing.Material;

import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.Light.Light;
import Raytracing.World;

/**
 * Created by Mio on 17.11.2015.
 */
public class LambertMaterial extends Material {
    public final Color color;

    public LambertMaterial(Color color) {
        this.color = color;
    }

    @Override
    public Color colorFor(Hit hit, World world) {
        Color a = world.ambientLight.mul(color);
        Color b = null;
        Point3 pos = hit.ray.o.add(hit.ray.d.mul(hit.t));
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
