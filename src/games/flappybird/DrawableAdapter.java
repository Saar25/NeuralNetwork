package games.flappybird;

import javax.swing.*;
import java.awt.*;

public class DrawableAdapter extends JComponent {

    private final Drawable drawable;

    public DrawableAdapter(Drawable drawable) {
        this.drawable = drawable;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        getDrawable().draw(g);
    }
}
