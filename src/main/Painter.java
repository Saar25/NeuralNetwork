package main;

import math.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Painter extends JPanel {

    private static final int width = 800;
    private static final int height = 600;

    private final SortedSet<Point> points;
    private final List<Formula> formulas;

    public Painter() {
        this.points = new TreeSet<>((p1, p2) ->
                (int) (100 * (p1.getX() - p2.getX())));
        this.formulas = new ArrayList<>();
        setBackground(Color.BLACK);
    }

    public void start() {
        final JFrame jFrame = new JFrame("Neural Networks");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(width, height);
        jFrame.setVisible(true);

        jFrame.add(this);
    }

    public void addPoint(Point point) {
        this.points.add(point);
    }

    public void addFormula(Formula formula) {
        this.formulas.add(formula);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);

        for (Formula formula : formulas) {
            int x1 = 0, y1 = (int) formula.apply(x1);
            int x2 = width, y2 = (int) formula.apply(x2);
            g.drawLine(x1, y1, x2, y2);
        }

        final int r = 10;

        for (Point point : points) {
            int x = (int) (point.getX() * width);
            int y = (int) (point.getY() * height);
            g.fillOval(x - r / 2, y - r / 2, r, r);
        }
    }
}
