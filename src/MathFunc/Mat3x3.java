package MathFunc;

/**
 * Mat3x3 accepting 3x3 vectors.
 */
final public class Mat3x3 {

    /**
     * m11 - first row, first column of matrix
     */
    public final double m11;
    /**
     * m12 - first row, second column of matrix
     */
    public final double m12;
    /**
     * m13 - first row, third column of matrix
     */
    public final double m13;
    /**
     * m21 - second row, first column of matrix
     */
    public final double m21;
    /**
     * m22 - second row, second column of matrix
     */
    public final double m22;
    /**
     * m23 - second row, third column of matrix
     */
    public final double m23;
    /**
     * m31 - third row, first column of matrix
     */
    public final double m31;
    /**
     * m32 - third row, second column of matrix
     */
    public final double m32;
    /**
     * m33 - third row, third column of matrix
     */
    public final double m33;
    /**
     * determinant - the determinant of the matrix
     */
    public final double determinant;

    /**
     * Abstract constructor for a 3x3 Matrix
     */
    public Mat3x3(final double m11, final double m12, final double m13,
                  final double m21, final double m22, final double m23,
                  final double m31, final double m32, final double m33) {
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
        determinant = m11 * m22 * m33
                + m12 * m23 * m31
                + m13 * m21 * m32
                - m31 * m22 * m13
                - m32 * m23 * m11
                - m33 * m21 * m12;
    }

    /**
     * Multiply this Mat3x3 with another Mat3x3
     *
     * @param m Mat3x3 to multiply - must not be null
     * @return resulting new Mat3x3
     */
    public Mat3x3 mul(final Mat3x3 m) {
        if (m == null) throw new IllegalArgumentException("Cannot be null");
        final double mx11, mx12, mx13, mx21, mx22, mx23, mx31, mx32, mx33;
        mx11 = m11 * m.m11 + m12 * m.m21 + m13 * m.m31;
        mx12 = m11 * m.m12 + m12 * m.m22 + m13 * m.m32;
        mx13 = m11 * m.m13 + m12 * m.m23 + m13 * m.m33;
        mx21 = m21 * m.m11 + m22 * m.m21 + m23 * m.m31;
        mx22 = m21 * m.m12 + m22 * m.m22 + m23 * m.m32;
        mx23 = m21 * m.m13 + m22 * m.m23 + m23 * m.m33;
        mx31 = m31 * m.m11 + m32 * m.m21 + m33 * m.m31;
        mx32 = m31 * m.m12 + m32 * m.m22 + m33 * m.m32;
        mx33 = m31 * m.m13 + m32 * m.m23 + m33 * m.m33;
        return new Mat3x3(mx11, mx12, mx13, mx21, mx22, mx23, mx31, mx32, mx33);
    }

    /**
     * Multiply a Vector3 to this Mat3x3
     *
     * @param v Vector3 to be multiplied on Mat3x3 - must not be null
     * @return resulting new Vector3
     */
    public Vector3 mul(final Vector3 v) {
        if (v == null) throw new IllegalArgumentException("must not be null");
        final double v1, v2, v3, mg;
        v1 = m11 * v.x + m12 * v.y + m13 * v.z;
        v2 = m21 * v.x + m22 * v.y + m23 * v.z;
        v3 = m31 * v.x + m32 * v.y + m33 * v.z;
        mg = Math.sqrt(v1 * v1 + v2 * v2 + v3 * v3);
        return new Vector3(v1, v2, v3, mg);
    }

    /**
     * Multiply Point3 on Mat3x3
     *
     * @param p Point3 to be multiplied on Mat3x3 - must not be null
     * @return resulting new Point3
     */
    public Point3 mul(final Point3 p) {
        if (p == null) throw new IllegalArgumentException("Must not be null");
        final double p1, p2, p3;
        p1 = m11 * p.x + m12 * p.y + m13 * p.z;
        p2 = m21 * p.x + m22 * p.y + m23 * p.z;
        p3 = m31 * p.x + m32 * p.y + m33 * p.z;
        return new Point3(p1, p2, p3);
    }

    /**
     * Change the first column with values of this Vector3
     *
     * @param v Vector3 containing new Data for the column - must not be null
     * @return resulting new Mat3x3
     */
    public Mat3x3 changeCol1(final Vector3 v) {
        if (v == null) throw new IllegalArgumentException("Must not be null");
        return new Mat3x3(v.x, m12, m13,
                v.y, m22, m23,
                v.z, m32, m33);
    }

    /**
     * Change the second column with values of this Vector3
     *
     * @param v Vector3 containing new Data for the column - must not be null
     * @return resulting new Mat3x3
     */
    public Mat3x3 changeCol2(final Vector3 v) {
        if (v == null) throw new IllegalArgumentException("Must not be null");
        return new Mat3x3(m11, v.x, m13,
                m21, v.y, m23,
                m31, v.z, m33);
    }

    /**
     * Change the third column with values of this Vector3
     *
     * @param v Vector3 containing new Data for the column - must not be null
     * @return resulting new Mat3x3
     */
    public Mat3x3 changeCol3(final Vector3 v) {
        if (v == null) throw new IllegalArgumentException("Must not be null");
        return new Mat3x3(m11, m12, v.x,
                m21, m22, v.y,
                m31, m32, v.z);
    }

    @Override
    public String toString() {
        return String.format("Mat3x3:  (%8.2f, %8.2f, %8.2f)\n" +
                        "         (%8.2f, %8.2f, %8.2f)\n" +
                        "         (%8.2f, %8.2f, %8.2f)",
                m11, m12, m13, m21, m22, m23, m31, m32, m33);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mat3x3 mat3x3 = (Mat3x3) o;

        if (Double.compare(mat3x3.m11, m11) != 0) return false;
        if (Double.compare(mat3x3.m12, m12) != 0) return false;
        if (Double.compare(mat3x3.m13, m13) != 0) return false;
        if (Double.compare(mat3x3.m21, m21) != 0) return false;
        if (Double.compare(mat3x3.m22, m22) != 0) return false;
        if (Double.compare(mat3x3.m23, m23) != 0) return false;
        if (Double.compare(mat3x3.m31, m31) != 0) return false;
        if (Double.compare(mat3x3.m32, m32) != 0) return false;
        return Double.compare(mat3x3.m33, m33) == 0;

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
        temp = Double.doubleToLongBits(m21);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m22);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m23);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m31);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m32);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m33);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
