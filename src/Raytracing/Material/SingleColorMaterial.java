package Raytracing.Material;

import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.World;

public class SingleColorMaterial extends Material{

    /** Color representing a single color for SingleColorMaterial */
    public final Color color;


    /** Constructor used to create a SingleColorMaterial with a Color color */
    public SingleColorMaterial(Color color) {
        this.color = color;
    }

    @Override
    public Color colorFor(Hit hit, World world) {
        return this.color;
    }
}
