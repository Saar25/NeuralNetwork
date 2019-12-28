package games.flappybird;

import math.Vector2;
import neural.MatrixNeuralNetwork;
import neural.NeuralNetwork;

import java.awt.*;

public class Bird implements Drawable {

    private static final int jumpPower = 100;
    private static final int gravity = -100;
    private static final int xSpeed = 100;

    private final NeuralNetwork brain;

    private final Vector2 position;
    private final Vector2 dimensions;

    private final Vector2 velocity = Vector2.mutable(xSpeed, 0);

    public Bird(Vector2 position, Vector2 dimensions) {
        this(position, dimensions, new MatrixNeuralNetwork(4, 4, 2));
    }

    public Bird(Vector2 position, Vector2 dimensions, NeuralNetwork brain) {
        this.brain = brain;
        this.position = position;
        this.dimensions = dimensions;
    }

    public Bird mutate(float rate) {
        final Vector2 position = getPosition().copy();
        final Vector2 dimensions = getDimensions().copy();
        final NeuralNetwork mutatedBrain = brain.mutate(rate);
        return new Bird(position, dimensions, mutatedBrain);
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
        this.getPosition().add(velocity);
        this.velocity.div(interval);
    }

    public void addVelocity(float x) {
        this.velocity.add(x, 0);
    }

    public boolean isColliding(VisiblePipes pipes) {
        return pipes.isColliding(new Rectangle(getPosition(), getDimensions()));
    }

    public boolean isColliding(Rectangle bounds) {
        final Rectangle birdBounds = new Rectangle(
                getPosition(), getDimensions());
        return bounds.isColliding(birdBounds);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getDimensions() {
        return dimensions;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) getPosition().getX(), (int) getPosition().getY(),
                (int) getDimensions().getX(), (int) getDimensions().getY());
    }

    @Override
    public String toString() {
        return "Bird: \nBrain: " + brain.toString();
    }
}
