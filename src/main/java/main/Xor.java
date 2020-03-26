package main;

import neural.matrix.MatrixNeuralNetwork;
import neural.NeuralNetwork;
import util.Progress;

import javax.swing.*;
import java.util.Arrays;
import java.util.Random;

public class Xor {

    public static void main(String[] args) {
        final NeuralNetwork neuralNetwork = new MatrixNeuralNetwork(2, 4, 1);

        int inputs = 500000;
        final Progress progress = new Progress(inputs);
        final Random random = new Random();
        for (int i = 0; i < inputs; i++) {
            final int n1 = random.nextInt(2);
            final int n2 = random.nextInt(2);
            neuralNetwork.backpropagate(array(n1, n2), n1 ^ n2);
            progress.inc();
            System.out.print("\r" + progress);
        }
        System.out.println();
        System.out.println(progress.timeTaken());

        System.out.print("Should be 1: ");
        print(neuralNetwork.feedForward(0, 1));
        System.out.print("Should be 1: ");
        print(neuralNetwork.feedForward(1, 0));
        System.out.print("Should be 0: ");
        print(neuralNetwork.feedForward(1, 1));
        System.out.print("Should be 0: ");
        print(neuralNetwork.feedForward(0, 0));


        PredicatePainter painter = new PredicatePainter(p -> neuralNetwork.feedForward(p.toArray())[0]);
        painter.start();

        //visualizeTraining(neuralNetwork, painter);
    }

    private static void visualizeTraining(NeuralNetwork neuralNetwork, PredicatePainter painter) {
        new Timer(20, e -> {
            for (int i = 0; i < 500; i++) {
                int n1 = (int) Math.round(Math.random());
                int n2 = (int) Math.round(Math.random());
                neuralNetwork.backpropagate(array(n1, n2), n1 ^ n2);
            }
            painter.repaint();
        }).start();
    }

    private static void print(float[] array) {
        System.out.println(Arrays.toString(array));
    }

    private static float[] array(float... floats) {
        return floats;
    }

}
