package main;

import math.Point;

import javax.swing.*;
import java.awt.*;
import java.util.function.Function;

public class PredicatePainter extends JPanel {

    private static final int width = 1200;
    private static final int height = 700;
    private static final int pointSize = 20;

    private final Function<Point, Float> colourFunction;
    private JFrame jFrame;

    public PredicatePainter(Function<Point, Float> colourFunction) {
        this.colourFunction = colourFunction;
        setBackground(Color.BLACK);
    }

    public void start() {
        jFrame = new JFrame("Neural Networks");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(width, height);
        jFrame.setLocation(200, 50);
        jFrame.setVisible(true);

        jFrame.add(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);

        int width = jFrame.getWidth();
        int height = jFrame.getHeight();
        for (int x = 0; x < width; x += pointSize) {
            for (int y = 0; y < height; y += pointSize) {
                Point p = new Point((float) x / width, (float) y / height);
                int colour = (int) (colourFunction.apply(p) * 255);
                colour = Math.min(Math.max(colour, 0), 255);

                g.setColor(new Color(colour, colour, colour));
                g.fillRect(x, y, pointSize, pointSize);
            }
        }
    }
}
