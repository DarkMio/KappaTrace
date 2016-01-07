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
import Raytracing.Light.Light;
import Raytracing.Light.PointLight;
import Raytracing.Sampling.EvenlyDistributedPattern;
import Raytracing.World;

import java.util.ArrayList;
import java.util.Arrays;

public class SceneOBJ {
    public static void SceneOBJFactory() {
        // firstOBJSceneFactory();
        //secondOBJSceneFactory();
        //thirdOBJSceneFactory();
        fourthOBJSceneFactory();
        // fifthOBJSceneFactory();
        // sixthOBJSceneFactory();
    }

    private static void firstOBJSceneFactory() {
        SceneGenerator("./src/OBJFiles/cube-v.obj", new Point3(4, 4, 4));
    }

    private static void secondOBJSceneFactory() {
        SceneGenerator("./src/OBJFiles/cube-v-vn.obj", new Point3(4, 4, 4));
    }

    private static void thirdOBJSceneFactory() {
        SceneGenerator("./src/OBJFiles/cube-v-vt.obj", new Point3(4, 4, 4));
    }

    private static void fourthOBJSceneFactory() {
        SceneGenerator("./src/OBJFiles/teddy.obj", new Point3(2, 2, 2));
    }

    private static void fifthOBJSceneFactory() {
        SceneGenerator("./src/OBJFiles/cube-v-vt-vn.obj", new Point3(4, 4, 4));
    }
    private static void sixthOBJSceneFactory() {
        SceneGenerator("./src/OBJFiles/bunny.obj", new Point3(40, 40, 40));
    }

    private static void SceneGenerator(String file, Point3 camera) {
        Color background = new Color(0, 0, 0);
        Color ambientLight = new Color(0.5, 0.5, 0.5);
        Normal3 up = new Normal3(0, 1, 0);
        ShapeFromFile sff = new ShapeFromFile(file, Materials.WHITE_PHONG);
        ArrayList<Geometry> scene = new ArrayList<>(Arrays.asList(
                sff,
                new Plane(Materials.BLACK_REFLECTIVE, new Point3(0, -1, 0), new Normal3(0, 1, 0))
        ));
        ArrayList<Geometry> boundingScene = new ArrayList<>(Arrays.asList(
                new BoundingBox(scene),
                new Plane(Materials.BLACK_REFLECTIVE, new Point3(0, -1, 0), new Normal3(0, 1, 0))
        ));
        for(Geometry g: boundingScene) {
            System.out.println("Something");
        }
        PerspectiveCamera ppc = new PerspectiveCamera(camera, new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI/4, new EvenlyDistributedPattern(1));
        ArrayList<Light> lights2 = new ArrayList<>();
        lights2.add(new PointLight(new Point3(0.1,4,0), Colors.WHITE, true));
        // new MultiRaytracer(640, 480, new World(background, boundingScene, ambientLight, lights2), ppc, 7);
        new MultiRaytracer(640, 480, new World(background, scene, ambientLight, lights2), ppc, 7);

    }
}
