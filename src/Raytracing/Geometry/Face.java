package Raytracing.Geometry;

import MathFunc.Normal3;
import MathFunc.Vector3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fap-Fap on 02.01.2016.
 */
public class Face{

    public Vector3 vertexIndex;
    public Normal3 normalIndex;
    public Face(Vector3 vertexIndex, Normal3 normalIndex){
        this.vertexIndex = vertexIndex;
        this.normalIndex = normalIndex;
    }
}
