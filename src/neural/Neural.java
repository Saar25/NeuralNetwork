package neural;

import math.Maths;

public class Neural implements INeural {

    private final float[] weights;
    private float bias = Maths.randomf() * 2 - 1;

    public Neural(int inputs) {
        this.weights = new float[inputs];
        randomize(weights);
    }

    private static void randomize(float[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = Maths.randomf() * 2 - 1;
        }
    }

    @Override
    public float process(float... inputs) {
        validInputs(inputs);
        float weightedSum = bias;
        for (int i = 0; i < weights.length; i++) {
            weightedSum += weights[i] * inputs[i];
        }
        return weightedSum;
    }

    @Override
    public float adjust(float[] inputs, float output, float error) {
        validInputs(inputs);
        float delta = Maths.dsigmoid(output) * error * .1f;
        for (int i = 0; i < weights.length; i++) {
            weights[i] += delta * inputs[i];
        }
        //System.out.println(delta + " " + bias + Arrays.toString(inputs) + Arrays.toString(weights));
        bias += delta;
        float sum = 0;
        for (float weight : weights) {
            sum += weight;
        }
        return sum * error;
    }

    private void validInputs(float[] inputs) {
        if (inputs.length != weights.length) {
            throw new IllegalArgumentException("Cannot process the given inputs");
        }
    }
}
