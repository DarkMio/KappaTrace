package Raytracing.Material.Texturing;

public class TexturePosition {

    public final double u, v;

    public TexturePosition(double u, double v) {
        this.u = u - Math.floor(u);
        this.v = v - Math.floor(v);
    }

    public TexturePosition mul(double d) {
        return new TexturePosition(u*d, v*d);
    }

    public TexturePosition add(TexturePosition tp) {
        return new TexturePosition(u + tp.u, v + tp.v);
    }
}
