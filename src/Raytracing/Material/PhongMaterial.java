package Raytracing.Material;
/**
 * PhongMaterial represents class for all Phong objects
 */
import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.Light.Light;
import Raytracing.World;

public class PhongMaterial extends Material {

    /** Color representing diffuse reflections of rough surfaces */
    public final Color diffuse;
    /** Color representing specular reflections of shiny surfaces*/
    public final Color specular;
    /**int exponent representing the Phong exponent.  */
    public final int exponent;

    /** Constructor used to create PhongMaterial with a Color diffuse, a Color specular and an int exponent */
    public PhongMaterial(final Color diffuse, final Color specular, final int exponent) {
        if (diffuse == null) throw new IllegalArgumentException("must not be null");
        if (specular == null) throw new IllegalArgumentException("must not be null");
        if (exponent < 0) throw new IllegalArgumentException("must be greater or equals 0");
        this.diffuse = diffuse;
        this.specular = specular;
        this.exponent = exponent;
    }
/*
    @Override
    public Color colorFor(Hit hit, World world) {

        Color tC=world.ambientLight.mul(diffuse);

        Point3 p=hit.ray.o.add(hit.ray.d.mul(hit.t));
        for(Light light:world.lights){
            if(light.illuminates(p)){

                Vector3 l=light.directionFrom(p).normalized();
                Vector3 ref=l.reflectedOn(hit.n);
                double max1=Math.max(0, l.dot(hit.n));
                Vector3 e = hit.ray.d.mul(-1).normalized();
                double erl =e.dot(ref);

                Color diffuseLight=diffuse.mul(light.color).mul(Math.max(0,max1));
                Color specularLight=specular.mul(light.color).mul(Math.pow(Math.max(0,erl),exponent));

                tC=tC.add(diffuseLight).add(specularLight);
            }
        }
        return tC;
    }
*/

    @Override
    public Color colorFor(final Hit hit, final World world) {
        Color a = world.ambientLight.mul(diffuse);
        Color b = null;
        Point3 pos = hit.ray.o.add(hit.ray.d.mul(hit.t));
        for(Light light: world.lights) {
            if (light.illuminates(pos)) {
                Vector3 l = light.directionFrom(pos).normalized();
                Color c1 = diffuse.mul(light.color).mul(Math.max(0, hit.n.dot(l)));
                Vector3 tempVec = l.reflectedOn(hit.n);
                double temp = Math.max(0, hit.ray.d.mul(-1).normalized().dot(tempVec));
                Color c2 = specular.mul(light.color).mul(Math.pow(temp, exponent));
                if (b == null) b = c1.add(c2);
                else b.add(b);
            }
        }
        if(b == null) return a;
        return a.add(b);
    }

}
