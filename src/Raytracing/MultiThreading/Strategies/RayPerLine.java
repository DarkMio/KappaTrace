package Raytracing.MultiThreading.Strategies;

import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.MultiThreading.MultiRaytracer;
import Raytracing.Ray;
import Raytracing.Tracer;

/**
 * Created by Mio on 07/01/2016.
 */
public class RayPerLine extends RayStrategy {

    public final int width, height;

    public RayPerLine(MultiRaytracer rt, int offset, int threads) {
        super(rt, offset, threads);
        this.width = rt.img.getWidth();
        this.height = rt.img.getHeight();
    }

    @Override
    public void run() {
        Color pixelColor;
        for(int x = offset; x < width; x += threads) {
            for(int y = 1; y < height; y++) {
                pixelColor = renderPixel(x, y);
                multiTracer.img.setRGB(x, height-y, pixelColor.toIntRGB());
            }
            multiTracer.update();
        }
        multiTracer.complete();
    }
}
