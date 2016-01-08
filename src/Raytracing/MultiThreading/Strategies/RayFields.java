package Raytracing.MultiThreading.Strategies;

import Raytracing.Color;
import Raytracing.MultiThreading.MultiRaytracer;

/**
 * Created by Mio on 08/01/2016.
 */
public class RayFields extends RayStrategy {

    final int xMin;
    final int xMax;
    final int yMin;
    final int yMax;
    final int height;
    final int width;

    public RayFields(MultiRaytracer rt, int offset, int threads) {
        super(rt, offset, threads);
        int xThread;
        int yThread;
        xThread = (int) Math.sqrt(threads);
        if (threads % 2 == 1) {
            yThread = threads / xThread + 1;
        } else {
            yThread = threads / xThread;
        }
        int x = offset % xThread;
        int y = offset % yThread;
        System.out.print("x=" + x + " y=" + y + " | ");
        height = rt.img.getHeight();
        width = rt.img.getWidth();
        xMin = width * (x) / xThread;
        xMax = width * (x+1) / xThread;
        yMin = height * y / yThread;
        yMax = height * (y + 1) / yThread;
        System.err.println("xMin=" + xMin + " xMax=" + xMax + " yMin=" + yMin + " yMax=" + yMax);
    }

    public void run() {
        Color pixelColor;
        for(int x = xMin; x < xMax; x++) {
            for(int y = yMin; y < yMax; y++) {
                pixelColor = renderPixel(x, y);
                multiTracer.img.setRGB(x, height-(y+1), pixelColor.toIntRGB());
            }
            multiTracer.update();
        }
        multiTracer.complete();
    }
}
