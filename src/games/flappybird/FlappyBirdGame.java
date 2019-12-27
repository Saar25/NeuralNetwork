package games.flappybird;

import math.Maths;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class FlappyBirdGame {

    private static final int population = 100;
    private static final int survivors = 10;
    private static final int mutations = population / survivors;
    private static final float mutationRate = .05f;

    private final FlappyBirdRenderer renderer;

    private final List<Pipe> pipes;
    private final List<Bird> birds;

    private final List<Bird> deadBirds = new LinkedList<>();

    private long last;
    private int pipesCount = 0;
    private int generation = 1;

    private Bird bestBird = null;
    private boolean onlyBest;

    private FlappyBirdGame() {
        this.renderer = new FlappyBirdRenderer();

        this.birds = new LinkedList<>();
        for (int i = 0; i < population; i++) {
            birds.add(new Bird(new Rectangle(50, 500, 50, 50)));
        }

        this.pipes = new LinkedList<>();
        addNextPipe();
        addNextPipe();
        addNextPipe();
        addNextPipe();

        last = System.currentTimeMillis();

        while (generation < 0) {
            update(1);
        }

        onlyBest = false;

        new Timer(20, e -> {
            long current = System.currentTimeMillis();
            float interval = (current - last) / 1000f;
            this.last = current;

            update(interval * 10);
            renderer.repaint();
        }).start();
    }

    public static void main(String[] args) {
        new FlappyBirdGame();
    }

    private void restart() {
        if (bestBird == null || bestBird.getScore() < deadBirds.get(0).getScore()) {
            bestBird = deadBirds.get(0);
        }
        System.out.println(deadBirds.get(0));
        birds.clear();
        if (!onlyBest) {
            for (int i = 0; i < survivors; i++) {
                final Bird bird = deadBirds.get(i);
                for (int j = 0; j < mutations; j++) {
                    birds.add(bird.mutate(new Rectangle(50, 500, 50, 50), mutationRate));
                }
            }
        } else {
            birds.add(bestBird.mutate(new Rectangle(50, 500, 50, 50), 0));
        }
        deadBirds.clear();
        pipes.clear();
        pipesCount = 0;
        addNextPipe();
        addNextPipe();
        addNextPipe();
        addNextPipe();

        System.out.println("=============");
        System.out.println("Generation " + ++generation);
        System.out.println("Best score " + bestBird.getScore());
        System.out.println("=============");
    }

    private void addNextPipe() {
        final float width = 100;
        final float space = 500;
        final float holeHeight = 150;

        float x = 500 + pipesCount * (width + space);
        float bottomHeight = Maths.randomf(100, renderer.jFrame.getHeight() - holeHeight - 100);
        float topY = bottomHeight + holeHeight;
        float topHeight = renderer.jFrame.getHeight() - topY;

        Rectangle bottom = new Rectangle(x, 0, width, bottomHeight);
        Rectangle top = new Rectangle(x, topY, width, topHeight);
        pipes.add(new Pipe(top, bottom));
        pipesCount++;
    }

    public void update(float interval) {
        for (Bird bird : birds) {
            bird.update(interval, pipes.get(0));
        }

        if (birds.isEmpty()) {
            restart();
            return;
        }

        final Rectangle pipe = pipes.get(0).getTop();
        if (pipe.getPosition().getX() + pipe.getDimension().getX() + 50 < birds.get(0).getPosition().getX()) {
            pipes.remove(0);
            addNextPipe();
            for (Bird bird : birds) {
                bird.addVelocity(1f);
                bird.incScore();
            }
        }

        renderer.setBirds(birds);
        renderer.setPipes(pipes);

        List<Bird> died = new LinkedList<>();
        for (Bird bird : birds) {
            if (bird.getPosition().getY() > renderer.jFrame.getHeight()
                    || bird.getPosition().getY() < 0
                    || bird.isColliding(pipes)) {
                died.add(bird);
                deadBirds.add(0, bird);
            }
        }
        birds.removeAll(died);

    }
}
