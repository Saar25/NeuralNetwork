package main;

import math.Point;
import neural.OldNeural;

import javax.swing.*;

public class FindLinearEquation {

    private static final int length = 500;
    private static final int inputCount = 2;

    public static void main(String[] args) {
        Point[] inputs = new Point[length];
        float[] targets = new float[length];
        OldNeural neural = new OldNeural(inputCount);

        getValues(inputs, targets);
        train(neural, inputs, targets);

        int correct = 0;
        int wrong = 0;
        for (int i = 0; i < length; i++) {

            float x = inputs[i].getX();
            float y = inputs[i].getY();
            float ans = neural.process(inputs[i].toArray());

            if (ans - f(x) < .001f) {
                System.out.print("V ");
                correct++;
            } else {
                System.out.print(">>>>> X ");
                wrong++;
            }
            System.out.printf("%s%.3f %.3f %.3f \n", ans > 0 ? "+" : "", ans, x, y);

            //neural.adjustWeights(inputs[i].toArray(), targets[i]);
        }

        System.out.println();
        System.out.println("Correct:  " + correct);
        System.out.println("Wrong:    " + wrong);
        System.out.println("Percents: " + 100d * correct / length + "%");
        System.out.print(neural.getFormulaString());

        Painter painter = new Painter();
        painter.addFormula(FindLinearEquation::f);
        painter.addFormula(x -> neural.getFormula().apply(x));
        for (Point points : inputs) {
            painter.addPoint(points);
        }
        painter.start();

        new Timer(300, e -> {
            Point[] ps = new Point[10];
            float[] ts = new float[10];
            getValues(ps, ts);
            train(neural, ps, ts);
            System.out.print("\r" + neural.getFormulaString());

            painter.repaint();
        }).start();
    }

    private static float f(float x) {
        return .1f * x + 20;
    }

    private static void getValues(Point[] inputs, float[] targets) {
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = Point.random();
            targets[i] = target(inputs[i]);
        }
    }

    private static float target(Point point) {
        //return f(point.getX()) > point.getY() ? 1 : -1;
        return f(point.getX()) - point.getY();
    }

    private static void train(OldNeural neural, Point[] inputs, float[] targets) {
        for (int i = 0; i < inputs.length; i++) {
            neural.adjustWeights(inputs[i].toArray(), targets[i]);
        }
    }

}
