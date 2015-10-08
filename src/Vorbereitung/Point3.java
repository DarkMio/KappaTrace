package Vorbereitung;


public class Point3 {
    protected final double x;
    protected final double y;
    protected final double z;

    public Point3(double x, double y, double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public Vector3 sub(Point3 point3){
        double vx=point3.x-x;
        double vy=point3.y-y;
        double vz=point3.z-z;
        double magnitude=Math.sqrt(vx*vx+vy*vy+vz*vz);
        return new Vector3(vx, vy, vz, magnitude);
    }

    public Point3 sub(Vector3 vector3){
        return new Point3(x-vector3.x,y-vector3.y,z-vector3.z);
    }

    public Point3 add(Vector3 vector3){
        return new Point3(x+vector3.x,y+vector3.y,z+vector3.z);
    }

    public String toString(){
        return String.format("("+"%8.2f"+")"+"("+"%8.2f"+")"+"("+"%8.2f"+")"+"%n",x,y,z);
    }
}
