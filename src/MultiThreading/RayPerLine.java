package MultiThreading;

import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.Ray;
import Raytracing.Tracer;

/**
 * Created by Mio on 07/01/2016.
 */
public class RayPerLine implements Runnable {

    public final int width, height, offset, threads;

    public final MultiRaytracer multiTracer;

    public RayPerLine(MultiRaytracer rt, int offset, int threads) {
        this.multiTracer = rt;
        this.width = rt.img.getWidth();
        this.height = rt.img.getHeight();
        this.offset = offset;
        this.threads = threads;
    }

    @Override
    public void run() {
        Color pixelColor;
        for(int x = offset; x < width; x += threads) {
            for(int y = 1; y < height; y++) {
                pixelColor = renderPixel(x, y);
                multiTracer.img.setRGB(x, height-y, pixelColor.toIntRGB());
                // multiTracer.img.setRGB(x, multiTracer.height-y, finalColor.toIntRGB());
            }
            multiTracer.update();
        }
        multiTracer.complete();
        System.err.println("Done: " + width + " " + height);
    }

    private Color renderPixel(int x, int y) {
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
}
