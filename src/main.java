import MathFunc.Normal3;
import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.*;
import Raytracing.Camera.OrthographicCamera;
import Raytracing.Camera.PerspectiveCamera;
import Raytracing.Color;
import Raytracing.Geometry.*;
import Raytracing.UI.Raytracer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class main {

    public static void main(String[] args) {
        PerspectiveCamera pCam = new PerspectiveCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), Math.PI/4);
        OrthographicCamera oCam = new OrthographicCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), 3);
        PerspectiveCamera boxCam = new PerspectiveCamera(new Point3(2, 2, 2), new Vector3(-2, -2, -2), new Vector3(0, 1, 0), Math.PI/4);
        Color background = new Color(0.1, 0.1, 0.1);
        Color red = new Color(220/255.0, 34/255.0, 25/255.0);
        Color blue = new Color(20/255.0, 120/255.0, 204/255.0);
        Color green = new Color(19/255.0, 178/255.0, 76/255.0);
        Color pink = new Color(220/255.0, 34/255.0, 204/255.0);
        ArrayList<Geometry> plane = new ArrayList<>(Arrays.asList(new Plane(green, new Point3(0, -1, 0), new Normal3(0, 1, 0))));
        ArrayList<Geometry> sphere = new ArrayList<>(Arrays.asList(new Sphere(red, new Point3(-1, 0, -3), 0.5)));
        ArrayList<Geometry> box = new ArrayList<>(Arrays.asList(new AxisAlignedBox(blue, new Point3(-0.5, 0, -0.5), new Point3(0.5, 1, 0.5))));
        ArrayList<Geometry> triangle = new ArrayList<>(Arrays.asList(new Triangle(pink, new Point3(-0.5, 0.5, -3), new Point3(0.5, 0.5, -3), new Point3(0.5, -0.5, -3))));
/*
        new Raytracer(640, 480, new World(background, plane), pCam);
        new Raytracer(640, 480, new World(background, sphere), pCam);
        new Raytracer(640, 480, new World(background, box), boxCam);
        new Raytracer(640, 480, new World(background, triangle), pCam);
*/
        sphere.add(new Sphere(red, new Point3(1, 0, -6), 0.5));
/*
        new Raytracer(640, 480, new World(background, sphere), pCam);
*/        new Raytracer(640, 480, new World(background, sphere), oCam);
/*
        box.addAll(plane);
        box.addAll(sphere);
        box.addAll(triangle);
        new Raytracer(640, 480, new World(background, box), boxCam);
*/
    }
}
