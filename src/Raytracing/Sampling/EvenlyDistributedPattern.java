package Raytracing.Sampling;

import MathFunc.Point2;

public class EvenlyDistributedPattern extends SamplingPattern {

    private final Point2[] pattern;

    public static void main(String[] args) {
        new EvenlyDistributedPattern(4);
    }

    public EvenlyDistributedPattern(int sampleResolution) {
        super(sampleResolution);
        pattern = new Point2[sampleResolution * sampleResolution]; // inited here, because of compiler checks.
        fillArrayWithPattern();
    }

    public Point2[] generatePattern() {
        return pattern;
    }

    /**
     * Alright, here it goes:
     * (i * sR + j) - generate in order numbers from 0 to i*j-1
     * The next segment, we define some stuff:
     * x = i; y = j;
     * Then we get this:
     * (x+1)*2/pS-0.5, (y+1)*2/pS-0.5
     * It generates the median offset of a ray generated for one pixel (example with 3x3) :
     * 1 2 3 4 5 6 7
     * ┌───────┐
     * 1 │            │
     * 2 │ •   •   •  │
     * 3 │            │
     * 4 │ •   •   •  │
     * 5 │            │
     * 6 │ •   •   •  │
     * 7 │            │
     * └───────┘
     * (Just pretend it's a square)
     * Also it moves the coordinates to fit from y,x ^= -0.5 ... 0.5
     */
    private void fillArrayWithPattern() {
        double patternSize = 2.0 * sampleResolution + 1;
        for (int i = 0; i < sampleResolution; i++) {
            for (int j = 0; j < sampleResolution; j++) {
                pattern[i * sampleResolution + j] = new Point2((i + 1) * 2 / patternSize - 0.5, (j + 1) * 2 / patternSize - 0.5);
            }
        }
    }
}
