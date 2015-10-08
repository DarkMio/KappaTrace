package Vorbereitung;


public class Vector3 {
    protected final double x;
    protected final double y;
    protected final double z;
    protected final double magnitude;

    public Vector3(double x, double y, double z, double magnitude){
        this.x=x;
        this.y=y;
        this.z=z;
        this.magnitude=calcMagnitude(x,y,z);
    }

    private double calcMagnitude(double x, double y, double z){
        return Math.sqrt(x*x+y*y+z*z);
    }

    public Vector3 add(Vector3 vector3){
        double nx=x+vector3.x;
        double ny=y+vector3.y;
        double nz=z+vector3.z;
        return new Vector3(nx,ny,nz,calcMagnitude(nx, ny, nz));
    }
    /**TODO check**/
    public Vector3 add(Normal3 normal3){
        double nx=x+normal3.x;
        double ny=y+normal3.y;
        double nz=z+normal3.z;
        return new Vector3(nx,ny,nz,calcMagnitude(nx, ny, nz));
    }
    /**TODO check**/
    public Vector3 sub(Normal3 normal3){
        double nx=normal3.x-x;
        double ny=normal3.y-y;
        double nz=normal3.z-z;
        return new Vector3(nx,ny,nz,calcMagnitude(nx, ny, nz));
    }

    public Vector3 mul(double s){
        return new Vector3(x*s,y*s,z*s,calcMagnitude(x*s, y*s, z*s));
    }
    /**TODO**/
    public double dot(Vector3 vector3){
        return 0;
    }
    /**TODO**/
    public double dot(Normal3 normal3){
        return 0;
    }

    public Vector3 normalized(){
        return mul(1/magnitude);
    }
    /**TODO**/
    public Normal3 asNormal(){
        return new Normal3();
    }
    /**TODO**/
    public Vector3 reflection(Normal3 normal3){
        return new Vector3();
    }

    public Vector3 x(Vector3 vector3){
        double nx=y*vector3.z-z*vector3.y;
        double ny=z*vector3.x-x*vector3.z;
        double nz=x*vector3.y-y*vector3.x;
        return new Vector3(nx,ny,nz,calcMagnitude(nx,ny,nz));
    }

    public String toString(){
        return String.format("("+"%8.2f"+")"+"%n"+"("+"%8.2f"+")"+"%n"+"("+"%8.2f"+")"+"%n",x,y,z);
    }
}
