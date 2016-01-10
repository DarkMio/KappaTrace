package Raytracing.Material;
/**
 * Material represents abstract class for all material objects
 */

import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.Tracer;
import Raytracing.World;

public abstract class Material {

    /**
     * Abstract constructor used to determine the position of Color with a Hit hit, a World world and a Tracer tracer
     */
    public abstract Color colorFor(final Hit hit, final World world, final Tracer tracer);
}
