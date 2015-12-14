package Raytracing;

/** Epsilon represents the class for precision calculations*/

public class Epsilon {

    /** epsilon precision of doubles */
    public final static double PRECISION = 0.0000001;

    /** function calculation precision for 1 double */
    public static double precisionFor(final double a) {
        return PRECISION * Math.abs(a);
    }

    /** function calculation precision for 2 doubles */
    public static double precisionFor(final double a, final double b) {
        return PRECISION * Math.max(Math.abs(a), Math.abs(b));
    }

    /** function calculation precision for an array of doubles */
    public static double precisionFor(final double... values) {
        double max = 0;
        for(double value: values) {
            double abs = Math.abs(value);
            if (max < abs) max = abs;
        }
        return PRECISION * max;
    }
}
