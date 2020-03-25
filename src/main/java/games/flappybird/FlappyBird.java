package games.flappybird;

import games.gui.Rectangle;
import games.gui.Renderer;
import games.gui.Window;
import math.Vector2;
import util.Interval;

import javax.swing.*;
import java.awt.*;

public class FlappyBird {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private static final int X_OFFSET = 50;
    private static final int GROUND = 120;

    private static final GenerationSettings GENERATION_SETTINGS = new GenerationSettings(100, 10, 0.5f);
    private static final PipeSettings PIPE_SETTINGS = new PipeSettings(100, 500, 150, GROUND);
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

        this.renderer.setBackground(Color.CYAN);
        this.renderer.add(new ScoreDrawable(population.getScore(), WIDTH));
        this.renderer.add(new BirdsAliveDrawable(population, WIDTH));
        this.renderer.add(visiblePipes);
        this.renderer.add(new GroundDrawable(GROUND, WIDTH, HEIGHT));
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
        this.population.update(interval.seconds() * 10, visiblePipes.getFirst());

        final int currentX = (int) (population.getCurrentX() - X_OFFSET);
        this.population.filter(visiblePipes, new Rectangle(
                currentX, GROUND, currentX + WIDTH, HEIGHT - 100));

        if (population.count() == 0) {
            restart();
            population.nextGeneration(GENERATION_SETTINGS, STARTING_POSITION);
        }

        if (visiblePipes.getFirst().getBottom().xMax() < population.getCurrentX() - X_OFFSET) {
            visiblePipes.createPipe(PIPE_SETTINGS, HEIGHT);
            population.getScore().inc();
        }

        interval.restart();
    }

    private void repaint() {
        this.renderer.getTransform().setToTranslation(
                X_OFFSET - population.getCurrentX(), HEIGHT);
        this.renderer.getTransform().scale(1, -1);
        this.renderer.repaint();
    }
}
