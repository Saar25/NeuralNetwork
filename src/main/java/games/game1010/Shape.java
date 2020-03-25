package games.game1010;

import java.util.ArrayList;
import java.util.List;

public class Shape {

    private final boolean[][] cells;

    public Shape(boolean[][] cells) {
        this.cells = cells;
    }

    private boolean[][] getCells() {
        return cells;
    }

    private int width() {
        return getCells().length;
    }

    private int height() {
        return getCells()[0].length;
    }

    public List<Position> getPossiblePlacements(Board board) {
        final List<Position> possiblePlacements = new ArrayList<>();
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                final Position current = new Position(row, col);
                if (canBePlaced(board, current)) {
                    possiblePlacements.add(current);
                }
            }
        }
        return possiblePlacements;
    }

    public boolean canBePlaced(Board board, Position position) {
        final Position endingPosition = position.add(width() - 1, height() - 1);
        if (!board.exist(position) || !board.exist(endingPosition)) {
            return false;
        }
        for (int row = 0; row < getCells().length; row++) {
            for (int col = 0; col < getCells()[row].length; col++) {
                final Position current = position.add(row, col);
                if (getCells()[row][col] && board.hasCell(current)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void place(Board board, Position position) {
        for (int row = 0; row < getCells().length; row++) {
            for (int col = 0; col < getCells()[row].length; col++) {
                if (getCells()[row][col]) {
                    board.placeCell(position.add(row, col));
                }
            }
        }
        board.update();
    }
}
