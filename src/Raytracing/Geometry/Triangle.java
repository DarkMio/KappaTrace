package Raytracing.Geometry;

/**
 * class for Triangle objects
 */
import MathFunc.Mat3x3;
import MathFunc.Normal3;
import MathFunc.Point3;
import MathFunc.Vector3;
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

    /** constructor used to create triangle objects with a Material material, a Point3 a, a Point3 b, a Point3 c, a Normal3 an, a Normal3 bn and a Normal3 cn */
    public Triangle(Material material, Point3 a, Normal3 an, Point3 b, Normal3 bn, Point3 c, Normal3 cn) {
        super(material);
        this.a = a;
        this.b = b;
        this.c = c;
        this.an = an;
        this.bn = bn;
        this.cn = cn;
    }

    @Override
    public Hit hit(Ray r) {
        Mat3x3 matrixBase = new Mat3x3(a.x - b.x, a.x - c.x, r.d.x,
                                       a.y - b.y, a.y - c.y, r.d.y,
                                       a.z - b.z, a.z - b.z, r.d.z);
        Vector3 colChanger = new Vector3(a.x - r.o.x, b.y - r.o.y, a.z - r.o.z);
        Mat3x3 matrixBeta = matrixBase.changeCol1(colChanger);
        double beta = matrixBeta.determinant / matrixBase.determinant;
        if (!(beta + PRECISION >= 0 && beta - PRECISION <= 1)) return null;
        Mat3x3 matrixGamma = matrixBase.changeCol2(colChanger);
        double gamma = matrixGamma.determinant / matrixBase.determinant;
        if (!(gamma - PRECISION >= 0 && gamma - PRECISION <= 1)) return null;
        if (beta + gamma - PRECISION >= 1) return null;
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
