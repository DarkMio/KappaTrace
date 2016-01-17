package Raytracing.Material.Texturing;

import Raytracing.Color;

public class SingleColorTexture extends Texture {

    final private Color c;

    public SingleColorTexture(Color c) {
        this.c = c;
    }

    @Override
    public Color colorFor(double u, double v) {
        return c;
    }
}
