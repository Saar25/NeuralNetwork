package games.game1010;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Shape {

    private final String name;
    private final boolean[][] cells;
    private final boolean rotatable;

    public Shape(String name, boolean[][] cells, boolean rotatable) {
        this.name = name;
        this.cells = cells;
        this.rotatable = rotatable;
    }

    public List<Shape> getRotatedVariations() {
        if (!rotatable) {
            // need same probability for the random shape
            return Arrays.asList(this, this, this, this);
        }

        final List<Shape> variations = new ArrayList<>(4);
        variations.add(this);

        boolean[][] cells = getCells();
        for (int i = 0; i < 3; i++) {
            cells = rotate(cells);
            variations.add(new Shape(name, cells, rotatable));
        }
        return variations;
    }

    private static boolean[][] rotate(boolean[][] matrix) {
        final int M = matrix.length;
        final int N = matrix[0].length;
        final boolean[][] rotated = new boolean[N][M];
        for (int row = 0; row < M; row++) {
            for (int col = 0; col < N; col++) {
                rotated[col][M - 1 - row] = matrix[row][col];
            }
        }
        return rotated;
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
