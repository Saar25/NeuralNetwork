package games.flappybird;

import math.Vector2;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class BirdPopulation implements Drawable {

    private final List<Bird> population = new LinkedList<>();
    private final List<Bird> survivors = new LinkedList<>();
    private final Score score = new Score();

    public void add(Bird bird) {
        this.population.add(bird);
    }

    public void clear() {
        this.population.clear();
    }

    public int count() {
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
            if (bird.getPosition().getY() + bird.getDimensions().getY() >= bounds.yMax() ||
                    bird.getPosition().getY() < bounds.yMin() || bird.isColliding(pipes)) {
                survivors.add(0, bird);
                died.add(bird);
            }
        }
        population.removeAll(died);
    }

    public double getCurrentX() {
        if (count() != 0) {
            final Bird bird = population.get(0);
            return bird.getPosition().getX();
        }
        return 0;
    }

    @Override
    public void draw(Graphics g) {
        for (Bird bird : population) {
            bird.draw(g);
        }
    }

    public void nextGeneration(GenerationSettings settings, Vector2 startingPosition) {
        getScore().reset();

        population.clear();
        final int mutationsPerBird = settings.getMutationsPerEach();
        for (int i = 0; i < settings.getSurvivorsCount(); i++) {
            final Bird bird = survivors.get(i);
            for (int j = 0; j < mutationsPerBird; j++) {
                final Bird mutated = bird.mutate(settings.getMutationRate());
                mutated.getPosition().set(startingPosition);
                System.out.println(mutated);
                population.add(mutated);
            }
        }
    }

    public Score getScore() {
        return score;
    }
}
