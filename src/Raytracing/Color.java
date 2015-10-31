package Raytracing;

public class Color {

    public final double r, g, b;

    public Color(double r, double g, double b) {
        if(r < 0 || r > 1) throw new IllegalArgumentException("0 =< r =< 1"); // Throw exceptions? Or recalc with modulo?
        if(g < 0 || g > 1) throw new IllegalArgumentException("0 =< g =< 1");
        if(b < 0 || b > 1) throw new IllegalArgumentException("0 =< g =< 1");
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color add(Color c) {
        return new Color(r + c.r, g + c.g, b + c.b);
    }

    public Color sub(Color c) {
        return new Color(r - c.r, g - c.g, b - c.b);
    }

    public Color mul(Color c) {
       return new Color(r * c.r, g * c.g, b * c.b);
    }

    public Color mul(double v) {
        return new Color(r * v, g * v, b * v);
    }
}
