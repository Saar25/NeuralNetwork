package games.flappybird;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class VisiblePipes implements Drawable {

    private final int maxVisiblePipes;
    private final List<Pipe> pipes = new LinkedList<>();

    public VisiblePipes(int maxVisiblePipes) {
        this.maxVisiblePipes = maxVisiblePipes;
    }

    public Pipe add(Pipe pipe) {
        this.pipes.add(pipe);
        if (pipes.size() >= maxVisiblePipes) {
            return pipes.remove(0);
        }
        return null;
    }

    public void clear() {
        this.pipes.clear();
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
