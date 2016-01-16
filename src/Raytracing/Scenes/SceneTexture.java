package Raytracing.Scenes;

import MathFunc.Normal3;
import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Camera.Camera;
import Raytracing.Camera.PerspectiveCamera;
import Raytracing.Color;
import Raytracing.Constants.Colors;
import Raytracing.Constants.Materials;
import Raytracing.Geometry.*;
import Raytracing.Light.Light;
import Raytracing.Light.PointLight;
import Raytracing.Material.LambertMaterial;
import Raytracing.MultiThreading.MultiRaytracer;
import Raytracing.Sampling.EvenlyDistributedPattern;
import Raytracing.Transform;
import Raytracing.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Mio on 16/01/2016.
 */
public class SceneTexture {

    public static void SceneFactory() {
        Color background = new Color(0, 0, 0);
        Color ambientLight = new Color(0.5, 0.5, 0.5);
        Normal3 up = new Normal3(0, 1, 0);
        ArrayList<Geometry> scene = new ArrayList<>(Arrays.asList(
                new Plane(Materials.WHITE_LAMBERT, new Point3(0, 0, 0), new Normal3(0, 1, 10)),
                new Sphere(Materials.WHITE_LAMBERT, new Point3(1, 1, 1), 0.5)
                // new AxisAlignedBox(Materials.WHITE_LAMBERT, new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5)),
                // new Triangle(Materials.WHITE_LAMBERT, new Point3(0, 0, -1), up, new Point3(1, 0, -1), up, new Point3(1, 1, -1), up)
        ));
        Camera ppc = new PerspectiveCamera(new Point3(4, 4, 4), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI/4, new EvenlyDistributedPattern(5));
        ArrayList<Light> lights2 = new ArrayList<>();
        lights2.add(new PointLight(new Point3(10, 40, 0), Colors.WHITE, true));
        new MultiRaytracer(640, 480, new World(background, scene, ambientLight, lights2), ppc, 7);
        // new MultiRaytracer(640, 480, new World(background, scene, ambientLight, lights2), ppc, 7);

    }
}
