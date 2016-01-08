package Raytracing.MultiThreading.Strategies;

import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.MultiThreading.MultiRaytracer;
import Raytracing.Ray;
import Raytracing.Tracer;

public abstract class RayStrategy implements Runnable {
    public final MultiRaytracer multiTracer;
    public final int offset;
    public final int threads;

    public RayStrategy(MultiRaytracer rt, int offset, int threads) {
        this.multiTracer = rt;
        this.offset = offset;
        this.threads = threads;
    }

    protected Color renderPixel(int x, int y) {
        final Ray[] rays = multiTracer.cam.rayFor(multiTracer.width, multiTracer.height, x, y);
        Color finalColor = new Color(0, 0, 0);
        for (Ray ray : rays) {
            final Hit h = multiTracer.world.hit(ray);
            if (h == null) finalColor = finalColor.add(multiTracer.world.backgroundColor);
            else finalColor = finalColor.add(h.geo.material.colorFor(h, multiTracer.world,
                    new Tracer(8, multiTracer.world)));
        }
        finalColor = finalColor.mul(1 / (double) rays.length);
        return finalColor;
    }

    public abstract void run();

}
