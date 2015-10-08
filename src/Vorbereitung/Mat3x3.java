package Vorbereitung;


public class Mat3x3 {
    protected final double m11;
    protected final double m12;
    protected final double m13;
    protected final double m21;
    protected final double m22;
    protected final double m23;
    protected final double m31;
    protected final double m32;
    protected final double m33;
    protected final double determinant;

    public Mat3x3(double m11, double m12, double m13, double m21, double m22, double m23, double m31, double m32, double m33){
        this.m11=m11;
        this.m12=m12;
        this.m13=m13;
        this.m21=m21;
        this.m22=m22;
        this.m23=m23;
        this.m31=m31;
        this.m32=m32;
        this.m33=m33;
        this.determinant=m11*m22*m33+m12*m23*m31+m13*m21*m32-m13*m22*m31-m12*m21*m33-m11*m23*m32;
    }

    public Mat3x3 mul(Mat3x3 mat3x3){
        return new Mat3x3(m11*mat3x3.m11+m12*mat3x3.m21+m13*mat3x3.m31,
                m11*mat3x3.m12+ m12*mat3x3.m22+ m13*mat3x3.m32,
                m11*mat3x3.m13+ m12*mat3x3.m23+ m13*mat3x3.m33,
                m21*mat3x3.m11+ m22*mat3x3.m21+ m23*mat3x3.m31,
                m21*mat3x3.m12+ m22*mat3x3.m22+ m23*mat3x3.m32,
                m21*mat3x3.m13+ m22*mat3x3.m23+ m23*mat3x3.m33,
                m31*mat3x3.m11+ m32*mat3x3.m21+ m33*mat3x3.m31,
                m31*mat3x3.m12+ m32*mat3x3.m22+ m33*mat3x3.m32,
                m31*mat3x3.m13+ m32*mat3x3.m23+ m33*mat3x3.m33);
    }

    public String toString(){
        return String.format("("+"%10.2f"+"%10.2f"+"%10.2f"+")"+"%n"+"("+"%10.2f"+"%10.2f"+"%10.2f"+")"+"%n"+"("+"%10.2f"+"%10.2f"+"%10.2f"+")"+"%n",
                m11,m12,m13,m21,m22,m23,m31,m32,m33);
    }

}
