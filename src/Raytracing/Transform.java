package Raytracing;

import MathFunc.Mat4x4;
import MathFunc.Normal3;
import MathFunc.Vector3;

public class Transform {
    public final Mat4x4 m;
    public final Mat4x4 i;

    public Transform() {
        m = new Mat4x4(1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1);
        i = new Mat4x4(1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1);
    }

    private Transform(Mat4x4 m, Mat4x4 i) {
        this.m = m;
        this.i = i;
    }

    /**
     * You have to trust me with this code segment.
     * Translating a matrix is actually pretty easy,
     * imagine following scenario, you have following 4x4 matrix:
     * | a  b  c  d
     * --+------------
     * w | wa wb wc wd
     * x | xa xb xc xd
     * y | ya yb yc yd
     * z | za zb zc zd
     * <p>
     * then we just have to do the multiplication for following positions:
     * wd, xd, yd, zd - which are copy pasted from the Mat4x4 class.
     * Since the Mat4x4 is tested (a bit, hopefully, maybe) this should work fine and
     * is about ~4 times faster than doing all unnecessary multiplications.
     * <p>
     * And before you have to debug it:
     * - tMat -> transform matrix
     * - iMat -> inverse transform matrix
     *
     * @param v Vector3 to do the translation on
     * @return transform matrix and inverse transformation matrix
     */
    public Transform translate(Vector3 v) {
        final double tMatW = m.m11 * v.x + m.m12 * v.y + m.m13 * v.z + m.m14;
        final double tMatX = m.m21 * v.x + m.m22 * v.y + m.m23 * v.z + m.m24;
        final double tMatY = m.m31 * v.x + m.m32 * v.y + m.m33 * v.z + m.m34;
        final double tMatZ = m.m41 * v.x + m.m42 * v.y + m.m43 * v.z + m.m44;
        final Mat4x4 tMat = new Mat4x4(m.m11, m.m12, m.m13, tMatW,
                m.m21, m.m22, m.m23, tMatX,
                m.m31, m.m32, m.m33, tMatY,
                m.m41, m.m42, m.m43, tMatZ);
        final Vector3 in = v.mul(-1);
        final double iMatW = i.m11 * in.x + i.m12 * in.y + i.m13 * in.z + i.m14;
        final double iMatX = i.m21 * in.x + i.m22 * in.y + i.m23 * in.z + i.m24;
        final double iMatY = i.m31 * in.x + i.m32 * in.y + i.m33 * in.z + i.m34;
        final double iMatZ = i.m41 * in.x + i.m42 * in.y + i.m43 * in.z + i.m44;
        final Mat4x4 iMat = new Mat4x4(i.m11, i.m12, i.m13, iMatW,
                i.m21, i.m22, i.m23, iMatX,
                i.m31, i.m32, i.m33, iMatY,
                i.m41, i.m42, i.m43, iMatZ);
        return new Transform(tMat, iMat);
    }

    /**
     * This case isn't as easy - but still possible to shortcut it by a bit
     * Since we're changing the identity matrix like this:
     * | a  b  c  d
     * --+------------
     * w | vx
     * x |    vy
     * y |       vz
     * z |          1
     * It is only necessary to multiply all values within one row with
     * the value of the vector - or leave it, in case of row d.
     *
     * @param v
     * @return
     */
    public Transform scale(Vector3 v) {
        final Mat4x4 tMat = new Mat4x4(m.m11 * v.x, m.m12 * v.y, m.m13 * v.z, m.m14,
                m.m21 * v.x, m.m22 * v.y, m.m23 * v.z, m.m24,
                m.m31 * v.x, m.m32 * v.y, m.m33 * v.z, m.m34,
                m.m41 * v.x, m.m42 * v.y, m.m43 * v.z, m.m44);
        final Mat4x4 iMat = new Mat4x4(i.m11 * 1 / v.x, i.m12 * 1 / v.y, i.m13 * 1 / v.z, i.m14,
                i.m21 * 1 / v.x, i.m22 * 1 / v.y, i.m23 * 1 / v.z, i.m24,
                i.m31 * 1 / v.x, i.m32 * 1 / v.y, i.m33 * 1 / v.z, i.m34,
                i.m41 * 1 / v.x, i.m42 * 1 / v.y, i.m43 * 1 / v.z, i.m44);
        return new Transform(tMat, iMat);
    }

    /**
     * | a  b  c  d
     * --+------------
     * w | 1
     * x |    an an
     * y |    an an
     * z |          1
     *
     * @param angle
     * @return
     */
    public Transform rotateX(double angle) {
        final double sin = Math.sin(angle);
        final double cos = Math.cos(angle);
        final Mat4x4 tMat = new Mat4x4(m.m11, m.m12 * cos + m.m13 * sin, m.m12 * -sin + m.m13 * cos, m.m14,
                m.m21, m.m22 * cos + m.m23 * sin, m.m22 * -sin + m.m23 * cos, m.m24,
                m.m31, m.m32 * cos + m.m33 * sin, m.m32 * -sin + m.m33 * cos, m.m34,
                m.m41, m.m42 * cos + m.m43 * sin, m.m42 * -sin + m.m43 * cos, m.m44);
        final Mat4x4 iMat = new Mat4x4(i.m11, i.m12 * cos + i.m13 * -sin, i.m12 * sin + i.m13 * cos, i.m14,
                i.m21, i.m22 * cos + i.m23 * -sin, i.m22 * sin + i.m23 * cos, i.m24,
                i.m31, i.m32 * cos + i.m33 * -sin, i.m32 * sin + i.m33 * cos, i.m34,
                i.m41, i.m42 * cos + i.m43 * -sin, i.m42 * sin + i.m43 * cos, i.m44);
        return new Transform(tMat, iMat);
    }

    /**
     * | a  b  c  d
     * --+------------
     * w | an    an
     * x |    1
     * y | an    an
     * z |          1
     *
     * @param angle
     * @return
     */
    public Transform rotateY(double angle) {
        final double sin = Math.sin(angle);
        final double cos = Math.cos(angle);
        final Mat4x4 tMat = new Mat4x4(m.m11 * cos + m.m13 * -sin, m.m12, m.m11 * sin + m.m13 * cos, m.m14,
                m.m21 * cos + m.m23 * -sin, m.m22, m.m21 * sin + m.m23 * cos, m.m24,
                m.m31 * cos + m.m33 * -sin, m.m32, m.m31 * sin + m.m33 * cos, m.m34,
                m.m41 * cos + m.m43 * -sin, m.m42, m.m41 * sin + m.m43 * cos, m.m44);
        final Mat4x4 iMat = new Mat4x4(i.m11 * cos + i.m13 * sin, i.m12, i.m11 * -sin + i.m13 * cos, i.m14,
                i.m21 * cos + i.m23 * sin, i.m22, i.m21 * -sin + i.m23 * cos, i.m24,
                i.m31 * cos + i.m33 * sin, i.m32, i.m31 * -sin + i.m33 * cos, i.m34,
                i.m41 * cos + i.m43 * sin, i.m42, i.m41 * -sin + i.m43 * cos, i.m44);
        return new Transform(tMat, iMat);
    }

    /**
     * | a  b  c  d
     * --+------------
     * w | an an
     * x | an an
     * y |       1
     * z |          1
     *
     * @param angle
     * @return
     */
    public Transform rotateZ(double angle) {
        final double sin = Math.sin(angle);
        final double cos = Math.cos(angle);
        final Mat4x4 tMat = new Mat4x4(m.m11 * cos + m.m12 * sin, m.m11 * -sin + m.m12 * cos, m.m13, m.m14,
                m.m21 * cos + m.m22 * sin, m.m21 * -sin + m.m22 * cos, m.m23, m.m24,
                m.m31 * cos + m.m32 * sin, m.m31 * -sin + m.m32 * cos, m.m33, m.m34,
                m.m41 * cos + m.m42 * sin, m.m41 * -sin + m.m42 * cos, m.m43, m.m44);

        final Mat4x4 iMat = new Mat4x4(i.m11 * cos + i.m12 * -sin, i.m11 * sin + i.m12 * cos, i.m13, i.m14,
                i.m21 * cos + i.m22 * -sin, i.m21 * sin + i.m22 * cos, i.m23, i.m24,
                i.m31 * cos + i.m32 * -sin, i.m31 * sin + i.m32 * cos, i.m33, i.m34,
                i.m41 * cos + i.m42 * -sin, i.m41 * sin + i.m42 * cos, i.m43, i.m44);
        return new Transform(tMat, iMat);
    }

    public Ray mul(final Ray ray) {
        return new Ray(i.mul(ray.o), i.mul(ray.d));
    }

    public Normal3 mul(final Normal3 normal) {
        return (i.transpose().mul(new Vector3(normal.x, normal.y, normal.z))).normalized().asNormal();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transform transform = (Transform) o;

        if (!m.equals(transform.m)) return false;
        return i.equals(transform.i);

    }

    @Override
    public int hashCode() {
        int result = m.hashCode();
        result = 31 * result + i.hashCode();
        return result;
    }
}
