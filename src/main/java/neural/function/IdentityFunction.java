package neural.function;

public class IdentityFunction implements ActivationFunction {

    @Override
    public float apply(float value) {
        return value;
    }

    @Override
    public float derivative(float value) {
        return 1;
    }
}
