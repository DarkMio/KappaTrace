package Raytracing.Scenes;

import MathFunc.Normal3;
import MathFunc.Point3;
import MathFunc.Vector3;
import MultiThreading.MultiRaytracer;
import Raytracing.Camera.PerspectiveCamera;
import Raytracing.Color;
import Raytracing.Constants.Colors;
import Raytracing.Constants.Materials;
import Raytracing.Geometry.*;
import Raytracing.Light.DirectionalLight;
import Raytracing.Light.Light;
import Raytracing.Light.PointLight;
import Raytracing.Light.SpotLight;
import Raytracing.UI.Raytracer;
import Raytracing.World;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Scene3 {
    public static void Scene3Factory() {
        firstSceneFactory();
        secondSceneFactory();
        thirdSceneFactory();
        fourthSceneFactory();
        fifthSceneFactory();
        sixthSceneFactory();
    }

    private static void firstSceneFactory() {
        Color background = new Color(0.0, 0.0, 0.0);
        Color ambientLight = new Color(1, 1, 1);
        Normal3 up = new Normal3(0, 1, 0);
        ArrayList<Geometry> scene = new ArrayList<>(Arrays.asList(
                new Plane(Materials.RED_LAMBERT, new Point3(0, 0, 0), new Normal3(0, 1, 0)),
                new Sphere(Materials.GREEN_LAMBERT, new Point3(1, 1, 1), 0.5),
                new AxisAlignedBox(Materials.BLUE_LAMBERT, new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5)),
                new Triangle(Materials.YELLOW_LAMBERT, new Point3(0, 0, -1), up, new Point3(1, 0, -1), up, new Point3(1, 1, -1), up)
        ));
        PerspectiveCamera ppc = new PerspectiveCamera(new Point3(4, 4, 4), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI/4);
        ArrayList<Light> lights2 = new ArrayList<>();
        new Raytracer(640, 480, new World(background, scene, ambientLight, lights2), ppc);
    }

    private static void secondSceneFactory() {
        Color background = new Color(0.0, 0.0, 0.0);
        Color ambientLight = new Color(0, 0, 0);
        Normal3 up = new Normal3(0, 1, 0);
        ArrayList<Geometry> scene = new ArrayList<>(Arrays.asList(
                new Plane(Materials.RED_LAMBERT, new Point3(0, 0, 0), new Normal3(0, 1, 0)),
                new Sphere(Materials.GREEN_LAMBERT, new Point3(1, 1, 1), 0.5),
                new AxisAlignedBox(Materials.BLUE_LAMBERT, new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5)),
                new Triangle(Materials.YELLOW_LAMBERT, new Point3(0, 0, -1), up, new Point3(1, 0, -1), up, new Point3(1, 1, -1), up)
        ));
        PerspectiveCamera ppc = new PerspectiveCamera(new Point3(4, 4, 4), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI/4);
        ArrayList<Light> lights2 = new ArrayList<>();
        lights2.add(new PointLight(new Point3(4, 4, 4), Colors.WHITE, false));
        new Raytracer(640, 480, new World(background, scene, ambientLight, lights2), ppc);
    }

    private static void thirdSceneFactory() {
        Color background = new Color(0.0, 0.0, 0.0);
        Color ambientLight = new Color(0, 0, 0);
        Normal3 up = new Normal3(0, 1, 0);
        ArrayList<Geometry> scene = new ArrayList<>(Arrays.asList(
                new Plane(Materials.RED_PHONG, new Point3(0, 0, 0), new Normal3(0, 1, 0)),
                new Sphere(Materials.GREEN_PHONG, new Point3(1, 1, 1), 0.5),
                new AxisAlignedBox(Materials.BLUE_PHONG, new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5)),
                new Triangle(Materials.YELLOW_PHONG, new Point3(0, 0, -1), up, new Point3(1, 0, -1), up, new Point3(1, 1, -1), up)
        ));
        PerspectiveCamera ppc = new PerspectiveCamera(new Point3(4, 4, 4), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI/4);
        ArrayList<Light> lights2 = new ArrayList<>();
        lights2.add(new PointLight(new Point3(4, 4, 4), Colors.WHITE, false));
        new Raytracer(640, 480, new World(background, scene, ambientLight, lights2), ppc);
    }

    private static void fourthSceneFactory() {
        Color background = new Color(0.0, 0.0, 0.0);
        Color ambientLight = new Color(0, 0, 0);
        Normal3 up = new Normal3(0, 1, 0);
        ArrayList<Geometry> scene = new ArrayList<>(Arrays.asList(
                new Plane(Materials.RED_PHONG, new Point3(0, 0, 0), new Normal3(0, 1, 0)),
                new Sphere(Materials.GREEN_PHONG, new Point3(1, 1, 1), 0.5),
                new AxisAlignedBox(Materials.BLUE_PHONG, new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5)),
                new Triangle(Materials.YELLOW_PHONG, new Point3(0, 0, -1), up, new Point3(1, 0, -1), up, new Point3(1, 1, -1), up)
        ));
        PerspectiveCamera ppc = new PerspectiveCamera(new Point3(4, 4, 4), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI/4);
        ArrayList<Light> lights2 = new ArrayList<>();
        lights2.add(new DirectionalLight(new Vector3(-1, -1, -1), Colors.WHITE, false));
        new Raytracer(640, 480, new World(background, scene, ambientLight, lights2), ppc);
    }

    private static void fifthSceneFactory() {
        Color background = new Color(0.0, 0.0, 0.0);
        Color ambientLight = new Color(0, 0, 0);
        Normal3 up = new Normal3(0, 1, 0);
        ArrayList<Geometry> scene = new ArrayList<>(Arrays.asList(
                new Plane(Materials.RED_PHONG, new Point3(0, 0, 0), new Normal3(0, 1, 0)),
                new Sphere(Materials.GREEN_PHONG, new Point3(1, 1, 1), 0.5),
                new AxisAlignedBox(Materials.BLUE_PHONG, new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5)),
                new Triangle(Materials.YELLOW_PHONG, new Point3(0, 0, -1), up, new Point3(1, 0, -1), up, new Point3(1, 1, -1), up)
        ));
        PerspectiveCamera ppc = new PerspectiveCamera(new Point3(4, 4, 4), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI/4);
        ArrayList<Light> lights2 = new ArrayList<>();
        lights2.add(new SpotLight(new Point3(4, 4, 4), new Vector3(-1, -1, -1), Math.PI/15, Colors.WHITE, false));
        new Raytracer(640, 480, new World(background, scene, ambientLight, lights2), ppc);
    }

    private static void sixthSceneFactory() {
        Color background = new Color(0.0, 0.0, 0.0);
        Color ambientLight = new Color(0.25, 0.25, 0.25);
        Normal3 up = new Normal3(0, 1, 0);
        ArrayList<Geometry> scene = new ArrayList<>(Arrays.asList(
                new Plane(Materials.RED_PHONG, new Point3(0, 0, 0), new Normal3(0, 1, 0)),
                new Sphere(Materials.GREEN_PHONG, new Point3(1, 1, 1), 0.5),
                new AxisAlignedBox(Materials.BLUE_PHONG, new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5)),
                new Triangle(Materials.YELLOW_PHONG, new Point3(0, 0, -1), up, new Point3(1, 0, -1), up, new Point3(1, 1, -1), up)
        ));
        PerspectiveCamera ppc = new PerspectiveCamera(new Point3(2, 2, 2), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI/4);
        ArrayList<Light> lights2 = new ArrayList<>();
        lights2.add(new SpotLight(new Point3(4, 4, 4), new Vector3(-1, -1, -1), Math.PI/15, Colors.WHITE, false));
        new Raytracer(640, 480, new World(background, scene, ambientLight, lights2), ppc);
    }
}
