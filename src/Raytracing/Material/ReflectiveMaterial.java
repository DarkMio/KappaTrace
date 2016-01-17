package Raytracing.Material;
/**
 * ReflectiveMaterial represents class for all Reflect objects
 */

import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.*;
import Raytracing.Light.Light;
import Raytracing.Material.Texturing.InterpolatedImageTexture;
import Raytracing.Material.Texturing.SingleColorTexture;
import Raytracing.Material.Texturing.Texture;

public class ReflectiveMaterial extends Material {

    /**
     * Color representing diffuse reflections of rough surfaces
     */
    final Texture diffuse;
    /**
     * Color representing specular reflections
     */
    final Texture specular;
    /**
     * representing the Phong exponent for the intensity of Phong reflections
     */
    final int exponent;
    /**
     * Color representing reflections on a plane and objects
     */
    final Texture reflection;

    /**
     * constructor used to create ReflectiveMaterial
     *
     * @param diffuse    Color determining the reflection of rough surfaces - must not be null
     * @param specular   Color determining reflections - must not be null
     * @param exponent   int determining the Phong exponent
     * @param reflection Color determining the reflection on the plane and on objects - must not be null
     */
    public ReflectiveMaterial(final Color diffuse, final Color specular, final int exponent, final Color reflection) {
        this(new SingleColorTexture(diffuse), new SingleColorTexture(specular), exponent, new SingleColorTexture(reflection));
        if (diffuse == null) throw new IllegalArgumentException("diffuse must not be null!");
        if (specular == null) throw new IllegalArgumentException("specular must not be null!");
        if (reflection == null) throw new IllegalArgumentException("reflection must not be null!");
    }

    public ReflectiveMaterial(final Texture diffuse, final Texture specular, final int exponent, final Texture reflection) {
        if (diffuse == null) throw new IllegalArgumentException("diffuse must not be null!");
        if (specular == null) throw new IllegalArgumentException("specular must not be null!");
        if (reflection == null) throw new IllegalArgumentException("reflection must not be null!");
        this.diffuse = diffuse;
        this.specular = specular;
        this.exponent = exponent;
        this.reflection = reflection;
    }

    public ReflectiveMaterial() {
        this(new InterpolatedImageTexture("./src/Resources/CheckerboardTextureGray.png"), new SingleColorTexture(new Color(0.5, 0.5, 0.5)), 64, new SingleColorTexture(new Color(0.75, 0.75, 0.75)));
    }


    //c = cd*ca + summe(i=1 bis imax)(cd*cl*imax(0, Vn * Vl)+ cs * cl * imax(0, Ve * Vn)^p + cr*fr(t * Vpr, Vrd))
    // ^ nice comment, m8, reel funny FUCK YOJ - in Love, Mio.
    @Override
    public Color colorFor(Hit hit, World world, Tracer tracer) {
        final Point3 hitPoint = hit.ray.at(hit.t);
        final double cosinusPhi = hit.n.dot(hit.ray.d.mul(-1.0)) * 2;
        final Vector3 v = hit.ray.d.mul(-1).normalized();
        Color c = world.ambientLight.mul(this.diffuse.colorFor(hit.tp.u, hit.tp.v));
        // check each light in scene
        for (final Light light : world.lights) {
            // check if light illuminates this point
            boolean lighting = light.illuminates(hitPoint, world);
            if (lighting) {
                Vector3 normalVector = light.directionFrom(hitPoint);
                // the opposite vector
                Vector3 reflectedVector = normalVector.reflectedOn(hit.n);
                // if light illuminates hit point, there is need to calculate new xolor
                double firstMaximum = Math.max(0.0, hit.n.dot(normalVector));
                double secondMaximum = Math.pow(Math.max(0.0, reflectedVector.dot(v)), this.exponent);
                c = c.add(light.color.mul(this.diffuse.colorFor(hit.tp.u, hit.tp.v)).mul(firstMaximum)).add(light.color.mul(this.specular.colorFor(hit.tp.u, hit.tp.v)).mul(secondMaximum));
            }
        }
        Color reflectedColor = tracer.traceColor(new Ray(hitPoint, hit.ray.d.add(hit.n.mul(cosinusPhi))));
        return c.add(reflection.colorFor(hit.tp.u, hit.tp.v).mul((reflectedColor)));
    }

}
