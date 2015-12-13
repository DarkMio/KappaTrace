package Raytracing.Material;


import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.*;
import Raytracing.Light.Light;

public class ReflectiveMaterial extends Material{
    final Color diffuse;
    final Color specular;
    final int exponent;
    final Color reflection;

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
    public Color colorFor(final Hit hit, final World world, final Tracer tracer){
        Color surfaceColor = world.ambientLight.mul(diffuse);
        final Point3 hitPoint = hit.ray.o.add(hit.ray.d.mul(hit.t));
        for(final Light light: world.lights){
            if(light.illuminates(hitPoint, world)){
                final Vector3 direction = light.directionFrom(hitPoint);
                final Vector3 reflect = direction.reflectedOn(hit.n);
                final Vector3 temp = hit.ray.d.mul(-1).normalized();
                final double imax1 = Math.max(0.0, direction.dot(hit.n));
                final double imax2 = Math.pow(Math.max(0.0, temp.dot(reflect)), exponent);

                final Color diffuseC = diffuse.mul(light.color).mul(imax1);
                final Color specularC = specular.mul(light.color).mul(imax2);
                final Color reflectC = reflection.mul(tracer.traceColor(new Ray(hitPoint, hit.ray.d.reflectedOn(hit.n).mul(-1))));
                // surfaceColor = surfaceColor.add(diffuseLight).add(specularLight).add(reflectionLight);
                surfaceColor = surfaceColor.add(diffuseC).add(specularC).add(reflectC);
            }
        }
        return surfaceColor;
    }

}
