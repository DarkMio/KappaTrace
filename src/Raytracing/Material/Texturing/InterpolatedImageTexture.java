package Raytracing.Material.Texturing;

import Raytracing.Color;

import java.awt.image.BufferedImage;

/**
 * Created by Mio on 17/01/2016.
 */
public class InterpolatedImageTexture extends ImageTexture {


    public InterpolatedImageTexture(String file) {
        super(file);
    }

    @Override
    public Color colorFor(double u, double v) {
        final double uPos = u * (image.getWidth() - 1);
        final double vPos = v * (image.getHeight() - 1);
        final int x = (int) uPos;
        final int y = (int) vPos;
        final double widthFloor = uPos - Math.floor(uPos);
        final double heightFloor =  vPos - Math.floor(vPos);
        final double top, bottom, left, right;
        // calculate the bias
        bottom = heightFloor;
        top = 1 - heightFloor;
        right = widthFloor;
        left = 1 - widthFloor;
        Color topLeft = new Color(image.getRGB(x, y));
        Color topRight = new Color(image.getRGB(x + 1, y));
        Color botLeft = new Color(image.getRGB(x, y + 1));
        Color botRight = new Color(image.getRGB(x + 1, y + 1));
        Color a = topLeft.mul(top).mul(left);
        Color b = topRight.mul(top).mul(right);
        Color c = botLeft.mul(bottom).mul(left);
        Color d = botRight.mul(bottom).mul(right);
        return a.add(b).add(c).add(d);
    }

    private static Color[] colorFromRGB(int ... rgb) {
        Color[] colorArray = new Color[rgb.length];
        for(int i = 0; i < rgb.length; i++) {
            int color = rgb[i];
            int r = (color >> 16) & BYTEMASK;
            int g = (color >> 8) & BYTEMASK;
            int b = color & BYTEMASK;
            colorArray[i] = new Color(r/255.0, g/255.0, b/255.0);
        }
        return colorArray;
    }
}
