package Raytracing.Material.Texturing;

import Raytracing.Color;
import Raytracing.Material.Material;

public abstract class Texture{

    public abstract Color colorFor(double u, double v);
}
