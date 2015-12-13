package Raytracing.Light;
/**
 * PointLight represents class for creating PointLight objects in a Raytracer
 */

import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.*;

public class PointLight extends Light {

    /** Point3 used to determine the position of PointLight */
    public final Point3 position;

    /** Constructor used to create PointLight with a Point3 position and a Color color
     * @param position Point3 for light position - must not be null
     * */
    public PointLight(final Point3 position, final Color color, final boolean castShadows) {
        super(color, castShadows);
        if (position == null) throw new IllegalArgumentException("must not be null");
        this.position = position;
    }

    @Override
    public boolean illuminates(final Point3 p, final World w) {
        if(!this.castShadows){
            return true;
        }
        final Ray r = new Ray(p, directionFrom(p));
        Hit hit = w.hit(r);
        if(hit == null)return true;
        double tl = position.sub(p).magnitude/1;
        double e = Epsilon.precisionFor(tl, hit.t);
        return (hit.t > tl && hit.t < e);
    }

    @Override
    public Vector3 directionFrom(final Point3 p) {
        return position.sub(p).normalized();
    }
}
