package Raytracing.Material;

import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.Light.Light;
import Raytracing.World;

public class PhongMaterial extends Material {
    public final Color diffuse;
    public final Color specular;
    public final int exponent;

    public PhongMaterial(Color diffuse, Color specular, int exponent) {
        this.diffuse = diffuse;
        this.specular = specular;
        this.exponent = exponent;
    }

    @Override
    public Color colorFor(Hit hit, World world) {
        Color a = world.ambientLight.mul(diffuse);
        Color b = null;
        Point3 lightPos = hit.ray.o.add(hit.ray.d.mul(hit.t));
        for(Light light: world.lights) {
            Vector3 l = light.directionFrom(lightPos);
            Color c1 = diffuse.mul(light.color).mul(Math.max(0, hit.n.dot(l)));
            Vector3 tempVec = l.reflectedOn(hit.n);
            double temp = Math.max(0, hit.ray.d.mul(-1).normalized().dot(tempVec));
            Color c2 = specular.mul(light.color).mul(Math.pow( temp, exponent));
            if(b == null) b = c1.add(c2);
            else b.add(b);
        }
        return a.add(b);
    }
}
