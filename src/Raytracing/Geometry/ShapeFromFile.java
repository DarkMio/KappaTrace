package Raytracing.Geometry;


import MathFunc.Normal3;
import MathFunc.Point3;
import Raytracing.Epsilon;
import Raytracing.Hit;
import Raytracing.Material.Material;
import Raytracing.Ray;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShapeFromFile extends Geometry {

    public final ArrayList<Geometry> objects;
    private static final Normal3 n1 = new Normal3(0, 1, 0);
    private static final Normal3 n2 = new Normal3(0, 1, 0);
    private static final Normal3 n3 = new Normal3(0, 1, 0);


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
        ArrayList<double[]> textureData = new ArrayList<>();
        ArrayList<Normal3> normals = new ArrayList<>();
        ArrayList<int[]> faces3 = new ArrayList<>();
        ArrayList<Integer> faces4 = new ArrayList<>();
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String str = s.nextLine();
            String[] astra = str.split(" +");

            if (str.startsWith("v ")) {
                if (astra.length == 4) {
                    points.add(new Point3(Double.valueOf(astra[1]), Double.valueOf(astra[2]), Double.valueOf(astra[3])));
                }
            }
            if (str.startsWith("vn")) {
                if (astra.length == 4) {
                    normals.add(new Normal3(Double.valueOf(astra[1]), Double.valueOf(astra[2]), Double.valueOf(astra[3])));
                }
                if (astra.length == 5) {
                    continue;
                }
            }
            if (str.startsWith("vt")) { //broken
                double[] doubleArray = new double[2];
                doubleArray[0] = Double.valueOf(astra[1]);
                doubleArray[1] = Double.valueOf(astra[2]);
                textureData.add(doubleArray);
            }

            if (str.startsWith("f")) {
                processF(astra, faces3);
                /*System.out.println(astra[1].split("/+").length);
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
                    System.out.println("new poly");
            }*/
            }
        }
        if (!faces3.isEmpty()) {
            if (!normals.isEmpty()) {
                int[] check = faces3.get(0);
                if (check.length > 6) {
                    for (int[] a : faces3) {
                        Point3 p1 = points.get(a[0]);
                        Point3 p2 = points.get(a[1]);
                        Point3 p3 = points.get(a[2]);
                        Normal3 n1 = normals.get(a[6]);
                        Normal3 n2 = normals.get(a[7]);
                        Normal3 n3 = normals.get(a[8]);
                        objects.add(new Triangle(material, p1, n1, p2, n2, p3, n3));
                    }
                } else if (check.length == 6) {
                    for (int[] a : faces3) {
                        Point3 p1 = points.get(a[0]);
                        Point3 p2 = points.get(a[1]);
                        Point3 p3 = points.get(a[2]);
                        Normal3 n1 = normals.get(a[3]);
                        Normal3 n2 = normals.get(a[4]);
                        Normal3 n3 = normals.get(a[5]);
                        objects.add(new Triangle(material, p1, n1, p2, n2, p3, n3));
                    }
                }
            } else {
                for (int[] a : faces3) {
                    Point3 p1 = points.get(a[0]);
                    Point3 p2 = points.get(a[1]);
                    Point3 p3 = points.get(a[2]);
                    objects.add(new Triangle(material, p1, n1, p2, n2, p3, n3));
                }
            }
        }
    }

    private void processF(String[] array, ArrayList<int[]> faces3) {
        System.out.println(array[1]);
        if (array[1].matches("\\d//\\d")) {
            processFacesWithN(array, faces3);
        } else if (array[1].matches("\\d/\\d")) {
            processFacesWithT(array, faces3);
        } else if (array[1].matches("\\d/\\d/\\d")) {
            processFacesWithNAndT(array, faces3);
        } else {
            processFaces(array, faces3);
        }
    }

    private void processFacesWithN(String[] array, ArrayList<int[]> faces3) {
        if (array.length == 4) {
            int[] intArray = new int[6];
            intArray[0] = Integer.valueOf(array[1].split("//")[0]) - 1;
            intArray[1] = Integer.valueOf(array[2].split("//")[0]) - 1;
            intArray[2] = Integer.valueOf(array[3].split("//")[0]) - 1;
            intArray[3] = Integer.valueOf(array[1].split("//")[1]) - 1;
            intArray[4] = Integer.valueOf(array[2].split("//")[1]) - 1;
            intArray[5] = Integer.valueOf(array[3].split("//")[1]) - 1;
            faces3.add(intArray);
        }
    }

    private void processFacesWithT(String[] array, ArrayList<int[]> faces3) {
        if (array.length == 4) {
            int[] intArray = new int[3];
            intArray[0] = Integer.valueOf(array[1].split("/")[0]) - 1;
            intArray[1] = Integer.valueOf(array[2].split("/")[0]) - 1;
            intArray[2] = Integer.valueOf(array[3].split("/")[0]) - 1;
            faces3.add(intArray);
        }
    }

    private void processFacesWithNAndT(String[] array, ArrayList<int[]> faces3) {
        if (array.length == 4) {
            int[] intArray = new int[9];
            intArray[0] = Integer.valueOf(array[1].split("/")[0]) - 1;
            intArray[1] = Integer.valueOf(array[2].split("/")[0]) - 1;
            intArray[2] = Integer.valueOf(array[3].split("/")[0]) - 1;
            intArray[3] = Integer.valueOf(array[1].split("/")[1]) - 1;
            intArray[4] = Integer.valueOf(array[2].split("/")[1]) - 1;
            intArray[5] = Integer.valueOf(array[3].split("/")[1]) - 1;
            intArray[6] = Integer.valueOf(array[1].split("/")[2]) - 1;
            intArray[7] = Integer.valueOf(array[2].split("/")[2]) - 1;
            intArray[8] = Integer.valueOf(array[3].split("/")[2]) - 1;
            faces3.add(intArray);
        }
    }

    private void processFaces(String[] array, ArrayList<int[]> faces3) {
        if (array.length == 4) {
            int[] intArray = new int[3];
            intArray[0] = Integer.valueOf(array[1].split("//")[0]) - 1;
            intArray[1] = Integer.valueOf(array[2].split("//")[0]) - 1;
            intArray[2] = Integer.valueOf(array[3].split("//")[0]) - 1;
            faces3.add(intArray);
        }
    }
}