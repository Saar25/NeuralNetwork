package games.gui;

import math.Vector2;

import java.awt.*;

public class Rectangle implements Drawable {

    private final Vector2 position;
    private final Vector2 dimension;

    public Rectangle(Vector2 position, Vector2 dimension) {
        this(position.getX(), position.getY(), dimension.getX(), dimension.getY());
    }

    public Rectangle(float x, float y, float w, float h) {
        this.position = Vector2.mutable(x, y);
        this.dimension = Vector2.mutable(w, h);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getDimension() {
        return dimension;
    }

    public boolean isColliding(Vector2 point) {
        return xMin() < point.getX() && yMin() < point.getY() &&
                xMax() > point.getX() && yMax() > point.getY();
    }

    public boolean isColliding(Rectangle rectangle) {
        return xMin() < rectangle.xMax() && xMax() > rectangle.xMin() &&
                yMin() < rectangle.yMax() && yMax() > rectangle.yMin();
    }

    public float xMin() {
        return position.getX();
    }

    public float yMin() {
        return position.getY();
    }

    public float xMax() {
        return position.getX() + dimension.getX();
    }

    public float yMax() {
        return position.getY() + dimension.getY();
    }

    public float xCenter() {
        return position.getX() + dimension.getX() / 2;
    }

    public float yCenter() {
        return position.getY() + dimension.getY() / 2;
    }

    @Override
    public void draw(Graphics g) {
        g.fillRect((int) position.getX(), (int) position.getY(),
                (int) dimension.getX(), (int) dimension.getY());
    }

    @Override
    public String toString() {
        return "[Rectangle: Position = " + position + ", Dimension = " + dimension + "]";
    }
}
