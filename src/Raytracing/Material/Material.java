package Raytracing.Material;

import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.World;

public abstract class Material {

    /** Abstract constructor used to determine the position of Color with a Hit hit and a World world */
    public abstract Color colorFor(Hit hit, World world);
}
