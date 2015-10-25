package Math;


import java.io.PrintWriter;

public class MainTests {

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out, true);
        out.println("-- Normal multiplied with decimal:");
        out.println(new Normal3(1, 2, 3).mul(0.5));
        out.println("-- Normal added with normal:");
        out.println(new Normal3(1, 2, 3).add(new Normal3(3, 2, 1)));
        out.println("-- Vector dot Vector, Vector dot Normal, Normal dot Vector = 1:");
        out.println(new Vector3(1, 0, 0).dot(new Vector3(1, 0, 0)));
        out.println(new Vector3(1, 0, 0).dot(new Normal3(1, 0, 0)));
        out.println(new Normal3(1, 0, 0).dot(new Vector3(1, 0, 0)));
        out.println("-- Vector dot Vector, Vector dot Normal, Normal dot Vector = 0");
        out.println(new Vector3(1, 0, 0).dot(new Vector3(0, 1, 0)));
        out.println(new Vector3(1, 0, 0).dot(new Normal3(0, 1, 0)));
        out.println(new Normal3(1, 0, 0).dot(new Vector3(0, 1, 0)));
        out.println("-- Point minus point");
        out.println(new Point3(1, 1, 1).sub(new Point3(2, 2, 0)));
        out.println("-- Point minus vector");
        out.println(new Point3(1, 1, 1).sub(new Vector3(4, 3, 2)));
        out.println("-- Point plus Vector");
        out.println(new Point3(1, 1, 1).add(new Vector3(4, 3, 2)));
        out.println("-- Magnitude");
        out.println(String.format("%5.3f == %5.3f", new Vector3(1, 1, 1).magnitude, Math.sqrt(3)));
        out.println("-- Vector3 add, sub, mul");
        out.println(new Vector3(1, 1, 1).add(new Normal3(3, 2, 1)));
        out.println(new Vector3(1, 1, 1).add(new Vector3(3, 2, 1)));
        out.println(new Vector3(1, 1, 1).sub(new Normal3(3, 2, 1)));
        out.println(new Vector3(1, 1, 1).mul(5));
        out.println("-- Reflection");
        out.println(new Vector3(-0.707, 0.707, 0).reflectedOn(new Normal3(0, 1, 0)));
        out.println(new Vector3(0.707, 0.707, 0).reflectedOn(new Normal3(1, 0, 0)));
        out.println("-- Matrix multiplied with Vector / Point");
        final Mat3x3 mx = new Mat3x3(1, 0, 0, 0, 1, 0, 0, 0, 1);
        final Mat3x3 my = new Mat3x3(1, 2, 3, 4, 5, 6, 7, 8, 9);
        final Mat3x3 mz = new Mat3x3(0, 0, 1, 0, 1, 0, 1, 0, 0);
        out.println(mx.mul(new Point3(3, 2, 1)));
        out.println(mx.mul(new Vector3(3, 2, 1)));
        out.println("-- Matrix multiplied with Matrix");
        out.println(my.mul(mx));
        out.println(my.mul(mz));
        out.println("-- Change columns of matrix");
        final Vector3 eight = new Vector3(8, 8, 8);
        out.println(my.changeCol1(eight));
        out.println(my.changeCol2(eight));
        out.println(my.changeCol3(eight));
    }
}
