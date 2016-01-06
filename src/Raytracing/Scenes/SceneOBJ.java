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
import javafx.scene.Scene;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SceneOBJ {
    public static void SceneOBJFactory() {
        firstOBJSceneFactory();
        secondOBJSceneFactory();
        thirdOBJSceneFactory();
        fourthOBJSceneFactory();
    }

    private static void firstOBJSceneFactory() {
        SceneGenerator("./src/OBJFiles/cube-v.obj");
    }

    private static void secondOBJSceneFactory() {
        SceneGenerator("./src/OBJFiles/cube-v-vn.obj");
    }

    private static void thirdOBJSceneFactory() {
        SceneGenerator("./src/OBJFiles/cube-v-vt.obj");
    }

    private static void fourthOBJSceneFactory() {
        SceneGenerator("./src/OBJFiles/teddy.obj");
    }

    private static void SceneGenerator(String file) {
        Color background = new Color(0.0, 0.0, 0.0);
        Color ambientLight = new Color(1, 1, 1);
        Normal3 up = new Normal3(0, 1, 0);
        ArrayList<Geometry> scene = new ArrayList<>(Arrays.asList(
                new ShapeFromFile(file, Materials.ORANGE_REFLECTIVE)
        ));
        PerspectiveCamera ppc = new PerspectiveCamera(new Point3(4, 4, 4), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI/4);
        ArrayList<Light> lights2 = new ArrayList<>();
        lights2.add(new PointLight(new Point3(4,4,4), Colors.WHITE, true));
        new MultiRaytracer(640, 480, new World(background, scene, ambientLight, lights2), ppc, 8);
    }
}
