package games.flappybird;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class FlappyBirdRenderer extends JPanel {

    final JFrame jFrame;

    private List<Pipe> pipes = new LinkedList<>();
    private List<Bird> birds = new LinkedList<>();

    public FlappyBirdRenderer() {
        this.jFrame = new JFrame("Flappy Bird");
        jFrame.setSize(1000, 700);
        jFrame.setLocation(300, 50);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

        jFrame.add(this);
        setBackground(Color.CYAN);
    }

    public static void main(String[] args) {
        new FlappyBirdRenderer();
    }

    public void add(Drawable drawable) {
        super.add(new DrawableAdapter(drawable));
    }

    public void remove(Drawable drawable) {
        for (Component component : getComponents()) {
            if (component instanceof DrawableAdapter) {
                final DrawableAdapter adapter = ((DrawableAdapter) component);
                if (adapter.getDrawable() == drawable) {
                    super.remove((component));
                }
            }
        }
    }

    @Deprecated
    public void setPipes(List<Pipe> pipes) {
        this.pipes.clear();
        this.pipes.addAll(pipes);
    }

    @Deprecated
    public void setBirds(List<Bird> birds) {
        this.birds.clear();
        this.birds.addAll(birds);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        final Graphics2D g = (Graphics2D) graphics;

        if (!birds.isEmpty()) {
            String str = "Score: " + birds.get(0).getScore();
            g.setFont(new Font("default", Font.BOLD, 48));
            int len = g.getFontMetrics().stringWidth(str);
            g.drawString(str, (jFrame.getWidth() - len) / 2, 50);

            str = "Birds alive: " + birds.size();
            g.setFont(new Font("default", Font.BOLD, 24));
            len = g.getFontMetrics().stringWidth(str);
            g.drawString(str, (jFrame.getWidth() - len) / 2, 85);
        }

        g.translate(0, jFrame.getHeight());
        g.scale(1, -1);

        if (!birds.isEmpty()) {
            g.translate(50 - birds.get(0).getPosition().getX(), 0);
        }

        g.setColor(Color.GREEN);
        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }
        int score = 0;
        g.setColor(Color.RED);
        for (Bird bird : birds) {
            bird.draw(g);
            score = Math.max(score, bird.getScore());
        }

    }
}
