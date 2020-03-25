package games.game1010;

import java.util.ArrayList;
import java.util.List;

public class Shape {

    private final String name;
    private final boolean[][] cells;

    public Shape(String name, boolean[][] cells) {
        this.name = name;
        this.cells = cells;
    }

    public String getName() {
        return name;
    }

    public boolean[][] getCells() {
        return cells;
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
        if (board.exist(position) && board.exist(position.add(getCells().length, getCells()[0].length))) {
            for (int row = 0; row < getCells().length; row++) {
                for (int col = 0; col < getCells()[row].length; col++) {
                    final Position current = position.add(row, col);
                    if (getCells()[row][col] && board.hasCell(current)) {
                        return false;
                    }
                }
            }
        } else {
            return false;
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
