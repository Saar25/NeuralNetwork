package neural;

public interface INeural {

    /**
     * Process the given inputs through the neural and return the output
     *
     * @param inputs the inputs values to process
     * @return the output
     */
    float process(float... inputs);

    /**
     * Adjust the neural using the inputs and the errors
     *
     * @param inputs the inputs from the previous neurals
     * @param output the output from the current neural
     * @param error  the error from the consecutive neurals
     * @return the error from this neural
     */
    float adjust(float[] inputs, float output, float error);

    default float adjustByError(float[] inputs, float error) {
        return adjust(inputs, process(inputs), error);
    }

    default float adjustByTarget(float[] inputs, float target) {
        final float output = process(inputs);
        return adjust(inputs, output, output - target);
    }

}
