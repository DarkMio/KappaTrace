package Raytracing.Scenes;

import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Camera.PerspectiveCamera;
import Raytracing.Color;
import Raytracing.Constants.Materials;
import Raytracing.Geometry.BoundingBox;
import Raytracing.Geometry.Geometry;
import Raytracing.Geometry.Node;
import Raytracing.Geometry.Triangle;
import Raytracing.Light.Light;
import Raytracing.Light.PointLight;
import Raytracing.MultiThreading.MultiRaytracer;
import Raytracing.Sampling.EvenlyDistributedPattern;
import Raytracing.Transform;
import Raytracing.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SceneBounding {

    public static void SceneFactory() {
        Color background = new Color(0.0, 0.0, 0.0);
        Color ambientLight = new Color(0.25, 0.25, 0.25);

        Point3 one = new Point3(-0.5, -0.5, 0.5);
        Point3 two = new Point3(0.5, -0.5, 0.5);
        Point3 three = new Point3(0.5, 0.5, 0.5);
        Point3 four = new Point3(-0.5, 0.5, 0.5);
        Point3 five = new Point3(-0.5, -0.5, -0.5);
        Point3 six = new Point3(0.5, -0.5, -0.5);
        Point3 seven = new Point3(0.5, 0.5, -0.5);
        Point3 eight = new Point3(-0.5, 0.5, -0.5);
        ArrayList<Geometry> scene_reflective = new ArrayList<>(Arrays.asList(
                new Triangle(Materials.RED_LAMBERT, one, two, three),
                new Triangle(Materials.ORANGE_REFLECTIVE, three, four, one),
                new Triangle(Materials.RED_LAMBERT, two, six, seven),
                new Triangle(Materials.ORANGE_REFLECTIVE, seven, three, two),
                new Triangle(Materials.RED_LAMBERT, six, five, eight),
                new Triangle(Materials.ORANGE_REFLECTIVE, eight, seven, six),
                new Triangle(Materials.RED_LAMBERT, five, one, four),
                new Triangle(Materials.ORANGE_REFLECTIVE, four, eight, five),
                new Triangle(Materials.RED_LAMBERT, four, three, seven),
                new Triangle(Materials.ORANGE_REFLECTIVE, seven, eight, four),
                new Triangle(Materials.RED_LAMBERT, five, six, two),
                new Triangle(Materials.ORANGE_REFLECTIVE, two, one, five)

        ));
        ArrayList<Geometry> boundingScene = new ArrayList<>(Collections.singletonList(
                new BoundingBox(scene_reflective)
        ));
        ArrayList<Light> lights2 = new ArrayList<>();
        lights2.add(new PointLight(new Point3(8.0, 8.0, 0.0), new Color(1.0, 1.0, 1.0), true));

        Transform x = new Transform().scale(new Vector3(3, 1, 1.5));
        ArrayList<Geometry> scene3 = new ArrayList<>(Collections.singletonList(new Node(Materials.RED_REFLECTIVE, x, scene_reflective)));
        PerspectiveCamera ppc2 = new PerspectiveCamera(new Point3(2, 2, 2), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4, new EvenlyDistributedPattern(3));
        new MultiRaytracer(640, 480, new World(background, boundingScene, ambientLight, lights2), ppc2, 8);
        new MultiRaytracer(640, 480, new World(background, scene_reflective, ambientLight, lights2), ppc2, 8);
    }
}
