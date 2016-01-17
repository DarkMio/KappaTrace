package Raytracing.Material.Texturing;

public class TexCoord2 {

    public final double u, v;

    public TexCoord2(double u, double v) {
        //@TODO: Pretty sure this goes more elegant.
        if(u == 1) {
            this.u = u;
        } else {
            this.u = u - Math.floor(u);
        }
        if(v == 1) {
            this.v = 1;
        } else {
            this.v = v - Math.floor(v);
        }
    }

    public TexCoord2 mul(double d) {
        return new TexCoord2(u*d, v*d);
    }

    public TexCoord2 add(TexCoord2 tp) {
        return new TexCoord2(u + tp.u, v + tp.v);
    }
}
