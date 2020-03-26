package neural.matrix;

import math.Matrixf;
import neural.NeuralLayer;

public class MatrixNeuralLayer implements NeuralLayer {

    private final MatrixNeuralNetworkConfig config;

    private Matrixf weights;
    private Matrixf biases;

    private MatrixNeuralLayer(MatrixNeuralNetworkConfig config, Matrixf weights, Matrixf biases) {
        this.weights = weights;
        this.biases = biases;
        this.config = config;
    }

    public MatrixNeuralLayer(MatrixNeuralNetworkConfig config, int neurals, int inputs) {
        this.weights = Matrixf.randomize(neurals, inputs);
        this.biases = Matrixf.randomize(neurals, 1);
        this.config = config;
    }

    @Override
    public float[] process(float... inputs) {
        final Matrixf inputMatrix = Matrixf.fromArray(inputs);
        return process(inputMatrix).toArray();
    }

    @Override
    public float[] adjust(float[] inputs, float[] outputs, float[] errors) {
        final Matrixf inputMatrix = Matrixf.fromArray(inputs);
        final Matrixf errorMatrix = Matrixf.fromArray(errors);
        final Matrixf outputsMatrix = Matrixf.fromArray(outputs);
        return adjust(inputMatrix, outputsMatrix, errorMatrix).toArray();
    }

    @Override
    public float[] adjust(float[] inputs, float[] errors) {
        final Matrixf inputMatrix = Matrixf.fromArray(inputs);
        final Matrixf errorMatrix = Matrixf.fromArray(errors);
        return adjust(inputMatrix, errorMatrix).toArray();
    }

    @Override
    public MatrixNeuralLayer mutate(float rate) {
        final Matrixf biases = this.biases.mutate(rate);
        final Matrixf weights = this.weights.mutate(rate);
        return new MatrixNeuralLayer(config, weights, biases);
    }

    @Override
    public int getInputs() {
        return weights.getCols();
    }

    @Override
    public int getOutputs() {
        return weights.getRows();
    }

    public Matrixf process(Matrixf input) {
        if (!weights.canMultiply(input)) {
            throw new IllegalArgumentException("Cannot process the given inputs");
        }
        return weights.dot(input).add(biases);
    }

    public Matrixf adjust(Matrixf input, Matrixf error) {
        final Matrixf output = process(input);
        return adjust(input, output, error);
    }

    public Matrixf adjust(Matrixf input, Matrixf output, Matrixf error) {
        final Matrixf delta = output.map(value -> config
                .getActivationFunction().derivative(value))
                .mul(error).mul(config.getLearningRate());
        final Matrixf inputT = input.transpose();
        final Matrixf deltaWeights = delta.dot(inputT);

        this.weights = this.weights.add(deltaWeights);
        this.biases = this.biases.add(delta);
        return this.weights.transpose().dot(error);
    }

    @Override
    public String toString() {
        return "Weights:\n" + weights.toString() + "\nBiases:\n" + biases.toString();
    }
}
