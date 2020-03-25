package neural;

public interface NeuralNetwork {

    /**
     * Append a layer to the neural network
     *
     * @param neurals the number of nodes of the layer
     */
    void addLayer(int neurals);

    /**
     * Feed forward the given inputs and process it
     *
     * @param inputs the inputs
     * @return the processed output
     */
    float[] feedForward(float... inputs);

    /**
     * Backpropagate the neural network based of the inputs and the targets
     *
     * @param inputs the inputs
     * @param targets the expected targets
     */
    void backpropagate(float[] inputs, float... targets);

    /**
     * Mutate the neural network to a new neural network
     *
     * @param rate the mutation rate
     * @return a mutated neural network
     */
    NeuralNetwork mutate(float rate);

}