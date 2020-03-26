package neural.matrix;

import neural.function.ActivationFunction;

import java.util.ArrayList;
import java.util.List;

public class MatrixNeuralNetworkConfig {

    private final List<Integer> layers = new ArrayList<>();
    private ActivationFunction activationFunction;
    private float learningRate;

    public List<Integer> getLayers() {
        return layers;
    }

    public ActivationFunction getActivationFunction() {
        return activationFunction;
    }

    public float getLearningRate() {
        return learningRate;
    }

    public MatrixNeuralNetworkConfig setLayers(int inputs, int... layers) {
        getLayers().clear();
        getLayers().add(inputs);
        for (int layer : layers) {
            getLayers().add(layer);
        }
        return this;
    }

    public MatrixNeuralNetworkConfig setActivationFunction(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
        return this;
    }

    public MatrixNeuralNetworkConfig setLearningRate(float learningRate) {
        this.learningRate = learningRate;
        return this;
    }
}
