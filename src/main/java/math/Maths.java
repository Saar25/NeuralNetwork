package math;

import kotlin.jvm.functions.Function1;

public final class Maths {

    public static final Function1<Float, Float> sigmoid = Maths::sigmoid;
    public static final Function1<Float, Float> dsigmoid = Maths::dsigmoid;
    public static final Function1<Float, Float> dsigmoidOfSigmoid = Maths::dsigmoidOfSigmoid;

    private Maths() {

    }

    public static float randomf() {
        return (float) Math.random();
    }

    public static float randomf(float min, float max) {
        return randomf() * (max - min) + min;
    }

    public static float sigmoid(float x) {
        return (float) (1 / (1 + Math.exp(-x)));
    }

    public static float dsigmoid(float x) {
        return dsigmoidOfSigmoid(sigmoid(x));
    }

    public static float dsigmoidOfSigmoid(float x) {
        return x * (1 - x);
    }

    public static float expf(float value) {
        return (float) Math.exp(value);
    }

    public static float max(float max, float... values) {
        for (float value : values)
            max = Math.max(max, value);
        return max;
    }

    public static float min(float min, float... values) {
        for (float value : values)
            min = Math.min(min, value);
        return min;
    }

    public static float max(float[] values) {
        return max(values[0], values);
    }

    public static float min(float[] values) {
        return min(values[0], values);
    }

    public static int maxIndex(float[] values) {
        int max = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > values[max]) {
                max = i;
            }
        }
        return max;
    }

    public static int minIndex(float[] values) {
        int min = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] < values[min]) {
                min = i;
            }
        }
        return min;
    }

}
