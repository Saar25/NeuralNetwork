package main;

import math.Maths;
import neural.MatrixNeuralNetwork;
import neural.NeuralNetwork;
import util.Progress;
import util.StopWatch;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DigitsRecognition {

    private static final int imageCount = 60000;
    private static final int pixelsPerImage = 28 * 28;

    public static void main(String[] args) {
        // Load all necessary data
        final StopWatch stopWatch = new StopWatch();
        final List<Byte> imageData = readFile("/train-images.idx3-ubyte", 16, imageCount * pixelsPerImage);
        System.out.println("Loaded all images! Time taken: " + stopWatch.stop() + " ms");

        stopWatch.restart();
        final List<Byte> labelData = readFile("/train-labels.idx1-ubyte", 8, imageCount);
        System.out.println("Loaded all labels! Time taken: " + stopWatch.stop() + " ms");

        stopWatch.restart();
        final List<ImageData> images = toImageData(imageData, labelData);
        System.out.println("Created all image data! Time taken: " + stopWatch.stop() + " ms");

        // Delete unnecessary data
        imageData.clear();
        labelData.clear();

        // Train the neural network
        stopWatch.restart();
        final int epoch = 1;
        final Progress progress = new Progress(imageCount * epoch);
        final NeuralNetwork nn = new MatrixNeuralNetwork(784, 15, 10);

        for (int i = 0; i < epoch; i++) {
            for (ImageData image : images) {
                image.adjust(nn);
                progress.inc();
                if (progress.changed()) {
                    System.out.print("\rTraining " + progress);
                }
            }
        }
        System.out.println("\nTrained the brain! Time taken: " + stopWatch.stop() + " ms");
        images.clear();

        try (Scanner scanner = new Scanner(System.in)) {
            String fileName;
            while (!(fileName = scanner.next()).equals("-1")) {
                float[] data = loadImage("/" + fileName + ".png");
                print(data);

                float[] guess = nn.feedForward(data);
                System.out.println("\nOutput " + Arrays.toString(guess));
                System.out.println("Digit recognized: " + Maths.maxIndex(guess));
            }
        }
    }

    private static List<Byte> readFile(String filePath, int toSkip, int capacity) {
        final List<Byte> data = new ArrayList<>(capacity);
        try (InputStream in = Class.class.getResourceAsStream(filePath)) {
            int read;
            in.skip(toSkip);
            final byte[] buffer = new byte[5000];
            while ((read = in.read(buffer, 0, buffer.length)) != -1) {
                for (int i = 0; i < read; i++) {
                    data.add(buffer[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static List<ImageData> toImageData(List<Byte> imageData, List<Byte> labelData) {
        final List<ImageData> images = new ArrayList<>(imageCount);
        final int pixelCount = 28 * 28;
        float[] data = new float[pixelCount];
        for (int i = 0; i < imageData.size(); i++) {
            float f = asUnsigned(imageData.get(i));
            data[i % pixelCount] = f;
            if ((i + 1) % pixelCount == 0) {
                images.add(new ImageData(labelData.get(i / pixelCount), data));
                data = new float[pixelCount];
            }
        }
        return images;
    }

    private static float[] loadImage(String filePath) {
        float[] data = new float[28 * 28 * 3];
        try (InputStream in = Class.class.getResourceAsStream(filePath)) {
            if (in != null) {
                BufferedImage image = ImageIO.read(in);
                image.getData().getPixels(0, 0, 28, 28, data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return takeThird(data);
    }

    private static float[] takeThird(float[] array) {
        float[] newArray = new float[array.length / 3];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i * 3] / 255;
        }
        return newArray;
    }

    private static float asUnsigned(byte b) {
        return (b & 255) / 255f;
    }

    private static String format(float i) {
        if (i > .8) {
            return "X";
        } else if (i > .4) {
            return "x";
        } else if (i > 0) {
            return "+";
        } else {
            return "-";
        }
    }

    private static void print(float[] image) {
        for (int i = 0; i < image.length; i++) {
            System.out.print(format(image[i]));
            if ((i + 1) % 28 == 0)
                System.out.println();
        }
        System.out.println();
    }

    private static class ImageData {

        private final int label;
        private final float[] data;

        private ImageData(int label, float[] data) {
            this.label = label;
            this.data = data;
        }

        private void adjust(NeuralNetwork nn) {
            float[] target = new float[10];
            target[label] = 1;
            nn.backpropagate(data, target);
        }

        private void print() {
            System.out.println("Label = " + label);
            DigitsRecognition.print(data);
        }
    }

}
