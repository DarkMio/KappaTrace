package Raytracing.Geometry;

import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.Ray;

public abstract class Geometry {

    public final static double PRECISION = 0.0000001;
    public final Color color;

    public Geometry(Color color) {
        this.color = color;
    }

    public abstract Hit hit(Ray r);

    public static double precisionFor(double a) {
        return PRECISION * Math.abs(a);
    }

    public static double precisionFor(double a, double b) {
        return PRECISION * Math.max(Math.abs(a), Math.abs(b));
    }

    public static double precisionFor(double... values) {
        double max = 0;
        for(double value: values) {
            double abs = Math.abs(value);
            if (max < abs) max = abs;
        }
        return PRECISION * max;
    }
}
