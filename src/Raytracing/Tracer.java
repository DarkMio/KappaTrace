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
        if(recusion < 0)return new Color(1, 1, 1);
        if(world.hit(ray)==null)return world.backgroundColor;
        return world.hit(ray).geo.material.colorFor(world.hit(ray), world, new Tracer(recusion-1 ,world));
    }
}
