package Raytracing.Geometry;

import MathFunc.Mat3x3;
import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Color;
import Raytracing.Hit;
import Raytracing.Ray;

public class Triangle extends Geometry {

    public final Point3 a, b, c;

    public Triangle(Color color, Point3 a, Point3 b, Point3 c) {
        super(color);
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
        return new Hit(matrixT.determinant / matrixBase.determinant, r, this);
    }
}
