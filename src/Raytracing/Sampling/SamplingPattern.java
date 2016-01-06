package Raytracing.Sampling;

import MathFunc.Point2;

/**
 * Created by Mio on 06/01/2016.
 */
public abstract class SamplingPattern {
    public final int sampleResolution;

    public SamplingPattern(int sampleResolution){
        this.sampleResolution = sampleResolution;
    }

    public abstract Point2[] generatePattern();
}
