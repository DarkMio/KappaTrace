package Raytracing.Material;

import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.World;

/**
 * Created by Mio on 17.11.2015.
 */
public class LambertMaterial extends Material {
    public final Color color;

    public LambertMaterial(Color color) {
        this.color = color;
    }

    @Override
    public Color colorFor(Hit hit, World world) {
        return null;
    }
}
