package MathFunc;


public class Mat4x4 {

    public final double m11;
    public final double m12;
    public final double m13;
    public final double m14;
    public final double m21;
    public final double m22;
    public final double m23;
    public final double m24;
    public final double m31;
    public final double m32;
    public final double m33;
    public final double m34;
    public final double m41;
    public final double m42;
    public final double m43;
    public final double m44;
    public final double determinant;


    public Mat4x4(double m11, double m12, double m13, double m14,
                  double m21, double m22, double m23, double m24,
                  double m31, double m32, double m33, double m34,
                  double m41, double m42, double m43, double m44) {
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

    public Vector3 mul(Vector3 o) { // o = other
        if (o == null) throw new IllegalArgumentException("Parameter must not be null");
        return new Vector3(m11 * o.x + m12 * o.y + m13 * o.z,
                           m21 * o.x + m22 * o.y + m23 * o.z,
                           m31 * o.x + m32 * o.y + m33 * o.z);
    }

    public Point3 mul(Point3 o) { // o = other
        if (o == null) throw new IllegalArgumentException("Parameter must not be null");
        return new Point3(m11 * o.x + m12 * o.y + m13 * o.z + m14,
                          m21 * o.x + m22 * o.y + m23 * o.z + m24,
                          m31 * o.x + m32 * o.y + m33 * o.z + m34);
    }

    public Mat4x4 mul(Mat4x4 o) { // o = other, n = new
        if (o == null) throw new IllegalArgumentException("Parameter must not be null");
        // Following segment is easier to debug (probably not needed tho)
        final double n11 = m11 * o.m11 + m12 * o.m21 + m13 * o.m31 + m14 * o.m41;
        final double n12 = m11 * o.m12 + m12 * o.m22 + m13 * o.m32 + m14 * o.m42;
        final double n13 = m11 * o.m13 + m12 * o.m23 + m13 * o.m33 + m14 * o.m43;
        final double n14 = m11 * o.m14 + m12 * o.m24 + m13 * o.m34 + m14 * o.m44;
        final double n21 = m21 * o.m11 + m22 * o.m21 + m23 * o.m31 + m24 * o.m41;
        final double n22 = m21 * o.m12 + m22 * o.m22 + m23 * o.m32 + m24 * o.m42;
        final double n23 = m21 * o.m13 + m22 * o.m23 + m23 * o.m33 + m24 * o.m43;
        final double n24 = m21 * o.m14 + m22 * o.m24 + m23 * o.m34 + m24 * o.m44;
        final double n31 = m31 * o.m11 + m32 * o.m21 + m33 * o.m31 + m34 * o.m41;
        final double n32 = m31 * o.m12 + m32 * o.m22 + m33 * o.m32 + m34 * o.m42;
        final double n33 = m31 * o.m13 + m32 * o.m23 + m33 * o.m33 + m34 * o.m43;
        final double n34 = m31 * o.m14 + m32 * o.m24 + m33 * o.m34 + m34 * o.m44;
        final double n41 = m41 * o.m11 + m42 * o.m21 + m43 * o.m31 + m44 * o.m41;
        final double n42 = m41 * o.m12 + m42 * o.m22 + m43 * o.m32 + m44 * o.m42;
        final double n43 = m41 * o.m13 + m42 * o.m23 + m43 * o.m33 + m44 * o.m43;
        final double n44 = m41 * o.m14 + m42 * o.m24 + m43 * o.m34 + m44 * o.m44;
        return new Mat4x4(n11, n12, n13, n14,
                          n21, n22, n23, n24,
                          n31, n32, n33, n34,
                          n41, n42, n43, n44);
    }

    public Mat4x4 transpose() {
        return new Mat4x4(m11, m21, m31, m41,
                          m12, m22, m32, m42,
                          m13, m23, m33, m43,
                          m14, m24, m34, m44);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mat4x4 mat4x4 = (Mat4x4) o;

        if (Double.compare(mat4x4.m11, m11) != 0) return false;
        if (Double.compare(mat4x4.m12, m12) != 0) return false;
        if (Double.compare(mat4x4.m13, m13) != 0) return false;
        if (Double.compare(mat4x4.m14, m14) != 0) return false;
        if (Double.compare(mat4x4.m21, m21) != 0) return false;
        if (Double.compare(mat4x4.m22, m22) != 0) return false;
        if (Double.compare(mat4x4.m23, m23) != 0) return false;
        if (Double.compare(mat4x4.m24, m24) != 0) return false;
        if (Double.compare(mat4x4.m31, m31) != 0) return false;
        if (Double.compare(mat4x4.m32, m32) != 0) return false;
        if (Double.compare(mat4x4.m33, m33) != 0) return false;
        if (Double.compare(mat4x4.m34, m34) != 0) return false;
        if (Double.compare(mat4x4.m41, m41) != 0) return false;
        if (Double.compare(mat4x4.m42, m42) != 0) return false;
        if (Double.compare(mat4x4.m43, m43) != 0) return false;
        if (Double.compare(mat4x4.m44, m44) != 0) return false;
        return Double.compare(mat4x4.determinant, determinant) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(m11);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m12);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m13);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m14);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m21);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m22);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m23);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m24);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m31);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m32);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m33);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m34);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m41);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m42);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m43);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m44);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(determinant);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
