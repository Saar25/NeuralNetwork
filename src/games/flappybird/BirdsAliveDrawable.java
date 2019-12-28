package games.flappybird;

import java.awt.*;

public class BirdsAliveDrawable implements Drawable {

    private final BirdPopulation population;
    private final int windowWidth;

    public BirdsAliveDrawable(BirdPopulation population, int windowWidth) {
        this.population = population;
        this.windowWidth = windowWidth;
    }

    @Override
    public void draw(Graphics g) {
        g.setFont(new Font("default", Font.BOLD, 24));
        final String string = "Birds alive: " + population.count();
        final int stringLength = g.getFontMetrics().stringWidth(string);
        g.drawString(string, (windowWidth - stringLength) / 2, 85);
    }
}
