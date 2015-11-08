package Raytracing.Geometry;

import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.Ray;

public abstract class Geometry {

    public final Color color;

    public Geometry(Color color) {
        this.color = color;
    }

    public abstract Hit hit(Ray r);
}
