package Raytracing.UI;

import Raytracing.*;
import Raytracing.Camera.Camera;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class Raytracer {

    final private int width, height;
    final private JFrame jf;
    final private BufferedImage img;
    private Camera cam;
    final private World world;
    private ImageIcon frame;

    public Raytracer(final int width, final int height, World world, Camera camera) {
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
        this.world = world;
        cam = camera;
        render();
        jf.pack();
        jf.setVisible(true);
    }

    private void render() {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                final Hit h = world.hit(cam.rayFor(width, height, x, y));
                if(h == null) img.setRGB(x, height-y-1, world.backgroundColor.toIntRGB());
                else {img.setRGB(x, height-y-1, h.geo.color.toIntRGB());}
            }
        }
        reload();
    }

    private void reload() {
        frame.setImage(img);
    }
}
