package Raytracing.Geometry;


import MathFunc.Normal3;
import MathFunc.Point3;
import Raytracing.*;
import Raytracing.Material.Material;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Fabby on 03.01.2016.
 */
public class ShapeFromFile extends Geometry {

    public final ArrayList<Geometry> objects;
    private static final Normal3 up = new Normal3(0, 1, 0);

    public ShapeFromFile(String path, Material material) {
        super(material);
        objects = new ArrayList<>();
        try {
            System.out.println("load");
            parseFile(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Hit hit(Ray r) {
        double t = Double.MAX_VALUE;
        Hit h = null;
        for (Geometry g : objects) {
            final Hit hit = g.hit(r);
            if (hit != null && hit.t < t && hit.t > Epsilon.PRECISION) {
                t = hit.t;
                h = hit;
            }
        }
        return h;
    }

    public void parseFile(File f) throws IOException {

        ArrayList<Point3> points = new ArrayList<>();
        ArrayList<Normal3> normals = new ArrayList<>();
        ArrayList<int[]> faces3 = new ArrayList<>();
        ArrayList<Integer> faces4 = new ArrayList<>();
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String str = s.nextLine();
            String[] astra = str.split(" +");

            if (str.startsWith("v")) {
                if (astra.length == 4) {
                    points.add(new Point3(Double.valueOf(astra[1]), Double.valueOf(astra[2]), Double.valueOf(astra[3])));
                }
            }
            if (str.startsWith("vn")){

            }
            if (str.startsWith("f")) {
                if (astra.length == 4) {
                    int[] intArray = new int[3];
                    intArray[0] = Integer.valueOf(astra[1]) - 1;
                    intArray[1] = Integer.valueOf(astra[2]) - 1;
                    intArray[2] = Integer.valueOf(astra[3]) - 1;
                    faces3.add(intArray);
                  /*  int ab = Integer.valueOf(astra[1]) - 1;
                    int b = Integer.valueOf(astra[2]) - 1;
                    int c = Integer.valueOf(astra[3]) - 1;
                    Point3 p1 = points.get(ab);
                    Point3 p2 = points.get(b);
                    Point3 p3 = points.get(c);
                    objects.add(new Triangle(material, p1, up, p2, up, p3, up));
                    System.out.println("new triangle");*/
                }
                if (astra.length == 5) {
                    /*int ab = Integer.valueOf(astra[1]) - 1;
                    int b = Integer.valueOf(astra[2]) - 1;
                    int c = Integer.valueOf(astra[3]) - 1;
                    int d = Integer.valueOf(astra[4]) - 1;
                    Point3 p1 = points.get(ab);
                    Point3 p2 = points.get(b);
                    Point3 p3 = points.get(c);
                    Point3 p4 = points.get(d);
                    objects.add(new Triangle(material, p1, up, p2, up, p3, up));
                    objects.add(new Triangle(material, p1, up, p3, up, p4, up));
                    System.out.println("new poly");*/
                }
            }
        }
        for (int i = 0; i < faces3.size(); i++) {
            int[] a = faces3.get(i);
            Point3 p1 = points.get(a[0]);
            Point3 p2 = points.get(a[1]);
            Point3 p3 = points.get(a[2]);
            objects.add(new Triangle(material, p1, up, p2, up, p3, up));
        }
    }
}