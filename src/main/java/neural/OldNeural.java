package neural;

import main.Formula;

public class OldNeural implements INeural {

    private final float[] weights;
    private float output;

    public OldNeural(int inputs) {
        this.weights = new float[inputs + 1];
        randomize(weights);
        setBias(0);
    }

    private static void randomize(float[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) Math.random() * 2 - 1;
        }
    }

    @Override
    public float process(float... inputs) {
        validInputs(inputs);

        output = getBias();
        for (int i = 0; i < inputs.length; i++) {
            output += inputs[i] * weights[i];
        }
        return output;
    }

    @Override
    public float adjust(float[] inputs, float output, float error) {
        validInputs(inputs);
        for (int i = 0; i < inputs.length; i++) {
            weights[i] += inputs[i] * error;
        }
        setBias(getBias() + error);
        return 0;
    }

    public void adjustWeights(float[] inputs, float target) {
        validInputs(inputs);

        float guess = process(inputs);
        float error = (target - guess) * .1f;

        for (int i = 0; i < inputs.length; i++) {
            weights[i] += inputs[i] * error;
        }
        setBias(getBias() + error);
    }

    public float getOutput() {
        return output;
    }

    private float getBias() {
        return weights[weights.length - 1];
    }

    private void setBias(float value) {
        weights[weights.length - 1] = value;
    }

    public String getFormulaString() {
        return String.format("y = %.3fx + %.3f", -weights[0] / weights[1], -getBias() / weights[1]);
    }

    public Formula getFormula() {
        return x -> -weights[0] / weights[1] * x - getBias() / weights[1];
    }

    private void validInputs(float[] inputs) {
        if (inputs.length != weights.length - 1) {
            throw new AssertionError("Wrong number of inputs, Received: " + inputs.length + ", Required: " + weights.length);
        }
    }
}
