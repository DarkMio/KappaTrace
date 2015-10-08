package Vorbereitung;


public class Test {
    public static void main(String[] args){
        Mat3x3 m1 = new Mat3x3(1,2,3,4,5,6,7,8,9);
        Mat3x3 m2 = new Mat3x3(11,12,13,14,15,16,17,18,19);
        Mat3x3 m3 = m1.mul(m2);
        System.out.printf(m3.toString());
    }
}
