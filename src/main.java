import MathFunc.Normal3;
import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.*;
import Raytracing.Camera.OrthographicCamera;
import Raytracing.Camera.PerspectiveCamera;
import Raytracing.Color;
import Raytracing.Geometry.*;
import Raytracing.Light.Light;
import Raytracing.Light.PointLight;
import Raytracing.Material.LambertMaterial;
import Raytracing.Material.Material;
import Raytracing.Material.PhongMaterial;
import Raytracing.Material.SingleColorMaterial;
import Raytracing.UI.Raytracer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class main {

    public static void main(String[] args) {
        Color background = new Color(0.1, 0.1, 0.1);
        Color ambientLight = new Color(0.25, 0.25, 0.25);
        Material red = new PhongMaterial(new Color(10/255.0, 34/255.0, 25/255.0), new Color(1, 1, 1), 10);
        Material blue = new PhongMaterial(new Color(20/255.0, 120/255.0, 204/255.0), new Color(1, 1, 1), 3);
        Material green = new PhongMaterial(new Color(19/255.0, 178/255.0, 76/255.0), new Color(1, 1, 1), 10);
        Material pink = new PhongMaterial(new Color(220/255.0, 34/255.0, 204/255.0), new Color(0, 0, 0), 1234675);
/*  Übung 1:
        PerspectiveCamera pCam = new PerspectiveCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), Math.PI/4);
        OrthographicCamera oCam = new OrthographicCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), 3);
        PerspectiveCamera boxCam = new PerspectiveCamera(new Point3(2, 2, 2), new Vector3(-2, -2, -2), new Vector3(0, 1, 0), Math.PI/4);
        ArrayList<Geometry> plane = new ArrayList<>(Arrays.asList(new Plane(green, new Point3(0, -1, 0), new Normal3(0, 1, 0))));
        ArrayList<Geometry> sphere = new ArrayList<>(Arrays.asList(new Sphere(red, new Point3(-1, 0, -3), 0.5)));
        ArrayList<Geometry> box = new ArrayList<>(Arrays.asList(new AxisAlignedBox(blue, new Point3(-0.5, 0, -0.5), new Point3(0.5, 1, 0.5))));
        ArrayList<Geometry> triangle = new ArrayList<>(Arrays.asList(new Triangle(pink, new Point3(-0.5, 0.5, -3), new Point3(0.5, 0.5, -3), new Point3(0.5, -0.5, -3))));

        new Raytracer(640, 480, new World(background, plane, ambientLight), pCam);
        new Raytracer(640, 480, new World(background, sphere, ambientLight), pCam);
        new Raytracer(640, 480, new World(background, box, ambientLight), boxCam);
        new Raytracer(640, 480, new World(background, triangle, ambientLight), pCam);

        sphere.add(new Sphere(red, new Point3(1, 0, -6), 0.5));

        new Raytracer(640, 480, new World(background, sphere, ambientLight), pCam);
        new Raytracer(640, 480, new World(background, sphere, ambientLight), oCam);

        box.addAll(plane);
        box.addAll(sphere);
        box.addAll(triangle);
        new Raytracer(640, 480, new World(background, box, ambientLight), boxCam);
*/
        ArrayList<Geometry> scene = new ArrayList<>(Arrays.asList(
                new Plane(blue, new Point3(0, 0, 0), new Normal3(0, 1, 0)),
                new Sphere(green, new Point3(1, 1, 1), 0.5),
                new AxisAlignedBox(pink, new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5)),
                new Triangle(red, new Point3(0, 0, -1), new Point3(1, 0, -1), new Point3(1, 1, -1))
        ));
        ArrayList<Light> lights = new ArrayList<>(Arrays.asList(new PointLight(new Point3(2, 2, 2), new Color(1, 244/255.0, 99/255.0))));
        PerspectiveCamera ppc = new PerspectiveCamera(new Point3(2, 2, 2), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI/4);
        new Raytracer(640, 480, new World(background, scene, ambientLight, lights), ppc);

    }
}
