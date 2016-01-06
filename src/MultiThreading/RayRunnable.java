package MultiThreading;

import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.Ray;
import Raytracing.Tracer;

public class RayRunnable implements Runnable {

    final MultiRaytracer multiTracer;
    final int xMin;
    final int xMax;
    final int yMin;
    final int yMax;


    public RayRunnable(MultiRaytracer rt, int xMin, int xMax, int yMin, int yMax) {
        this.multiTracer = rt;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    public void run() {
        for(int x = xMin; x < xMax; x++) {
            for(int y = yMin; y < yMax; y++) {
                final Ray[] rays = multiTracer.cam.rayFor(multiTracer.width, multiTracer.height, x, y);
                Color finalColor = new Color(0, 0, 0);
                for (Ray ray : rays) {
                    final Hit h = multiTracer.world.hit(ray);
                    if (h == null) finalColor = finalColor.add(multiTracer.world.backgroundColor);
                    else finalColor = finalColor.add(h.geo.material.colorFor(h, multiTracer.world,
                            new Tracer(8, multiTracer.world)));
                }
                finalColor = finalColor.mul(1 / (double) rays.length);
                multiTracer.img.setRGB(x, multiTracer.height-y-1, finalColor.toIntRGB());
                /*
                final Hit h = multiTracer.world.hit(multiTracer.cam.rayFor(multiTracer.width, multiTracer.height, x, y));
                if(h == null) multiTracer.img.setRGB(x, multiTracer.height-y-1, multiTracer.world.backgroundColor.toIntRGB());
                else {
                    multiTracer.img.setRGB(x, multiTracer.height-y-1,
                            h.geo.material.colorFor(h, multiTracer.world, new Tracer(8,multiTracer.world)).toIntRGB());
                }
                */
            }
            multiTracer.update();
        }
        multiTracer.complete();
    }
}
