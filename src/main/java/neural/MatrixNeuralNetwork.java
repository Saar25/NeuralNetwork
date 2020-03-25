package neural;

import math.Maths;
import math.Matrixf;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MatrixNeuralNetwork implements NeuralNetwork {

    private final List<MatrixNeuralLayer> layers = new ArrayList<>();
    private int outputs;

    private MatrixNeuralNetwork(int inputs) {
        this.outputs = inputs;
    }

    public MatrixNeuralNetwork(int inputs, int outputs) {
        this.outputs = inputs;
        addLayer(outputs);
    }

    public MatrixNeuralNetwork(int inputs, int... layers) {
        this.outputs = inputs;
        for (int neurals : layers) {
            addLayer(neurals);
        }
    }

    private void addLayer(MatrixNeuralLayer layer) {
        this.layers.add(layer);
        this.outputs = layer.getOutputs();
    }

    @Override
    public void addLayer(int neurals) {
        this.addLayer(new MatrixNeuralLayer(neurals, outputs));
    }

    @Override
    public float[] feedForward(float... inputs) {
        final Matrixf matrix = Matrixf.fromArray(inputs);
        return feedForward(matrix).toArray();
    }

    @Override
    public void backpropagate(float[] inputs, float... targets) {
        final Matrixf targetMatrix = Matrixf.fromArray(targets);
        final Matrixf inputMatrix = Matrixf.fromArray(inputs);
        backpropagation(inputMatrix, targetMatrix);
    }

    @Override
    public MatrixNeuralNetwork mutate(float rate) {
        final int inputs = layers.isEmpty() ? outputs : layers.get(0).getInputs();
        final MatrixNeuralNetwork neuralNetwork = new MatrixNeuralNetwork(inputs);
        for (MatrixNeuralLayer layer : layers) {
            neuralNetwork.addLayer(layer.mutate(rate));
        }
        return neuralNetwork;
    }

    private Matrixf feedForward(Matrixf input) {
        Matrixf output = input;
        for (MatrixNeuralLayer layer : layers) {
            output = layer.process(input);
            input = output.map(Maths.sigmoid);
        }
        return output;
    }

    private void backpropagation(Matrixf input, Matrixf target) {
        final List<Matrixf> outputs = new LinkedList<>();
        final List<Matrixf> inputs = new LinkedList<>();
        inputs.add(input);

        Matrixf output = input;
        for (MatrixNeuralLayer layer : layers) {
            outputs.add(output = layer.process(input));
            inputs.add(input = output.map(Maths.sigmoid));
        }

        Matrixf error = target.sub(output);
        for (int i = layers.size() - 1; i >= 0; i--) {
            final MatrixNeuralLayer layer = layers.get(i);
            error = layer.adjust(inputs.get(i), outputs.get(i), error);
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Matrix Neural Network");
        for (int i = 0; i < layers.size(); i++) {
            MatrixNeuralLayer layer = layers.get(i);
            string.append("\nLayer ").append(i + 1).append('\n');
            string.append(layer.toString()).append('\n');
        }
        return string.toString();
    }
}
