package games.game1010;

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

    public boolean canBePlaced(Board board, Position position) {
        if (board.exist(position) && board.exist(position.add(getCells().length, getCells()[0].length))) {
            for (int row = 0; row < getCells().length; row++) {
                for (int col = 0; col < getCells()[row].length; col++) {
                    final Position current = position.add(row, col);
                    if (board.hasCell(current)) {
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
                final Cell cell = getCells()[row][col] ? new Cell() : null;
                final Position current = position.add(row, col);
                board.setCell(current, cell);
            }
        }
    }
}
