package Raytracing.Geometry;

import MathFunc.Normal3;
import MathFunc.Vector3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fap-Fap on 02.01.2016.
 */
public class Model {

    public List<Vector3> vertices;
    public List <Normal3> normals;
    public List<Face> faces = new ArrayList<Face>();


    public Model(Model model){

    }

}

