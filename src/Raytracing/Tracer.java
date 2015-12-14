package Raytracing;

/** Tracer represents class for color tracing*/

public class Tracer {

    /**int representing */
    final private int recursion;
    /**World representing a world object */
    final World world;

    /** constructor used to create Tracer objects
     * @param recursion int
     * @param world World - must not be null
     */
    public Tracer(final int recursion, final World world){
        if(world == null) throw new IllegalArgumentException("wolrd must not be null!");
        this.world=world;
        this.recursion = recursion;
    }

    public Color traceColor(final Ray ray){
        if(recursion <= 0)return world.backgroundColor;
        if(world.hit(ray) != null) return world.hit(ray).geo.material.colorFor(world.hit(ray), world, new Tracer(recursion -1 ,world));
        return world.backgroundColor;
    }
}
