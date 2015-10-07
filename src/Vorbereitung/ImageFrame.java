package Vorbereitung;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Mio on 07/10/2015.
 */
public class ImageFrame extends JFrame implements ActionListener {

    protected ImagePanel ip;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("save")) {
            saveImagePanel();
        }
    }

    protected void saveImagePanel() {
        final JFileChooser fc = new JFileChooser();
        final int returnVal = fc.showDialog(this, "save me, baby.");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File f = new File(fc.getSelectedFile().getAbsolutePath());
                BufferedImage bi = ip.getImg();
                ImageIO.write(bi, "png", f);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public ImageFrame() {
        ImagePanel ip = new ImagePanel();
        this.ip = ip;
        setLayout(new BorderLayout());
        add(ip, BorderLayout.CENTER);
        add(new ImageMenuBar(this), BorderLayout.NORTH);
        addComponentListener(ip);
        setSize(640, 480);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
