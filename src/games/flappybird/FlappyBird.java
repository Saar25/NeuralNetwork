package games.flappybird;

import math.Vector2;
import util.Interval;

import javax.swing.*;

public class FlappyBird {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private static final int X_OFFSET = 50;

    private static final GenerationSettings GENERATION_SETTINGS = new GenerationSettings(100, 10, .5f);
    private static final PipeSettings PIPE_SETTINGS = new PipeSettings(100, 500, 150);
    private static final Vector2 STARTING_POSITION = Vector2.mutable(X_OFFSET, HEIGHT / 2f);

    private final Window window = new Window("Flappy bird");
    private final Renderer renderer = new Renderer();

    private final Interval interval = new Interval();

    private final BirdPopulation population = new BirdPopulation();
    private final VisiblePipes visiblePipes = new VisiblePipes(4);

    public static void main(String[] args) {
        final FlappyBird game = new FlappyBird();
        game.initialize();

        new Timer(20, e -> {
            game.update();
            game.repaint();
        }).start();
    }

    private void initialize() {
        this.window.initialized(WIDTH, HEIGHT, true);
        this.window.add(renderer);

        this.renderer.add(visiblePipes);
        this.renderer.add(population);

        final Vector2 birdDimensions = Vector2.immutable(50, 50);
        for (int i = 0; i < GENERATION_SETTINGS.getPopulationSize(); i++) {
            final Vector2 birdPosition = STARTING_POSITION.copy();
            population.add(new Bird(birdPosition, birdDimensions));
        }

        restart();
    }

    private void restart() {
        visiblePipes.clear();
        visiblePipes.createPipe(PIPE_SETTINGS, HEIGHT);
        visiblePipes.createPipe(PIPE_SETTINGS, HEIGHT);
        visiblePipes.createPipe(PIPE_SETTINGS, HEIGHT);
        visiblePipes.createPipe(PIPE_SETTINGS, HEIGHT);

        interval.restart();
    }

    private void update() {
        this.population.update(interval.seconds(), visiblePipes.getFirst());

        final int currentX = (int) (population.getCurrentX() - X_OFFSET);
        this.population.filter(visiblePipes, new Rectangle(
                currentX, 0, currentX + WIDTH, HEIGHT));

        if (population.count() == 0) {
            restart();
            population.nextGeneration(GENERATION_SETTINGS, STARTING_POSITION);
        }

        if (visiblePipes.getFirst().getBottom().xMax() < 0) {
            visiblePipes.createPipe(PIPE_SETTINGS, HEIGHT);
        }

        interval.restart();
    }

    private void repaint() {
        this.renderer.getTransform().setToTranslation(
                X_OFFSET - population.getCurrentX(), 0);
        this.renderer.repaint();
    }
}
