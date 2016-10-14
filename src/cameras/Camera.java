package cameras;

import utilities.Point3D;
import utilities.Vector3D;
import world.World;

abstract public class Camera {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Fields
\*--------------------------------------------------------------------------------------------------------------------*/

    Point3D  eye;
    Point3D  lookat;
    Vector3D up;
    Vector3D u, v, w;
    double   exposureTime = 1.0;

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public Camera(Point3D eye) {
        this.eye = eye;
        this.lookat = new Point3D(0, 0, 0);
        this.up = new Vector3D(0, 1, 0);
        this.uvw();
    }

    public Camera(Point3D eye, Point3D lookat) {
        this.eye = eye;
        this.lookat = lookat;
        this.up = new Vector3D(0, 1, 0);
        this.uvw();
    }

    public Camera(Point3D eye, Point3D lookat, Vector3D up) {
        this.eye = eye;
        this.lookat = lookat;
        this.up = up;
        this.uvw();
    }

    // OpenGL style <code>gluLookat()</code>
    public Camera(double eyeX, double eyeY, double eyeZ,
                  double lookatX, double lookatY, double lookatZ,
                  double upX, double upY, double upZ)
    {
        this.eye = new Point3D(eyeX, eyeY, eyeZ);
        this.lookat = new Point3D(lookatX, lookatY, lookatZ);
        this.up = new Vector3D(upX, upY, upZ);
        this.uvw();
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Abstract methods
\*--------------------------------------------------------------------------------------------------------------------*/

    abstract public void renderScene(World w);

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Helper methods
\*--------------------------------------------------------------------------------------------------------------------*/

    /**
     * Computes the uvw-coordinate, aka, the camera coordinate.
     */
    void uvw() {
        w = eye.sub(lookat).normalVector();
        u = up.cross(w).normalVector();
        v = w.cross(u).normalVector();
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Override methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Camera camera = (Camera) o;

        if (Double.compare(camera.exposureTime, exposureTime) != 0) return false;
        if (eye != null ? !eye.equals(camera.eye) : camera.eye != null) return false;
        if (lookat != null ? !lookat.equals(camera.lookat) : camera.lookat != null) return false;
        if (up != null ? !up.equals(camera.up) : camera.up != null) return false;
        if (u != null ? !u.equals(camera.u) : camera.u != null) return false;
        if (v != null ? !v.equals(camera.v) : camera.v != null) return false;
        return w != null ? w.equals(camera.w) : camera.w == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = eye != null ? eye.hashCode() : 0;
        result = 31 * result + (lookat != null ? lookat.hashCode() : 0);
        result = 31 * result + (up != null ? up.hashCode() : 0);
        result = 31 * result + (u != null ? u.hashCode() : 0);
        result = 31 * result + (v != null ? v.hashCode() : 0);
        result = 31 * result + (w != null ? w.hashCode() : 0);
        temp = Double.doubleToLongBits(exposureTime);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "eye=" + eye +
                ", lookat=" + lookat +
                ", up=" + up +
                ", u=" + u +
                ", v=" + v +
                ", w=" + w +
                ", exposureTime=" + exposureTime +
                '}';
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Getters and setters
\*--------------------------------------------------------------------------------------------------------------------*/

    public Point3D getEye() {
        return eye;
    }

    public Camera setEye(Point3D eye) {
        this.eye = eye;
        return this;
    }

    public Camera setEye(double x, double y, double z) {
        this.eye = new Point3D(x, y, z);
        return this;
    }

    public Point3D getLookat() {
        return lookat;
    }

    public Camera setLookat(Point3D lookat) {
        this.lookat = lookat;
        return this;
    }

    public Camera setLookat(double x, double y, double z) {
        this.lookat = new Point3D(x, y, z);
        return this;
    }

    public Vector3D getUp() {
        return up;
    }

    public Camera setUp(Vector3D up) {
        this.up = up;
        return this;
    }

    public Camera setUp(double x, double y, double z) {
        this.up = new Vector3D(x, y, z);
        return this;
    }

    public Vector3D getU() {
        return u;
    }

    public Vector3D getV() {
        return v;
    }

    public Vector3D getW() {
        return w;
    }

}
