package games.flappybird;

import math.Maths;
import math.Vector2;
import util.Interval;

import javax.swing.*;

public class FlappyBird {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;

    private static final int survivorsCount = 10;
    private static final int populationSize = 100;
    private static final int mutations = populationSize / survivorsCount;
    private static final float mutationRate = .05f;

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
        for (int i = 0; i < populationSize; i++) {
            final Vector2 birdPosition = Vector2.mutable(50, 500);
            population.add(new Bird(birdPosition, birdDimensions));
        }

        final float width = 100;
        final float space = 500;
        final float holeHeight = 150;

        float x = 500 + (width + space) / 2;
        float bottomHeight = Maths.randomf(100, HEIGHT - holeHeight - 100);
        float topY = bottomHeight + holeHeight;
        float topHeight = HEIGHT - topY;

        Rectangle bottom = new Rectangle(x, 0, width, bottomHeight);
        Rectangle top = new Rectangle(x, topY, width, topHeight);
        visiblePipes.add(new Pipe(top, bottom));

        interval.restart();
    }

    private void update() {
        this.population.update(interval.seconds(), visiblePipes.getFirst());
        this.population.filter(visiblePipes, new Rectangle(0, 0, WIDTH, HEIGHT));
        interval.restart();
    }

    private void repaint() {
        this.renderer.getTransform().setToTranslation(50 - population.getCurrentX(), 0);
        this.renderer.repaint();
    }
}
