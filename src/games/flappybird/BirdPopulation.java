package games.flappybird;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class BirdPopulation implements Drawable {

    private final List<Bird> population = new LinkedList<>();

    public void add(Bird bird) {
        this.population.add(bird);
    }

    public void clear() {
        this.population.clear();
    }

    public int size() {
        return population.size();
    }

    public void update(float interval, Pipe closest) {
        for (Bird bird : this.population) {
            bird.update(interval, closest);
        }
    }

    public void filter(VisiblePipes pipes, Rectangle bounds) {
        final List<Bird> died = new LinkedList<>();
        for (Bird bird : population) {
            if (bird.getPosition().getY() > bounds.yMax()
                    || bird.getPosition().getY() < bounds.yMin()
                    || bird.isColliding(pipes)) {
                died.add(bird);
            }
        }
        population.removeAll(died);
    }

    public double getCurrentX() {
        final Bird bird = population.get(0);
        return bird.getPosition().getX();
    }

    @Override
    public void draw(Graphics g) {
        for (Bird bird : population) {
            bird.draw(g);
        }
    }
}
