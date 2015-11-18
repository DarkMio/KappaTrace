package Raytracing.Material;

import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.World;

public class PhongMaterial extends Material {
    public final Color diffuse;
    public final Color specular;
    public final int exponent;

    public PhongMaterial(Color diffuse, Color specular, int exponent) {
        this.diffuse = diffuse;
        this.specular = specular;
        this.exponent = exponent;
    }

    @Override
    public Color colorFor(Hit hit, World world) {
        return null;
    }
}
