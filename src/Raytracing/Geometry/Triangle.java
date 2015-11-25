package Raytracing.Geometry;

/**
 * class for Triangle objects
 */
import MathFunc.Mat3x3;
import MathFunc.Normal3;
import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Color;
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

    /** constructor used to create triangle objects with a Color color, a Point3 a, a Point3 b and a Point3 c */
    public Triangle(Material material, Point3 a, Point3 b, Point3 c) {
        super(material);
        this.a = a;
        this.b = b;
        this.c = c;
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
        return new Hit(matrixT.determinant / matrixBase.determinant, r, this, new Normal3(beta, gamma, matrixT.determinant / matrixBase.determinant));
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
