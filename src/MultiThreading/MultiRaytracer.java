package MultiThreading;

/**
 * MultiRaytracer represents class of the raytracer
 */

import Raytracing.Camera.Camera;
import Raytracing.World;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MultiRaytracer {

    /** int determining the width of a raytracer */
    final public int width;
    /** int determining the height of a raytracer */
    final public int height;
    /** JFrame for raytracer */
    final private JFrame jf;
    /** BufferedImage for raytracer */
    final public BufferedImage img;
    /** Camera for raytracer */
    public Camera cam;
    /** World for raytracer */
    public final World world;
    /** ImageIcon for raytracer */
    private ImageIcon frame;
    public final ArrayList<MyRunnable> runnables;


    /** constructor for a MultiRaytracer with int width, int height, World world and Camera camera */
    public MultiRaytracer(final int width, final int height, final World world, final Camera camera, final int threads) {
        int xThread = 0;
        int yThread = 0;
        if(threads%2==1){
            xThread = (threads-1)/2;
            yThread = 2;
        }else {
            xThread = threads/2;
            yThread = 2;
        }
        this.width = width;
        this.height = height;
        jf = new JFrame();
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setTitle("MultiRaytracing | Java");
        jf.setResizable(false);
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        frame = new ImageIcon(img);
        JLabel jl = new JLabel();
        jl.setIcon(frame);
        jf.add(jl);
        this.world = world;
        cam = camera;
        runnables=new ArrayList<>();
        for(int x = 0; x < xThread; x++){
            for(int y = 0; y < yThread; y++) {
                runnables.add(new MyRunnable(this, width * x / xThread, width * (x + 1) / xThread, height * y / yThread,
                        height * (y + 1) / yThread));
            }
        }
        render();
        jf.pack();
        jf.setVisible(true);
    }

    /** renders a single image */
    private void render() {
        try{
            for(Runnable t : runnables){
                Thread thread = new Thread(t);
                thread.start();
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
