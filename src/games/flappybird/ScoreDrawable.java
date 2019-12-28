package games.flappybird;

import java.awt.*;

public class ScoreDrawable implements Drawable {

    private final Score score;
    private final int windowWidth;

    public ScoreDrawable(Score score, int windowWidth) {
        this.score = score;
        this.windowWidth = windowWidth;
    }

    @Override
    public void draw(Graphics g) {
        g.setFont(new Font("default", Font.BOLD, 48));
        final String string = "Score: " + score.get();
        final int stringLength = g.getFontMetrics().stringWidth(string);
        g.drawString(string, (windowWidth - stringLength) / 2, 50);
    }
}
