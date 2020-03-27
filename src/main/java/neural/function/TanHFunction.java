package neural.function;

import math.Maths;

public class TanHFunction implements ActivationFunction {

    @Override
    public float apply(float value) {
        final float ep = Maths.expf(+value);
        final float em = Maths.expf(-value);
        return (ep - em) / (ep + em);
    }

    @Override
    public float derivative(float value) {
        final float apply = apply(value);
        return 1 - apply * apply;
    }
}
