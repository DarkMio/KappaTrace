package Vorbereitung;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ImageFrame extends JFrame implements ActionListener {


    public static void main(String[] args) {
        ImageFrame imgf = new ImageFrame();
        imgf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        imgf.setVisible(true);
    }

    /**
     * Reference to ImagePanel
     */
    protected final ImagePanel ip;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("save")) {
            saveImagePanel();
        }
    }

    /**
     * Creates a new JFileChooser to save an image as png.
     * The data is queried from the BufferedImage inside ImagePanel.
     */
    protected void saveImagePanel() {
        final JFileChooser fc = new JFileChooser();
        final int returnVal = fc.showDialog(this, "save me, baby.");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File f = new File(fc.getSelectedFile().getAbsolutePath());
                BufferedImage bi = ip.getImg();
                ImageIO.write(bi, "png", f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Standard constructor, builds already all UI components together.
     */
    public ImageFrame() {
        ImagePanel ip = new ImagePanel();
        this.ip = ip;
        setLayout(new BorderLayout());
        add(ip, BorderLayout.CENTER);
        setJMenuBar(factorySaveButton("save", this));
        addComponentListener(ip);
        setSize(1024, 768);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Builds together a JMenuBar with Datei > {text} to implement a save-functionality.
     *
     * @param text {text} for save button
     * @param al   ActionListener (in case we ever need a different one)
     * @return JMenuBar with Datei > {text}
     */
    protected static JMenuBar factorySaveButton(String text, ActionListener al) {
        final JMenuBar jmb = new JMenuBar();
        final JMenu jm = new JMenu("Datei");
        final JMenuItem jmi = new JMenuItem(text);
        jmi.setActionCommand("save");
        jmi.addActionListener(al);
        jm.add(jmi);
        jmb.add(jm);
        return jmb;
    }
}
