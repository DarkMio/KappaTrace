package MultiThreading;

import Raytracing.Hit;
import Raytracing.Tracer;

public class MyRunnable implements Runnable {

    final MultiRaytracer multiTracer;
    final int xMin;
    final int xMax;
    final int yMin;
    final int yMax;


    public MyRunnable(MultiRaytracer rt, int xMin, int xMax, int yMin, int yMax) {
        this.multiTracer = rt;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    public void run() {
        for(int x = xMin; x < xMax; x++) {
            for(int y = yMin; y < yMax; y++) {
                final Hit h = multiTracer.world.hit(multiTracer.cam.rayFor(multiTracer.width, multiTracer.height, x, y));
                if(h == null) multiTracer.img.setRGB(x, multiTracer.height-y-1, multiTracer.world.backgroundColor.toIntRGB());
                else {
                    multiTracer.img.setRGB(x, multiTracer.height-y-1,
                            h.geo.material.colorFor(h, multiTracer.world, new Tracer(8,multiTracer.world)).toIntRGB());
                }
            }
        }
    }
}
