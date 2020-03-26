package neural.function;

public interface ActivationFunction {

    float apply(float value);

    float derivative(float value);

}
