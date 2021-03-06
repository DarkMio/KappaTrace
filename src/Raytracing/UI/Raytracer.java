package Raytracing.UI;

/**
 * Raytracer represents class of the raytracer
 */

import Raytracing.Camera.Camera;
import Raytracing.*;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Raytracer {

    /**
     * int determining the width of a raytracer
     */
    final private int width;
    /**
     * int determining the height of a raytracer
     */
    final private int height;
    /**
     * JFrame for raytracer
     */
    final private JFrame jf;
    /**
     * BufferedImage for raytracer
     */
    final private BufferedImage img;
    /**
     * Camera for raytracer
     */
    final private Camera cam;
    /**
     * World for raytracer
     */
    final private World world;
    /**
     * ImageIcon for raytracer
     */
    final private ImageIcon frame;

    /**
     * constructor for a Raytracer with int width, int height, World world and Camera camera
     */
    public Raytracer(final int width, final int height, final World world, final Camera camera) {
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

    /**
     * renders a single image
     */
    private void render() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                final Ray[] rays = cam.rayFor(width, height, x, y);
                Color finalColor = new Color(0, 0, 0);
                for (Ray ray : rays) {
                    final Hit h = world.hit(ray);
                    if (h == null) finalColor = finalColor.add(world.backgroundColor);
                    else finalColor = finalColor.add(h.geo.material.colorFor(h, world,
                            new Tracer(8, world)));
                }
                finalColor = finalColor.mul(1 / (double) rays.length);
                img.setRGB(x, height - y - 1, finalColor.toIntRGB());
            }
        }
        reload();
    }

    /**
     * reloads the image frame
     */
    private void reload() {
        frame.setImage(img);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Raytracer raytracer = (Raytracer) o;

        if (width != raytracer.width) return false;
        if (height != raytracer.height) return false;
        if (jf != null ? !jf.equals(raytracer.jf) : raytracer.jf != null) return false;
        if (img != null ? !img.equals(raytracer.img) : raytracer.img != null) return false;
        if (cam != null ? !cam.equals(raytracer.cam) : raytracer.cam != null) return false;
        if (world != null ? !world.equals(raytracer.world) : raytracer.world != null) return false;
        return !(frame != null ? !frame.equals(raytracer.frame) : raytracer.frame != null);

    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        result = 31 * result + (jf != null ? jf.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (cam != null ? cam.hashCode() : 0);
        result = 31 * result + (world != null ? world.hashCode() : 0);
        result = 31 * result + (frame != null ? frame.hashCode() : 0);
        return result;
    }
}
