package MathFunc;

/**
 * Created by Christoph on 22.12.2015.
 */
public class Mat4x4 {

    /** m11 - first row, first column of matrix */
    public final double m11;
    /** m12 - first row, second column of matrix */
    public final double m12;
    /** m13 - first row, third column of matrix */
    public final double m13;
    public final double m14;
    /** m21 - second row, first column of matrix */
    public final double m21;
    /** m22 - second row, second column of matrix */
    public final double m22;
    /** m23 - second row, third column of matrix */
    public final double m23;
    public final double m24;
    /** m31 - third row, first column of matrix */
    public final double m31;
    /** m32 - third row, second column of matrix */
    public final double m32;
    /** m33 - third row, third column of matrix */
    public final double m33;
    public final double m34;
    public final double m41;
    public final double m42;
    public final double m43;
    public final double m44;

    /** determinant - the determinant of the matrix */
    public final double determinant;

    /** Abstract constructor for a 3x3 Matrix */
    public Mat4x4(final double m11, final double m12, final double m13, final double m14,
                  final double m21, final double m22, final double m23, final double m24,
                  final double m31, final double m32, final double m33, final double m34,
                  final double m41, final double m42, final double m43, final double m44) {
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m14 = m14;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m24 = m24;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
        this.m34 = m34;
        this.m41 = m41;
        this.m42 = m42;
        this.m43 = m43;
        this.m44 = m44;
        determinant = m11 * m22 * m33 * m44
                + m12 * m23 * m34 * m41
                + m13 * m24 * m31 * m42
                + m14 * m21 * m32 * m43
                - m41 * m32 * m23 * m14
                - m42 * m33 * m24 * m11
                - m43 * m34 * m21 * m12
                - m44 * m31 * m22 * m13;
    }

    public Mat4x4 mul(final Mat4x4 m) {
        if (m == null) throw new IllegalArgumentException("Cannot be null");
        final double mx11, mx12, mx13, mx14, mx21, mx22, mx23, mx24, mx31, mx32, mx33, mx34, mx41, mx42, mx43, mx44;
        mx11 = m11 * m.m11 + m12 * m.m21 + m13 * m.m31 + m14 * m.m41;
        mx12 = m11 * m.m12 + m12 * m.m22 + m13 * m.m32 + m14 * m.m42;
        mx13 = m11 * m.m13 + m12 * m.m23 + m13 * m.m33 + m14 * m.m43;
        mx14 = m11 * m.m14 + m12 * m.m24 + m13 * m.m34 + m14 * m.m44;
        mx21 = m21 * m.m11 + m22 * m.m21 + m23 * m.m31 + m24 * m.m41;
        mx22 = m21 * m.m12 + m22 * m.m22 + m23 * m.m32 + m24 * m.m42;
        mx23 = m21 * m.m13 + m22 * m.m23 + m23 * m.m33 + m24 * m.m43;
        mx24 = m21 * m.m14 + m22 * m.m24 + m23 * m.m34 + m24 * m.m44;
        mx31 = m31 * m.m11 + m32 * m.m21 + m33 * m.m31 + m34 * m.m41;
        mx32 = m31 * m.m12 + m32 * m.m22 + m33 * m.m32 + m34 * m.m42;
        mx33 = m31 * m.m13 + m32 * m.m23 + m33 * m.m33 + m34 * m.m43;
        mx34 = m31 * m.m14 + m32 * m.m24 + m33 * m.m34 + m34 * m.m43;
        mx41 = m41 * m.m11 + m42 * m.m21 + m43 * m.m31 + m44 * m.m41;
        mx42 = m41 * m.m12 + m42 * m.m22 + m43 * m.m32 + m44 * m.m42;
        mx43 = m41 * m.m13 + m42 * m.m23 + m43 * m.m33 + m44 * m.m43;
        mx44 = m41 * m.m14 + m42 * m.m24 + m43 * m.m34 + m44 * m.m44;
        return new Mat4x4(mx11, mx12, mx13, mx14, mx21, mx22, mx23, mx24, mx31, mx32, mx33, mx34, mx41, mx42, mx43, mx44);
    }

    public Vector3 mul(final Vector3 v) {
        if (v == null) throw new IllegalArgumentException("must not be null");
        final double v1, v2, v3, mg;
        v1 = m11*v.x + m12*v.y + m13*v.z + m14*0;
        v2 = m21*v.x + m22*v.y + m23*v.z + m24*0;
        v3 = m31*v.x + m32*v.y + m33*v.z + m34*0;
        mg = Math.sqrt(v1*v1 + v2*v2 + v3*v3);
        return new Vector3(v1, v2, v3, mg);
    }

    public Point3 mul(final Point3 p) {
        if (p == null) throw new IllegalArgumentException("Must not be null");
        final double p1, p2, p3;
        p1 = m11*p.x + m12*p.y + m13*p.z + m14*1;
        p2 = m21*p.x + m22*p.y + m23*p.z + m24*1;
        p3 = m31*p.x + m32*p.y + m33*p.z + m34*1;
        return new Point3(p1, p2, p3);
    }

}
