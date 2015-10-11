package Vorbereitung;

final public class Mat3x3 {

    public final double m11, m12, m13, m21, m22, m23, m31, m32, m33;

    public Mat3x3(double m11, double m12, double m13,
                  double m21, double m22, double m23,
                  double m31, double m32, double m33) {
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
    }

    public Mat3x3 mul(Mat3x3 m) {
        double mx11 = m11 * m.m11 + m12 * m.m21 + m13 * m.m31;
        double mx12 = m11 * m.m12 + m12 * m.m22 + m13 * m.m32;
        double mx13 = m11 * m.m13 + m12 * m.m23 + m13 * m.m33;
        double mx21 = m21 * m.m11 + m22 * m.m21 + m23 * m.m31;
        double mx22 = m21 * m.m12 + m22 * m.m22 + m23 * m.m32;
        double mx23 = m21 * m.m13 + m22 * m.m23 + m23 * m.m33;
        double mx31 = m31 * m.m11 + m32 * m.m21 + m33 * m.m31;
        double mx32 = m31 * m.m12 + m32 * m.m22 + m33 * m.m32;
        double mx33 = m31 * m.m13 + m32 * m.m23 + m33 * m.m33;
        return new Mat3x3(mx11, mx12, mx13, mx21, mx22, mx23, mx31, mx32, mx33);
    }

    public Vector3 mul(Vector3 v) {
        final double v1 = m11*v.x + m12*v.y + m13*v.z;
        final double v2 = m21*v.x + m22*v.y + m23*v.z;
        final double v3 = m31*v.x + m32*v.y + m33*v.z;
        final double mg = Math.sqrt(v1*v1 + v2*v2 + v3*v3);
        return new Vector3(v1, v2, v3, mg);
    }

    public Point3 mul(Point3 p) {
        final double p1 = m11*p.x + m12*p.y + m13*p.z;
        final double p2 = m21*p.x + m22*p.y + m23*p.z;
        final double p3 = m31*p.x + m32*p.y + m33*p.z;
        return new Point3(p1, p2, p3);
    }

    public Mat3x3 changeCol1(Vector3 v) {
        return new Mat3x3(v.x, m12, m13,
                v.y, m22, m23,
                v.z, m32, m33);
    }

    public Mat3x3 changeCol2(Vector3 v) {
        return new Mat3x3(m11, v.x, m13,
                          m21, v.y, m23,
                          m31, v.z, m33);
    }
    public Mat3x3 changeCol3(Vector3 v) {
        return new Mat3x3(m11, m12, v.x,
                          m21, m22, v.y,
                          m31, m32, v.z);
    }

    public String toString() {
        return String.format("Mat3x3:  (%8.2f, %8.2f, %8.2f)\n" +
                             "         (%8.2f, %8.2f, %8.2f)\n" +
                             "         (%8.2f, %8.2f, %8.2f)",
                             m11, m12, m13, m21, m22, m23, m31, m32, m33);
    }
}
