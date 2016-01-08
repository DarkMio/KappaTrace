package Raytracing.Scenes;

import MathFunc.Normal3;
import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.MultiThreading.MultiRaytracer;
import Raytracing.Camera.PerspectiveCamera;
import Raytracing.Color;
import Raytracing.Constants.Materials;
import Raytracing.Geometry.AxisAlignedBox;
import Raytracing.Geometry.Geometry;
import Raytracing.Geometry.Plane;
import Raytracing.Geometry.Sphere;
import Raytracing.Light.Light;
import Raytracing.Light.PointLight;
import Raytracing.Sampling.EvenlyDistributedPattern;
import Raytracing.World;

import java.util.ArrayList;
import java.util.Arrays;

public class Scene4 {

    public static void SceneFactory() {
        firstSceneFactory();
        // secondSceneFactory();
    }

    private static void firstSceneFactory() {
        Color background = new Color(0.0, 0.0, 0.0);
        Color ambientLight = new Color(0.25, 0.25, 0.25);
        ArrayList<Geometry> scene_reflective = new ArrayList<>(Arrays.asList(
                new Plane(Materials.WHITE_REFLECTIVE, new Point3(0, 0, 0), new Normal3(0, 1, 0)),
                new Sphere(Materials.RED_REFLECTIVE, new Point3(-3,1,0), 1),
                new Sphere(Materials.GREEN_REFLECTIVE, new Point3(0,1,0), 1),
                new Sphere(Materials.BLUE_REFLECTIVE, new Point3(3,1,0), 1)
        ));
        ArrayList<Light> lights = new ArrayList<>();
        lights.add(new PointLight(new Point3(8.0,8.0,8.0), new Color(1.0,1.0,1.0),true));

        PerspectiveCamera ppc = new PerspectiveCamera(new Point3(3,3,3), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI/4, new EvenlyDistributedPattern(3));
        new MultiRaytracer(1920, 1000, new World(background, scene_reflective, ambientLight, lights), ppc, 8);
    }

    private static void secondSceneFactory() {
        Color background = new Color(0.0, 0.0, 0.0);
        Color ambientLight = new Color(0.25, 0.25, 0.25);
        ArrayList<Geometry> scene = new ArrayList<>(Arrays.asList(
                new Plane(Materials.WHITE_LAMBERT, new Point3(0, 0, 0), new Normal3(0, 1, 0)),
                new AxisAlignedBox(Materials.RED_LAMBERT,new Point3(-0.5,0,-0.5),new Point3(0.5,1,0.5))
        ));
        PerspectiveCamera ppc = new PerspectiveCamera(new Point3(8,8,8), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI/4, new EvenlyDistributedPattern(3));
        ArrayList<Light> lights = new ArrayList<>();
        lights.add(new PointLight(new Point3(8.0,8.0,0.0), new Color(1.0,1.0,1.0),true));
        new MultiRaytracer(1280, 960, new World(background, scene, ambientLight, lights), ppc, 8);
    }
}
