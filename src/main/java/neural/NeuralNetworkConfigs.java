package neural;

import kotlin.jvm.functions.Function1;
import math.Maths;

public class NeuralNetworkConfigs {

    public final Function1<Float, Float> activation;
    public final Function1<Float, Float> derivative;
    public final Function1<Float, Float> inputActivation;
    public final Function1<Float, Float> outputActivation;
    public final float learningRate;

    public NeuralNetworkConfigs(Function1<Float, Float> activation, Function1<Float, Float> derivative,
                                Function1<Float, Float> inputActivation, Function1<Float, Float> outputActivation,
                                float learningRate) {
        this.activation = activation;
        this.derivative = derivative;
        this.inputActivation = inputActivation;
        this.outputActivation = outputActivation;
        this.learningRate = learningRate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Function1<Float, Float> activation = Maths.sigmoid;
        private Function1<Float, Float> derivative = Maths.dsigmoid;
        private Function1<Float, Float> inputActivation = f -> f;
        private Function1<Float, Float> outputActivation = f -> f;
        private float learningRate = .1f;

        public Builder setActivation(Function1<Float, Float> activation) {
            this.activation = activation;
            return this;
        }

        public Builder setDerivative(Function1<Float, Float> derivative) {
            this.derivative = derivative;
            return this;
        }

        public Builder setInputActivation(Function1<Float, Float> inputActivation) {
            this.inputActivation = inputActivation;
            return this;
        }

        public Builder setOutputActivation(Function1<Float, Float> outputActivation) {
            this.outputActivation = outputActivation;
            return this;
        }

        public Builder setLearningRate(float learningRate) {
            this.learningRate = learningRate;
            return this;
        }

        public NeuralNetworkConfigs createNeuralNetworkConfigs() {
            return new NeuralNetworkConfigs(activation, derivative, inputActivation, outputActivation, learningRate);
        }
    }
}
