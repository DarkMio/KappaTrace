package Raytracing;

import Raytracing.Geometry.Geometry;

public class Hit {

    public final double r;
    public final Ray ray;
    public final Geometry geo;

    public Hit(double r, Ray ray, Geometry geo) {
        this.r = r;
        this.ray = ray;
        this.geo = geo;
    }
}
