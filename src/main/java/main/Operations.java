package main;

import neural.INeural;
import neural.matrix.MatrixNeuralNetwork;
import neural.Neural;
import neural.NeuralNetwork;

import java.util.Arrays;
import java.util.Random;

public class Operations {

    public static void main(String[] args) {
        teachAddition();
        System.out.println();
        teachSubtraction();
    }

    private static void teachAddition() {
        System.out.println("Addition");
        teach(Float::sum);
    }

    private static void teachSubtraction() {
        System.out.println("Subtraction");
        teach((a, b) -> a - b);
    }

    private static void teach(Operation operation) {
        NeuralNetwork nn = new MatrixNeuralNetwork(2, 1);
        INeural neural = new Neural(2);

        Random random = new Random();
        for (int i = 0; i < 50000; i++) {
            float a = random.nextInt(8) / 8f;
            float b = random.nextInt(8) / 8f;
            float target = operation.compute(a, b);
            nn.backpropagate(array(a, b), target);
            neural.adjustByTarget(array(a, b), target);
        }
        float[][] inputs = {
                {4f, 3f}, {8f, 2f},
                {7f, 4f}, {0f, 5f},
                {5f, 6f}, {-7, 4f}
        };

        for (float[] input : inputs) {
            System.out.print("Answer1 is " + operation.compute(input[0], input[1]) + ": ");
            print(nn.feedForward(input));
            //System.out.print("Answer2 is " + operation.compute(input[0], input[1]) + ": ");
            //print(neural.process(input));
        }
    }

    private static void print(float... array) {
        System.out.println(Arrays.toString(array));
    }

    private static float[] array(float... array) {
        return array;
    }

    interface Operation {
        float compute(float a, float b);
    }
}
