package Raytracing;


public class Tracer {
    private int recusion;
    final World world;

    public Tracer(final int recusion, final World world){
        if(world == null) throw new IllegalArgumentException("wolrd must not be null!");
        this.world=world;
        this.recusion=recusion;
    }

    public Color traceColor(final Ray ray){
        if(recusion<1)return world.backgroundColor;
        if(world.hit(ray)==null)return world.backgroundColor;
        return world.hit(ray).geo.material.colorFor(world.hit(ray), world, new Tracer(recusion-1 ,world));
    }
}
