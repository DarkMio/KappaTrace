package MultiThreading;

/**
 * MultiRaytracer represents class of the raytracer
 */

import Raytracing.Camera.Camera;
import Raytracing.World;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MultiRaytracer {

    /**
     * int determining the width of a raytracer
     */
    final public int width;
    /**
     * int determining the height of a raytracer
     */
    final public int height;
    /**
     * JFrame for raytracer
     */
    public JFrame jf;
    /**
     * BufferedImage for raytracer
     */
    final public BufferedImage img;
    /**
     * Camera for raytracer
     */
    public Camera cam;
    /**
     * World for raytracer
     */
    public final World world;
    /**
     * ImageIcon for raytracer
     */
    private ImageIcon frame;
    private JLabel jl;
    public final ArrayList<MyRunnable> runnables;

    private int rowCount = 0;
    private long startTime;
    private int xThread;
    private int yThread;
    private int threadsCompleted = 0;
    private JProgressBar progress = new JProgressBar(0, 1000);
    private JLabel status = new JLabel("Estimating rendering time.");

    /**
     * renders a single image
     */
    private void render() {
        for (Runnable t : runnables) {
            Thread thread = new Thread(t);
            thread.start();
            //thread.join();
        }
    }

    /**
     * constructor for a MultiRaytracer with int width, int height, World world and Camera camera
     */
    public MultiRaytracer(final int width, final int height, final World world, final Camera camera, final int threads) {
        if (threads % 2 == 1) {
            xThread = (threads - 1) / 2;
            yThread = 2;
        } else {
            xThread = threads / 2;
            yThread = 2;
        }
        this.width = width;
        this.height = height;
        jf = new JFrame();
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setTitle("MultiRaytracing | Java");
        jf.setResizable(false);
        jf.setLayout(new BorderLayout());
        jf.setIconImage(new ImageIcon("./src/Resources/Kappa.png").getImage());
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        frame = new ImageIcon(img);
        jl = new JLabel();
        jl.setIcon(frame);
        jf.add(jl, BorderLayout.NORTH);
        progress.setStringPainted(true);
        jf.add(progress, BorderLayout.CENTER);
        jf.add(status, BorderLayout.SOUTH);
        this.world = world;
        cam = camera;
        runnables = new ArrayList<>();
        for (int x = 0; x < xThread; x++) {
            for (int y = 0; y < yThread; y++) {
                runnables.add(new MyRunnable(this, width * x / xThread, width * (x + 1) / xThread, height * y / yThread,
                        height * (y + 1) / yThread));
            }
        }
        jf.setVisible(true);
        jf.pack();
        startTime = System.currentTimeMillis();
        render();
    }

    public void update() {
        jl.setIcon(new ImageIcon(img));
        rowCount++;
        int rowSet = width * yThread;
        long offset = System.currentTimeMillis() - startTime;
        long perLine = offset / rowCount;
        long inter = perLine * rowSet;
        long i = inter - startTime;
        i = inter / 1000;
        progress.setValue(rowCount*1000/rowSet);
        status.setText("Rendering... | Remaining time: " + i / (60 * 60) + "h " + i / (60) % 60 + "m " + i % 60 + "s");
    }

    public void complete() {
        threadsCompleted++;
        if (threadsCompleted == runnables.size()) {
            int endTime = (int) (System.currentTimeMillis() - startTime);
            endTime = endTime / 1000;
            status.setText("Rendering finished | Time taken: " + endTime / (60 * 60) + "h " + endTime / (60) % 60 + "m " + endTime % 60 + "s");
        }
    }

}
