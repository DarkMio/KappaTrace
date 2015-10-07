package Vorbereitung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

/**
 * Created by Mio on 07/10/2015.
 */
public class ImagePanel extends JPanel implements ComponentListener {

    protected BufferedImage img;
    protected static final Color black = new Color(0, 0, 0);
    protected static final Color red = new Color(125, 0, 0);

    public ImagePanel() {
        img = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final int height = img.getHeight();
        final int width = img.getWidth();
        final int max = height > width ? width : height; // select the smaller one of both

        for(int i = 0; i < max; i++) {
            img.setRGB(i, i, red.getRGB());
        }
        g.drawImage(img, 0, 0, null);
    }


    @Override
    public void componentResized(ComponentEvent e) {
        final int width = e.getComponent().getWidth();
        final int height = e.getComponent().getHeight();
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
