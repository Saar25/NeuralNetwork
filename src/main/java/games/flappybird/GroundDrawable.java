package games.flappybird;

import games.gui.Drawable;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class GroundDrawable implements Drawable {

    private static final Color BROWN = new Color(139, 69, 19);

    private final int height;
    private final int windowWidth;
    private final int windowHeight;

    public GroundDrawable(int height, int windowWidth, int windowHeight) {
        this.height = height;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    @Override
    public void draw(Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        final AffineTransform saveTransform = g2d.getTransform();
        final AffineTransform transform = new AffineTransform();
        g2d.setTransform(transform);

        final float grassRate = .3f;
        g.setColor(Color.GREEN);
        g.fillRect(0, windowHeight - height, windowWidth, (int) (height * grassRate));
        g.setColor(BROWN);
        g.fillRect(0, windowHeight - (int) (height * (1 - grassRate)), windowWidth, (int) (height * (1 - grassRate)));

        g2d.setTransform(saveTransform);
    }
}
