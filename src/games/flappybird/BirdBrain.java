package games.flappybird;

import neural.NeuralNetwork;

public class BirdBrain {

    private final NeuralNetwork neuralNetwork;

    public BirdBrain(NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }

    public void act(Bird bird, Pipe closest) {
        final float[] inputs = {
                bird.getPosition().getY(),
                closest.getBottom().xMin() - bird.getPosition().getX(),
                closest.getBottom().yMax(),
                closest.getTop().yMin()
        };
        final float[] outputs = getNeuralNetwork().feedForward(inputs);

        if (outputs[0] > outputs[1]) {
            bird.jump();
        }
    }

    public BirdBrain mutate(float rate) {
        return new BirdBrain(getNeuralNetwork().mutate(rate));
    }

    private NeuralNetwork getNeuralNetwork() {
        return neuralNetwork;
    }
}
