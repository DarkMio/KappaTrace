package Raytracing;

/**
 * Color represents class for color determination in RGB
 */
public class Color {

    /** double determining the r color space */
    public final double r;
    /** double determining the g color space */
    public final double g;
    /** double determining the b color space */
    public final double b;

    /** constructor used to determine the color space of an object */
    public Color(double r, double g, double b) {
        if(r < 0 || r > 1) throw new IllegalArgumentException("0 =< r =< 1"); // Throw exceptions? Or recalc with modulo?
        if(g < 0 || g > 1) throw new IllegalArgumentException("0 =< g =< 1");
        if(b < 0 || b > 1) throw new IllegalArgumentException("0 =< g =< 1");
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Add this Color with another Color
     * @param c Color to add
     * @return resulting new Color
     */
    public Color add(Color c) {
        return new Color(r + c.r, g + c.g, b + c.b);
    }

    /**
     * Substract this Color from another Color
     * @param c Color to substract
     * @return resulting new Color
     */
    public Color sub(Color c) {
        return new Color(r - c.r, g - c.g, b - c.b);
    }

    /**
     * Multiply this Color with another Color
     * @param c Color to multiply
     * @return resulting new Color
     */
    public Color mul(Color c) {
       return new Color(r * c.r, g * c.g, b * c.b);
    }

    /**
     * Multiply this Color with a double
     * @param v double to multiply
     * @return resulting new Color
     */
    public Color mul(double v) {
        return new Color(r * v, g * v, b * v);
    }

    /** calculates from double color to single color int */
    public int toIntRGB() {
        int color = (int) (r * 255) << 16;
        color += (int) (g*255) << 8;
        color += (int) (b*255);
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Color color = (Color) o;

        if (Double.compare(color.r, r) != 0) return false;
        if (Double.compare(color.g, g) != 0) return false;
        return Double.compare(color.b, b) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(r);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(g);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
