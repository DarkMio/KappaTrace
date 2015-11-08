package Raytracing.Geometry;

import MathFunc.Point3;
import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.Ray;

public class AxisAlignedBox extends Geometry{

    public final Point3 lbf, run;

    public AxisAlignedBox(Color c, Point3 lbf, Point3 run) {
        super(c);
        this.lbf = lbf;
        this.run = run;
    }

    @Override
    public Hit hit(Ray r) {
        return null;
    }
}
