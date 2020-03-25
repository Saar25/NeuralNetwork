package main;

import neural.MatrixNeuralNetwork;
import neural.NeuralNetwork;
import util.Progress;

import java.util.Random;

public class BinaryMultiplication {

    private static final int trainings = 50000;
    private static final int tests = 500;

    private static final int size1 = 5;
    private static final int size2 = 5;

    private static final int size = size1 + size2;

    public static void main(String[] args) {
        final NeuralNetwork nn = new MatrixNeuralNetwork(size, 128, 128, size * 2);

        teach(nn);
        test(nn);
    }

    private static void teach(NeuralNetwork nn) {
        final Progress training = new Progress(trainings);
        final Random random = new Random();
        for (int i = 0; i < trainings; i++) {
            int n1 = random.nextInt(1 << size1);
            int n2 = random.nextInt(1 << size2);
            float[] input = toFloatBitArray((n1 << size2) + n2, size);
            float[] target = toFloatBitArray(n1 * n2, size * 2);
            nn.backpropagate(input, target);
            training.inc();
            if (training.changed())
                System.out.print("\r" + training);
        }
        System.out.println();
    }

    private static void test(NeuralNetwork nn) {
        final Progress progress = new Progress(tests);
        final Random random = new Random();
        for (int i = 0; i < tests; i++) {
            int n1 = random.nextInt(1 << size1);
            int n2 = random.nextInt(1 << size2);

            float[] input = toFloatBitArray((n1 << size2) + n2, size);
            int in = bitArrayToInt(toBitArray(toFloatBitArray(n1 * n2, size * 2)));
            int out = bitArrayToInt(toBitArray(nn.feedForward(input)));

            if (in == out) {
                System.out.print("V ");
                progress.inc();
            } else {
                System.out.print("X ");
            }
            System.out.println(n1 + " " + n2);
        }
        System.out.println(progress);
    }

    private static float[] toFloatBitArray(int n, int size) {
        float[] bits = new float[size];
        for (int i = 0; n > 0; i++) {
            bits[i] = n & 1;
            n >>= 1;
        }
        return bits;
    }

    private static int[] toBitArray(float[] array) {
        int[] bitArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            bitArray[i] = array[i] > .5f ? 1 : 0;
        }
        return bitArray;
    }

    private static int bitArrayToInt(int[] array) {
        int n = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1) n |= 1 << i;
        }
        return n;
    }

}
