package games.flappybird;

import math.Vector2;

import java.awt.*;

public class Pipe implements Drawable {

    private final Rectangle top;
    private final Rectangle bottom;

    public Pipe(Rectangle top, Rectangle bottom) {
        this.top = top;
        this.bottom = bottom;
    }

    public boolean isColliding(Vector2 point) {
        return top.isColliding(point) || bottom.isColliding(point);
    }

    public boolean isColliding(Rectangle rectangle) {
        return top.isColliding(rectangle) || bottom.isColliding(rectangle);
    }

    public Rectangle getTop() {
        return top;
    }

    public Rectangle getBottom() {
        return bottom;
    }

    @Override
    public void draw(Graphics g) {
        top.draw(g);
        bottom.draw(g);
    }
}
