package games.flappybird;

import math.Vector2;
import neural.MatrixNeuralNetwork;
import neural.NeuralNetwork;

import java.awt.*;
import java.util.List;

public class Bird implements Drawable {

    private static final int jumpPower = 30;
    private static final int gravity = -10;
    private static final int xSpeed = 10;

    private final NeuralNetwork brain;

    private final Rectangle bounds;
    private final Vector2 velocity;

    private int score = 0;

    public Bird(Rectangle bounds) {
        this.bounds = bounds;
        this.brain = new MatrixNeuralNetwork(4, 4, 2);
        this.velocity = Vector2.mutable(xSpeed, 0);
    }

    public Bird(Rectangle bounds, NeuralNetwork brain) {
        this.brain = brain;
        this.bounds = bounds;
        this.velocity = Vector2.mutable(xSpeed, 0);
    }

    public Bird mutate(Rectangle bounds, float rate) {
        return new Bird(bounds, brain.mutate(rate));
    }

    public void jump() {
        velocity.setY(jumpPower);
    }

    public void update(float interval, Pipe pipe) {
        float[] inputs = {
                getPosition().getY(),
                pipe.getBottom().xMin() - getPosition().getX(),
                pipe.getBottom().yMax(),
                pipe.getTop().yMin(),
        };
        float[] outputs = brain.feedForward(inputs);
        if (outputs[0] > outputs[1]) {
            jump();
        }

        this.velocity.add(0, gravity * interval).mul(interval);
        this.bounds.getPosition().add(velocity);
        this.velocity.div(interval);
    }

    public void addVelocity(float x) {
        this.velocity.add(x, 0);
    }

    public boolean isColliding(List<Pipe> pipes) {
        for (Pipe pipe : pipes) {
            if (pipe.isColliding(bounds)) {
                return true;
            }
        }
        return false;
    }

    public void incScore() {
        this.score++;
    }

    public int getScore() {
        return score;
    }

    public Vector2 getPosition() {
        return bounds.getPosition();
    }

    @Override
    public void draw(Graphics g) {
        bounds.draw(g);
    }

    @Override
    public String toString() {
        return "Bird: Score: " + score + ", \nBrain: " + brain.toString();
    }
}
