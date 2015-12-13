package Raytracing.Material;
/**
 * SingleColorMaterial represents class for all SingleColorMaterial objects
 */
import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.Tracer;
import Raytracing.World;

public class SingleColorMaterial extends Material{

    /** Color representing a single color for SingleColorMaterial */
    public final Color color;


    /** Constructor used to create a SingleColorMaterial object
     * @param color Color - must not be null */
    public SingleColorMaterial(final Color color) {
        if (color == null) throw new IllegalArgumentException("must not be null");
        this.color = color;
    }

    @Override
    public Color colorFor(final Hit hit, final World world, final Tracer tracer) {
        return this.color;
    }
}
