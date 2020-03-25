package games.game1010;

import java.util.List;

public class EvaluatorPlayer {

    private final Board board;
    private final BoardEvaluator evaluator;
    private boolean isDead = false;

    public EvaluatorPlayer(Board board, BoardEvaluator evaluator) {
        this.board = board;
        this.evaluator = evaluator;
    }

    public Board getBoard() {
        return board;
    }

    public BoardEvaluator getEvaluator() {
        return evaluator;
    }

    public boolean isDead() {
        return isDead;
    }

    public void place(Shape shape) {
        if (isDead()) {
            return;
        }

        final List<Position> placements = shape.getPossiblePlacements(board);

        if (placements.size() == 0) {
            this.isDead = true;
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
