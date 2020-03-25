package games.game1010;

import neural.NeuralNetwork;

public class BoardEvaluator {

    private final NeuralNetwork evaluator;

    public BoardEvaluator(NeuralNetwork evaluator) {
        this.evaluator = evaluator;
    }

    public float evaluate(Board board) {
        final float[] input = new float[board.getSize() * board.getSize()];
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                final Position current = new Position(row, col);
                final float hasCell = board.hasCell(current) ? 1 : 0;
                input[row * board.getSize() + col] = hasCell;
            }
        }
        return evaluator.feedForward(input)[0];
    }

}
