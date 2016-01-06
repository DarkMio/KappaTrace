package Raytracing.Camera;
// @TODO: PLEASE, FishEyeCamera and OrthographicCamera are utterly broken right now.
/**
 * Camera represents abstract class for camera objects
 */
import Raytracing.Ray;
import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Sampling.SamplingPattern;

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

    final public SamplingPattern pattern;

    /**
     * Abstract Camaera constructor
     * @param e Point3 for plane construction - must not be null
     * @param g Vector3 for plane construction - must not be null
     * @param t Vector3 for plane construction - must not be null
     *
     */
    public Camera(final Point3 e, final Vector3 g, final Vector3 t, final SamplingPattern pattern) {
        if (e == null) throw new IllegalArgumentException("must not be null");
        if (g == null) throw new IllegalArgumentException("must not be null");
        if (t == null) throw new IllegalArgumentException("must not be null");
        this.e = e;
        this.g = g;
        this.t = t;
        this.pattern = pattern;
        w = g.normalized().mul(-1);
        u = t.x(w).normalized();
        v = w.x(u);
    }

    /** abstract constructor for a ray in a specified pixel */
    public abstract Ray[] rayFor(final int w, final int h, final int x, final int y);

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
