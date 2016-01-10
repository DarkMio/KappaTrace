package Raytracing.Sampling;

import MathFunc.Point2;

public abstract class SamplingPattern {
    public final int sampleResolution;

    public SamplingPattern(int sampleResolution) {
        this.sampleResolution = sampleResolution;
    }

    public abstract Point2[] generatePattern();
}
