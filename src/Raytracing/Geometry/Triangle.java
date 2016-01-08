package Raytracing.Geometry;

/**
 * class for Triangle objects
 */

import MathFunc.Mat3x3;
import MathFunc.Normal3;
import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Epsilon;
import Raytracing.Hit;
import Raytracing.Material.Material;
import Raytracing.Ray;

public class Triangle extends Geometry {

    /** Point3 representing first vertex of a triangle */
    public final Point3 a;
    /** Point3 representing second vertex of a triangle */
    public final Point3 b;
    /** Point3 representing third vertex of a triangle */
    public final Point3 c;
    /** Normal3 representing the Normal3 of Point3 a */
    public final Normal3 an;
    /** Normal3 representing the Normal3 of Point3 b */
    public final Normal3 bn;
    /** Normal3 representing the Normal3 of Point3 c */
    public final Normal3 cn;

    /**
     * Construct a Triangle
     * @param material Material for color
     * @param a Point3 for plane construction - must not be null
     * @param an Normal3 for plane construction - must not be null
     * @param b Point3 for plane construction - must not be null
     * @param bn Normal3 for plane construction - must not be null
     * @param c Point3 for plane construction - must not be null
     * @param cn Normal3 for plane construction - must not be null
     *
     */
    public Triangle(final Material material, final Point3 a, final Normal3 an, final Point3 b,
                    final Normal3 bn, final Point3 c, final Normal3 cn) {
        super(material);
        if (a == null) throw new IllegalArgumentException("must not be null");
        if (an == null) throw new IllegalArgumentException("must not be null");
        if (b == null) throw new IllegalArgumentException("must not be null");
        if (bn == null) throw new IllegalArgumentException("must not be null");
        if (c == null) throw new IllegalArgumentException("must not be null");
        if (cn == null) throw new IllegalArgumentException("must not be null");
        this.a = a;
        this.b = b;
        this.c = c;
        this.an = an;
        this.bn = bn;
        this.cn = cn;
    }
    public Triangle (final Material material, final Point3 a, final Point3 b, final Point3 c){
        this(material,  a, b.sub(a).x(c.sub(a)).asNormal(),
                        b, b.sub(a).x(c.sub(a)).asNormal(),
                        c, b.sub(a).x(c.sub(a)).asNormal()
        );
    }



    @Override
    public Hit hit(final Ray r) {
        Mat3x3 matrixBase = new Mat3x3(a.x - b.x, a.x - c.x, r.d.x,
                                       a.y - b.y, a.y - c.y, r.d.y,
                                       a.z - b.z, a.z - c.z, r.d.z);
        Vector3 colChanger = new Vector3(a.x - r.o.x, a.y - r.o.y, a.z - r.o.z);
        Mat3x3 matrixBeta = matrixBase.changeCol1(colChanger);
        double beta = matrixBeta.determinant / matrixBase.determinant;
        if (!(beta + Epsilon.PRECISION >= 0 && beta - Epsilon.PRECISION <= 1)) return null;
        Mat3x3 matrixGamma = matrixBase.changeCol2(colChanger);
        double gamma = matrixGamma.determinant / matrixBase.determinant;
        if (!(gamma - Epsilon.PRECISION >= 0 && gamma - Epsilon.PRECISION <= 1)) return null;
        if (beta + gamma - Epsilon.PRECISION >= 1) return null;
        Mat3x3 matrixT = matrixBase.changeCol3(colChanger);
        double t = matrixT.determinant / matrixBase.determinant;
        if(!(t>=0)) return null;
        final Normal3 temp = an.mul(1-beta-gamma).add(bn.mul(beta)).add(cn.mul(gamma));
        return new Hit(t, r, this, temp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Triangle triangle = (Triangle) o;

        if (a != null ? !a.equals(triangle.a) : triangle.a != null) return false;
        if (b != null ? !b.equals(triangle.b) : triangle.b != null) return false;
        return !(c != null ? !c.equals(triangle.c) : triangle.c != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (a != null ? a.hashCode() : 0);
        result = 31 * result + (b != null ? b.hashCode() : 0);
        result = 31 * result + (c != null ? c.hashCode() : 0);
        return result;
    }
}
