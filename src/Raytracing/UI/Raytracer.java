package Raytracing.UI;

import MathFunc.Normal3;
import MathFunc.Point3;
import MathFunc.Vector3;
import Raytracing.*;
import Raytracing.Camera.Camera;
import Raytracing.Camera.OrthographicCamera;
import Raytracing.Camera.PerspectiveCamera;
import Raytracing.Geometry.Geometry;
import Raytracing.Geometry.Plane;
import Raytracing.Geometry.Sphere;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Mio on 08/11/2015.
 */
public class Raytracer {

    final private int width, height;
    final private JFrame jf;
    final private BufferedImage img;
    final private ArrayList<Geometry> scene;
    private Camera cam;
    final private World world;
    private ImageIcon frame;

    public Raytracer(final int width, final int height) {
        this.width = width;
        this.height = height;
        jf = new JFrame();
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setTitle("Raytracing | Java");
        jf.setResizable(false);
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        frame = new ImageIcon(img);
        JLabel jl = new JLabel();
        jl.setIcon(frame);
        jf.add(jl);
        scene = new ArrayList<Geometry>();
        scene.add(new Plane(new Color(0, 1, 0), new Point3(0, -1, 0), new Normal3(0, 1, 0)));
        scene.add(new Sphere(new Color(1, 0, 0), new Point3(-1, 0, -3), 0.5));
        scene.add(new Sphere(new Color(1, 0, 0), new Point3(1, 0, -6), 0.5));

        world = new World(new Color(0, 0, 0), scene);
        // cam = new OrthographicCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), 3);
        cam = new PerspectiveCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), Math.PI/4);
        render();
        jf.pack();
        jf.setVisible(true);
    }

    private void render() {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                final Hit h = world.hit(cam.rayFor(width, height, x, y));
                if(h == null) img.setRGB(x, height-y-1, world.backgroundColor.toIntRGB());
                else img.setRGB(x, height-y-1, h.geo.color.toIntRGB());
            }
        }
        reload();
    }

    private void reload() {
        frame.setImage(img);
    }
}
