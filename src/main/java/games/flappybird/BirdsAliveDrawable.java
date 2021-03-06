package games.flappybird;

import games.gui.Drawable;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class BirdsAliveDrawable implements Drawable {

    private final BirdPopulation population;
    private final int windowWidth;

    public BirdsAliveDrawable(BirdPopulation population, int windowWidth) {
        this.population = population;
        this.windowWidth = windowWidth;
    }

    @Override
    public void draw(Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        final AffineTransform saveTransform = g2d.getTransform();
        final AffineTransform transform = new AffineTransform();
        g2d.setTransform(transform);

        g.setFont(new Font("default", Font.BOLD, 24));
        final String string = "Birds alive: " + population.count();
        final int stringLength = g.getFontMetrics().stringWidth(string);
        g.drawString(string, (windowWidth - stringLength) / 2, 85);

        g2d.setTransform(saveTransform);
    }
}
