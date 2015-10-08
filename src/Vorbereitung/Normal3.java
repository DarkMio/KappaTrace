package Vorbereitung;


public class Normal3 {
    protected final double x;
    protected final double y;
    protected final double z;

    public Normal3(double x, double y, double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }


    public Normal3 mul(double n){
        return new Normal3(x*n,y*n,z*n);
    }

    public Normal3 add(Normal3 n){
        return new Normal3(x+n.x,y+n.y,z+n.z);
    }
}
