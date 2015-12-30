package Raytracing.Material;
/**
 * ReflectiveMaterial represents class for all Reflect objects
 */

import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.*;
import Raytracing.Light.Light;

public class ReflectiveMaterial extends Material{

    /** Color representing diffuse reflections of rough surfaces */
    final Color diffuse;
    /** Color representing specular reflections*/
    final Color specular;
    /** representing the Phong exponent for the intensity of Phong reflections */
    final int exponent;
    /** Color representing reflections on a plane and objects*/
    final Color reflection;

    /** constructor used to create ReflectiveMaterial
     * @param diffuse Color determining the reflection of rough surfaces - must not be null
     * @param specular Color determining reflections - must not be null
     * @param exponent int determining the Phong exponent
     * @param reflection Color determining the reflection on the plane and on objects - must not be null
     */
    public ReflectiveMaterial(final Color diffuse, final Color specular, final int exponent, final Color reflection) {
        if(diffuse==null)throw new IllegalArgumentException("diffuse must not be null!");
        if(specular==null)throw new IllegalArgumentException("specular must not be null!");
        if(reflection==null)throw new IllegalArgumentException("reflection must not be null!");
        this.diffuse = diffuse;
        this.specular = specular;
        this.exponent = exponent;
        this.reflection = reflection;
    }


    //c = cd*ca + summe(i=1 bis imax)(cd*cl*imax(0, Vn * Vl)+ cs * cl * imax(0, Ve * Vn)^p + cr*fr(t * Vpr, Vrd))
    // ^ nice comment, m8, reel funny FUCK YOJ - in Love, Mio.
    @Override
    public Color colorFor(final Hit hit, final World world, final Tracer tracer) {
        if (hit == null) {
            throw new IllegalArgumentException("The Hit cannot be null!");
        }
        if (world == null) {
            throw new IllegalArgumentException("The World cannot be null!");
        }
        if (tracer == null) {
            throw new IllegalArgumentException("The Tracer cannot be null!");
        }

        Color color = world.ambientLight.mul(this.diffuse);
        final Point3 point = hit.ray.at(hit.t);
        final double cosinusPhi = hit.n.dot(hit.ray.d.mul(-1.0)) * 2;
        final Vector3 v = hit.ray.d.mul(-1).normalized();

        for (final Light currentLight : world.lights) {

            if (currentLight.illuminates(point, world)) {
                final Vector3 lightlVector = currentLight.directionFrom(point);

                final Vector3 reflectedVector = lightlVector.reflectedOn(hit.n);

                final double maxNL = Math.max(0.0, hit.n.dot(lightlVector));
                final double maxER = Math.pow(Math.max(0.0, reflectedVector.dot(v)), this.exponent);
                color = color.add(currentLight.color.mul(this.diffuse).mul(maxNL)).add(currentLight.color.mul(this.specular).mul(maxER));
            }
        }
        final Color reflectedColor = tracer.traceColor(new Ray(point, hit.ray.d.add(hit.n.mul(cosinusPhi))));
        return color.add(reflection.mul((reflectedColor)));
    }

}
