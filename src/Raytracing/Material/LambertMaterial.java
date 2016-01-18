package Raytracing.Material;

/**
 * LambertMaterial represents class for lambert material
 */

import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.*;
import Raytracing.Light.Light;
import Raytracing.Material.Texturing.Texture;
import Raytracing.Material.Texturing.ImageTexture;

public class LambertMaterial extends Material {

    public final Texture texture;


    public LambertMaterial(final Texture texture) {
        if (texture == null) throw new IllegalArgumentException("must not be null");
        this.texture = texture;
    }

    /**
     * constructor used to create LambertMaterial
     */
    public LambertMaterial() {
        texture = new ImageTexture("./src/Resources/CheckerboardTexture.png");
    }

    @Override
    public Color colorFor(final Hit hit, final World world, final Tracer tracer) {
        Color textureColor = texture.colorFor(hit.tp.u, hit.tp.v);
        Color a = world.ambientLight.mul(textureColor);
        final Point3 pos = hit.ray.at(hit.t - Epsilon.precisionFor(hit.t));
        for (Light light : world.lights) {
            if (light.illuminates(pos, world)) {
                final Vector3 l = light.directionFrom(pos).normalized();
                final Color c = textureColor.mul(light.color).mul(Math.max(0, l.dot(hit.n)));
                a = a.add(c);
            }
        }
        return a;
    }
}