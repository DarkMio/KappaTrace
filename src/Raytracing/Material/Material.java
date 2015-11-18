package Raytracing.Material;

import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.World;

public abstract class Material {
    public abstract Color colorFor(Hit hit, World world);
}
