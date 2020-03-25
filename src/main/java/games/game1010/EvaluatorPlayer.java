package games.game1010;

import java.util.List;

public class EvaluatorPlayer {

    private final Board board;
    private final BoardEvaluator evaluator;
    private boolean isAlive = true;

    public EvaluatorPlayer(Board board, BoardEvaluator evaluator) {
        this.board = board;
        this.evaluator = evaluator;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void place(Shape shape) {
        final List<Position> placements = shape.getPossiblePlacements(board);

        if (placements.size() == 0) {
            this.isAlive = false;
            return;
        }

        float bestEvaluation = 0;
        Position bestPlacement = null;

        for (Position placement : placements) {
            final Board temp = board.copy();
            shape.place(temp, placement);
            final float evaluation = evaluator.evaluate(temp);
            if (bestPlacement == null || evaluation > bestEvaluation) {
                bestEvaluation = evaluation;
                bestPlacement = placement;
            }
        }

        shape.place(board, bestPlacement);
    }
}
