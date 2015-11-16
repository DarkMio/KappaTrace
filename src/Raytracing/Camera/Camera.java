package Raytracing.Camera;

/**
 * Camera represents abstract class for camera objects
 */
import Raytracing.Ray;
import MathFunc.Point3;
import MathFunc.Vector3;

public abstract class Camera {

    /** Point3 e representing the origin*/
    final public Point3 e;
    /** Vector3 g representing the gaze*/
    final public Vector3 g;
    /** Vector3 t representing the up vector*/
    final public Vector3 t;
    /** Vector3 u representing the the local vector u*/
    final public Vector3 u;
    /** Vector3 v representing the the local vector v*/
    final public Vector3 v;
    /** Vector3 w representing the a local vector w*/
    final public Vector3 w;

    /** abstract constructor for camera objects */
    public Camera(Point3 e, Vector3 g, Vector3 t) {
        this.e = e;
        this.g = g;
        this.t = t;
        w = g.normalized().mul(-1);
        u = t.x(w).normalized();
        v = w.x(u);
    }

    /** abstract constructor for a ray in a specified pixel */
    public abstract Ray rayFor(int w, int h, int x, int y);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Camera camera = (Camera) o;

        if (!e.equals(camera.e)) return false;
        if (!g.equals(camera.g)) return false;
        if (!t.equals(camera.t)) return false;
        if (!u.equals(camera.u)) return false;
        if (!v.equals(camera.v)) return false;
        return w.equals(camera.w);

    }

    @Override
    public int hashCode() {
        int result = e.hashCode();
        result = 31 * result + g.hashCode();
        result = 31 * result + t.hashCode();
        result = 31 * result + u.hashCode();
        result = 31 * result + v.hashCode();
        result = 31 * result + w.hashCode();
        return result;
    }
}
