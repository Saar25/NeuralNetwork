package neural;

import math.Maths;
import math.Matrixf;

public class MatrixNeuralLayer implements NeuralLayer {

    private Matrixf weights;
    private Matrixf biases;

    private MatrixNeuralLayer(Matrixf weights, Matrixf biases) {
        this.weights = weights;
        this.biases = biases;
    }

    public MatrixNeuralLayer(int neurals, int inputs) {
        this.weights = Matrixf.randomize(neurals, inputs);
        this.biases = Matrixf.randomize(neurals, 1);
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
        final Matrixf weights = this.weights.mutate(rate);
        final Matrixf biases = this.biases.mutate(rate);
        return new MatrixNeuralLayer(weights, biases);
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
        final Matrixf delta = output.map(Maths.dsigmoid)
                .mul(error).mul(.5f);
        final Matrixf inputT = input.transpose();
        final Matrixf deltaWeights = delta.dot(inputT);

        weights = weights.add(deltaWeights);
        biases = biases.add(delta);
        return weights.transpose().dot(error);
    }

    @Override
    public String toString() {
        return "Weights:\n" + weights.toString() + "\nBiases:\n" + biases.toString();
    }
}
