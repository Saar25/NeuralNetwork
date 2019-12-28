package games.flappybird;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.List;

public class Renderer extends JComponent {

    private final List<Drawable> drawables = new LinkedList<>();
    private final AffineTransform transform = new AffineTransform();

    public void add(Drawable drawable) {
        this.drawables.add(drawable);
    }

    public void remove(Drawable drawable) {
        this.drawables.remove(drawable);
    }

    public AffineTransform getTransform() {
        return transform;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ((Graphics2D) g).setTransform(getTransform());

        for (Drawable drawable : drawables) {
            drawable.draw(g);
        }
    }
}
