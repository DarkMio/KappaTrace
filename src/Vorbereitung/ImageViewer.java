package Vorbereitung;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ImageViewer {

    public static void main(String[] args) {
        while (true) {
            final JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    final String absPath = fc.getSelectedFile().getAbsolutePath();
                    final String s = Files.probeContentType(Paths.get(absPath));
                    if (s == null) {
                        System.out.println("Filetype not recorgnized.");
                    } else if (s.contains("image")) {
                        displayImage(new File(absPath));
                    } else {
                        JOptionPane.showMessageDialog(null, "Selected file must be a picture.", "Dialog",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException e) {
                    e.printStackTrace();  // Mein Nicker, how can you even except when you just got chosen.
                }
            } else {
                break;
            }
        }
    }

    private static void displayImage(File image) throws IOException {
        BufferedImage img = ImageIO.read(image);
        ImageIcon icon = new ImageIcon(img); // Dude, Swing is so whacky.
        JFrame jf = new JFrame();
        jf.setLayout(new FlowLayout());
        jf.setSize(img.getWidth(), img.getHeight());
        JLabel jl = new JLabel();
        jf.setBackground(new Color(0, 0, 0));
        jl.setIcon(icon);
        jf.add(jl);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
