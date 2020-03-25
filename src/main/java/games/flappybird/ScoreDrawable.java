package games.flappybird;

import games.gui.Drawable;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class ScoreDrawable implements Drawable {

    private final Score score;
    private final int windowWidth;

    public ScoreDrawable(Score score, int windowWidth) {
        this.score = score;
        this.windowWidth = windowWidth;
    }

    @Override
    public void draw(Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        final AffineTransform saveTransform = g2d.getTransform();
        final AffineTransform transform = new AffineTransform();
        g2d.setTransform(transform);

        g.setColor(Color.BLACK);
        g.setFont(new Font("default", Font.BOLD, 48));
        final String string = "Score: " + score.get();
        final int stringLength = g.getFontMetrics().stringWidth(string);
        g.drawString(string, (windowWidth - stringLength) / 2, 50);

        g2d.setTransform(saveTransform);
    }
}
