package Vorbereitung;

/**
 * Created by Christoph on 08.10.2015.
 */
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

}
