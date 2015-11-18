package Raytracing.Material;

import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.World;

public class SingleColorMaterial extends Material{
    public final Color color;


    public SingleColorMaterial(Color color) {
        this.color = color;
    }

    @Override
    public Color colorFor(Hit hit, World world) {
        return null;
    }
}
