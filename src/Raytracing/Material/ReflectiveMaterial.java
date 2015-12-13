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
            if(light.illuminates(hitPoint,world)){
                final Vector3 l = light.directionFrom(hitPoint);
                final Vector3 rd = l.reflectedOn(hit.n);
                final double imax1=Math.max(0.0,hit.n.dot(l));
                final double imax2=Math.pow(Math.max(0.0,rd.dot(hit.ray.d.mul(-1).normalized())),exponent);
                surfaceColor = surfaceColor.add(light.color.mul(diffuse).mul(imax1).add(light.color.mul(specular).mul(imax2)));
            }
        }
        return surfaceColor.add(tracer.traceColor(new Ray(hitPoint, hit.ray.d.add(hit.n.mul(hit.n.dot(hit.ray.d.mul(-1)))))));
    }
}
