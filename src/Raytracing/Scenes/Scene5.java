package Raytracing.Scenes;

import MathFunc.Normal3;
import MathFunc.Point3;
import MathFunc.Vector3;
import MultiThreading.MultiRaytracer;
import Raytracing.Camera.PerspectiveCamera;
import Raytracing.Color;
import Raytracing.Constants.Materials;
import Raytracing.Geometry.*;
import Raytracing.Light.Light;
import Raytracing.Light.PointLight;
import Raytracing.Transform;
import Raytracing.UI.Raytracer;
import Raytracing.World;
import java.util.ArrayList;
import java.util.Arrays;

public class Scene5 {

    public static void Scene5Factory() {
        Color background = new Color(0.0, 0.0, 0.0);
        Color ambientLight = new Color(0.25, 0.25, 0.25);

        ArrayList<Geometry> scene_reflective = new ArrayList<>(Arrays.asList(
                new Plane(Materials.BLACK_REFLECTIVE, new Point3(0, 0, 0), new Normal3(0, 1, 0)),
                new Sphere(Materials.RED_REFLECTIVE, new Point3(-3,1,0), 1),
                new AxisAlignedBox(Materials.GREEN_REFLECTIVE, new Point3(0, 0, 0), new Point3(1, 1, 1)),
                new Sphere(Materials.BLUE_REFLECTIVE, new Point3(3,1,0), 1),
                new Sphere(Materials.YELLOW_REFLECTIVE, new Point3(3, 4, 0), 1),
                new Sphere(Materials.TEAL_REFLECTIVE, new Point3(3, 1, 0), 1),
                new Sphere(Materials.PURPLE_REFLECTIVE, new Point3(-3, 4, 0), 1),
                new Sphere(Materials.ORANGE_REFLECTIVE, new Point3(0, 4, 0), 1)
        ));
        ArrayList<Light> lights2 = new ArrayList<>();
        lights2.add(new PointLight(new Point3(8.0,8.0,0.0), new Color(1.0,1.0,1.0),true));

        Transform x = new Transform().scale(new Vector3(3, 1, 1.5));
        ArrayList<Geometry> scene3 = new ArrayList<>(Arrays.asList(new Node(Materials.RED_REFLECTIVE, x,scene_reflective)));
        PerspectiveCamera ppc2 = new PerspectiveCamera(new Point3(8,8,8), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI/4);
        new MultiRaytracer(1280, 920, new World(background, scene3, ambientLight, lights2), ppc2, 8);
    }
}
