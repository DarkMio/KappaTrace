package Vorbereitung;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mio on 07/10/2015.
 */
public class ImageSaver {

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        ImagePanel ip = new ImagePanel();
        jf.setLayout(new BorderLayout());
        jf.add(ip, BorderLayout.CENTER);
        jf.addComponentListener(ip);
        jf.setSize(640, 480);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
