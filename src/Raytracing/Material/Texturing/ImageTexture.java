package Raytracing.Material.Texturing;

import Raytracing.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageTexture extends Texture{

    /** Totes useful once only.         0xAARRGGBB */
    protected static final int BYTEMASK = 0x000000FF;
    public final BufferedImage image;

    public ImageTexture(final String file) {
        image = getImage(file);
    }

    /**
     * HASHTAG: INIT WITH TRY-CATCH? DON'T WORRY, I AM 'ZE JAVA.
     */
    private BufferedImage getImage(final String file) {
        try {
            return ImageIO.read(new File(file));
        } catch (IOException e) {
            System.err.println("Cannot open " + file + "!");
            return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
    }

    @Override
    public Color colorFor(double u, double v) {
        final int uPos = (int) (u * (image.getWidth() - 1));
        final int vPos = (int) (v * (image.getHeight() - 1));
        final int rgb = image.getRGB(uPos, vPos);
        int r = (rgb >> 16) & BYTEMASK;
        int g = (rgb >> 8) & BYTEMASK;
        int b = rgb & BYTEMASK;

        return new Color(r/255.0, g/255.0, b/255.0);
    }
}
