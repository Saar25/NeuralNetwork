package neural.function;

import math.Maths;

public class SigmoidFunction implements ActivationFunction {

    @Override
    public float apply(float value) {
        return Maths.sigmoid(value);
    }

    @Override
    public float derivative(float value) {
        return Maths.dsigmoid(value);
    }
}
