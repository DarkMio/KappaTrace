import MathFunc.Normal3;
import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.Camera.FishEyeCamera;
import Raytracing.Camera.PerspectiveCamera;
import Raytracing.Color;
import Raytracing.Constants.Materials;
import Raytracing.Geometry.*;
import Raytracing.Light.Light;
import Raytracing.Light.PointLight;
import Raytracing.Material.LambertMaterial;
import Raytracing.Transform;
import Raytracing.UI.Raytracer;
import Raytracing.World;

import java.util.ArrayList;
import java.util.Arrays;

public class main {

    public static void main(String[] args) {
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
        ArrayList<Geometry> scene_lambert = new ArrayList<>(Arrays.asList(
                new Plane(Materials.BLACK_LAMBERT, new Point3(0, 0, 0), new Normal3(0, 1, 0)),
                new Sphere(Materials.RED_LAMBERT, new Point3(-3,1,0), 1),
                new AxisAlignedBox(Materials.GREEN_LAMBERT, new Point3(0, 0, 0), new Point3(1, 1, 1)),
                new Sphere(Materials.BLUE_LAMBERT, new Point3(3,1,0), 1),
                new Sphere(Materials.YELLOW_LAMBERT, new Point3(3, 4, 0), 1),
                new Sphere(Materials.TEAL_LAMBERT, new Point3(3, 1, 0), 1),
                new Sphere(Materials.PURPLE_LAMBERT, new Point3(-3, 4, 0), 1),
                new Sphere(Materials.ORANGE_LAMBERT, new Point3(0, 4, 0), 1)
        ));
        ArrayList<Geometry> scene_phong = new ArrayList<>(Arrays.asList(
                new Plane(Materials.BLACK_PHONG, new Point3(0, 0, 0), new Normal3(0, 1, 0)),
                new Sphere(Materials.RED_PHONG, new Point3(-3,1,0), 1),
                new AxisAlignedBox(Materials.GREEN_PHONG, new Point3(0, 0, 0), new Point3(1, 1, 1)),
                new Sphere(Materials.BLUE_PHONG, new Point3(3,1,0), 1),
                new Sphere(Materials.YELLOW_PHONG, new Point3(3, 4, 0), 1),
                new Sphere(Materials.TEAL_PHONG, new Point3(3, 1, 0), 1),
                new Sphere(Materials.PURPLE_PHONG, new Point3(-3, 4, 0), 1),
                new Sphere(Materials.ORANGE_PHONG, new Point3(0, 4, 0), 1)
        ));
        ArrayList<Light> lights = new ArrayList<>();
        lights.add(new PointLight(new Point3(8.0,8.0,8.0), new Color(1.0,1.0,1.0),true));
        PerspectiveCamera ppc = new PerspectiveCamera(new Point3(8,8,8), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI/4);
        FishEyeCamera fec = new FishEyeCamera(new Point3(0,0,6), new Vector3(-0.3, -0.1, -0.3), new Vector3(0.10, 1, 0), 2, 100);

        new Raytracer(320, 240, new World(background, scene_reflective, ambientLight, lights), fec);
//      new Raytracer(640, 480, new World(background, scene_lambert, ambientLight, lights), ppc);
//      new Raytracer(640, 480, new World(background, scene_phong, ambientLight, lights), ppc);

        ArrayList<Geometry> scene2 = new ArrayList<>(Arrays.asList(
                new Plane(new LambertMaterial(new Color(0.8, 0.8, 0.8)), new Point3(0, 0, 0), new Normal3(0, 1, 0)),
                new AxisAlignedBox(Materials.YELLOW_REFLECTIVE,new Point3(-0.5,0,-0.5),new Point3(0.5,1,0.5))
        ));
        ArrayList<Light> lights2 = new ArrayList<>();
        lights2.add(new PointLight(new Point3(8.0,8.0,0.0), new Color(1.0,1.0,1.0),true));
        PerspectiveCamera ppc2 = new PerspectiveCamera(new Point3(8,8,8), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI/4);
//      new Raytracer(640, 480, new World(background, scene2, ambientLight, lights2), ppc2);

        Transform x = new Transform().scale(new Vector3(3, 1, 1.5));
        ArrayList<Geometry> scene3 = new ArrayList<>(Arrays.asList(new Node(Materials.RED_REFLECTIVE, x,scene_reflective)));
//      new Raytracer(640, 480, new World(background, scene3, ambientLight, lights2), ppc2);
    }
}
