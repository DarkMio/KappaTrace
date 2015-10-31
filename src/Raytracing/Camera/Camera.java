package Raytracing.Camera;

import Raytracing.Ray;
import MathFunc.Point3;
import MathFunc.Vector3;

public abstract class Camera {

    final protected Point3 e;
    final protected Vector3 g, t, u, v, w;

    public Camera(Point3 e, Vector3 g, Vector3 t) {
        this.e = e;
        this.g = g;
        this.t = t;
        w = g.normalized().mul(-1);
        u = t.x(w).normalized();
        v = w.x(u);
    }

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
