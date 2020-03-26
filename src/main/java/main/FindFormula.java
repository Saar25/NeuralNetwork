package main;

import neural.matrix.MatrixNeuralNetwork;
import neural.NeuralNetwork;

public class FindFormula {

    private static final float m = 1f;
    private static final float b = 0f;

    public static void main(String[] args) {
        final NeuralNetwork neuralNetwork = new MatrixNeuralNetwork(2, 1);

        for (int i = 0; i < 100000; i++) {
            float x = (float) Math.random() * 2;
            float y = (float) Math.random() * 2;
            neuralNetwork.backpropagate(array(x, y), y - f(x));
        }

        PredicatePainter painter = new PredicatePainter(p -> neuralNetwork.feedForward(p.toArray())[0]);
        painter.start();
    }

    private static float f(float x) {
        return m * x + b;
    }

    private static float[] array(float... floats) {
        return floats;
    }

}
