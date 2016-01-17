package Raytracing.Constants;

import Raytracing.Material.LambertMaterial;
import Raytracing.Material.PhongMaterial;
import Raytracing.Material.ReflectiveMaterial;
import Raytracing.Material.Texturing.SingleColorTexture;

public class Materials {

    public static final ReflectiveMaterial BLACK_REFLECTIVE = new ReflectiveMaterial(Colors.BLACK, Colors.SUN, 64, Colors.GRAY);
    public static final ReflectiveMaterial WHITE_REFLECTIVE = new ReflectiveMaterial(Colors.WHITE, Colors.SUN, 64, Colors.GRAY);
    public static final ReflectiveMaterial RED_REFLECTIVE = new ReflectiveMaterial(Colors.RED, Colors.SUN, 64, Colors.GRAY);
    public static final ReflectiveMaterial GREEN_REFLECTIVE = new ReflectiveMaterial(Colors.GREEN, Colors.SUN, 64, Colors.GRAY);
    public static final ReflectiveMaterial BLUE_REFLECTIVE = new ReflectiveMaterial(Colors.BLUE, Colors.SUN, 64, Colors.GRAY);
    public static final ReflectiveMaterial YELLOW_REFLECTIVE = new ReflectiveMaterial(Colors.YELLOW, Colors.SUN, 64, Colors.GRAY);
    public static final ReflectiveMaterial TEAL_REFLECTIVE = new ReflectiveMaterial(Colors.TEAL, Colors.SUN, 64, Colors.GRAY);
    public static final ReflectiveMaterial PURPLE_REFLECTIVE = new ReflectiveMaterial(Colors.PURPLE, Colors.SUN, 64, Colors.GRAY);
    public static final ReflectiveMaterial ORANGE_REFLECTIVE = new ReflectiveMaterial(Colors.ORANGE, Colors.SUN, 64, Colors.GRAY);

    public static final LambertMaterial BLACK_LAMBERT = new LambertMaterial(new SingleColorTexture(Colors.BLACK));
    public static final LambertMaterial WHITE_LAMBERT = new LambertMaterial(new SingleColorTexture(Colors.WHITE));
    public static final LambertMaterial RED_LAMBERT = new LambertMaterial(new SingleColorTexture(Colors.RED));
    public static final LambertMaterial GREEN_LAMBERT = new LambertMaterial(new SingleColorTexture(Colors.GREEN));
    public static final LambertMaterial BLUE_LAMBERT = new LambertMaterial(new SingleColorTexture(Colors.BLUE));
    public static final LambertMaterial YELLOW_LAMBERT = new LambertMaterial(new SingleColorTexture(Colors.YELLOW));
    public static final LambertMaterial TEAL_LAMBERT = new LambertMaterial(new SingleColorTexture(Colors.TEAL));
    public static final LambertMaterial PURPLE_LAMBERT = new LambertMaterial(new SingleColorTexture(Colors.PURPLE));
    public static final LambertMaterial ORANGE_LAMBERT = new LambertMaterial(new SingleColorTexture(Colors.ORANGE));

    public static final PhongMaterial BLACK_PHONG = new PhongMaterial(Colors.BLACK, Colors.SUN, 64);
    public static final PhongMaterial WHITE_PHONG = new PhongMaterial(Colors.WHITE, Colors.SUN, 64);
    public static final PhongMaterial RED_PHONG = new PhongMaterial(Colors.RED, Colors.SUN, 64);
    public static final PhongMaterial GREEN_PHONG = new PhongMaterial(Colors.GREEN, Colors.SUN, 64);
    public static final PhongMaterial BLUE_PHONG = new PhongMaterial(Colors.BLUE, Colors.SUN, 64);
    public static final PhongMaterial YELLOW_PHONG = new PhongMaterial(Colors.YELLOW, Colors.SUN, 64);
    public static final PhongMaterial TEAL_PHONG = new PhongMaterial(Colors.TEAL, Colors.SUN, 64);
    public static final PhongMaterial PURPLE_PHONG = new PhongMaterial(Colors.PURPLE, Colors.SUN, 64);
    public static final PhongMaterial ORANGE_PHONG = new PhongMaterial(Colors.ORANGE, Colors.SUN, 64);
}
