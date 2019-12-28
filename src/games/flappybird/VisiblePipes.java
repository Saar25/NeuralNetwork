package games.flappybird;

import math.Maths;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class VisiblePipes implements Drawable {

    private final int maxVisiblePipes;
    private final List<Pipe> pipes = new LinkedList<>();
    private int pipesCount = 0;

    public VisiblePipes(int maxVisiblePipes) {
        this.maxVisiblePipes = maxVisiblePipes;
    }

    public void add(Pipe pipe) {
        this.pipesCount++;

        this.pipes.add(pipe);
        if (pipes.size() >= maxVisiblePipes) {
            pipes.remove(0);
        }
    }

    public void createPipe(PipeSettings settings, int windowHeight) {
        int x = pipesCount * (settings.getWidth() + settings.getSpace());
        float bottomHeight = Maths.randomf(100, windowHeight - settings.getHoleHeight() - 100);
        float topY = bottomHeight + settings.getHoleHeight();
        float topHeight = windowHeight - topY;

        final Rectangle bottom = new Rectangle(
                x, 0, settings.getWidth(), bottomHeight);
        final Rectangle top = new Rectangle(
                x, topY, settings.getWidth(), topHeight);
        add(new Pipe(bottom, top));
    }

    public void clear() {
        this.pipes.clear();
        this.pipesCount = 0;
    }

    public Pipe getFirst() {
        return pipes.get(0);
    }

    public List<Pipe> getPipes() {
        return pipes;
    }

    @Override
    public void draw(Graphics g) {
        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }
    }
}
