package neural;

public interface NeuralLayer {

    /**
     * Process the given inputs through the neural layer and return the output
     *
     * @param inputs the inputs values to process
     * @return the output
     */
    float[] process(float... inputs);

    /**
     * Adjust the layer using the inputs and the errors
     *
     * @param inputs  the inputs from the previous layer
     * @param outputs the outputs from the current layer
     * @param errors  the errors from the consecutive layer
     * @return the errors from this layer
     */
    float[] adjust(float[] inputs, float[] outputs, float[] errors);

    /**
     * Adjust the layer using the inputs and the errors
     *
     * @param inputs  the inputs from the previous layer
     * @param errors  the errors from the consecutive layer
     * @return the errors from this layer
     */
    default float[] adjust(float[] inputs, float[] errors) {
        return adjust(inputs, process(inputs), errors);
    }

    /**
     * Mutate the neural layer to a new neural layer
     *
     * @param rate the mutation rate
     * @return a mutated neural layer
     */
    NeuralLayer mutate(float rate);

    /**
     * Returns the amount of inputs the neural layer should receive
     *
     * @return the amount of inputs
     */
    int getInputs();

    /**
     * Returns the amount of outputs the neural layer would provide
     *
     * @return the amount of outputs
     */
    int getOutputs();

}
