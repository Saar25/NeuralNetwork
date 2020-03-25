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

    public boolean draw(Board board, Position position) {
        if (board.exist(position) && board.exist(position.add(getCells().length, getCells()[0].length))) {
            for (int row = 0; row < getCells().length; row++) {
                for (int col = 0; col < getCells()[row].length; col++) {
                    final Position current = new Position(row, col);
                    if (board.hasCell())
                }
            }
        } else {
            return false;
        }
    }
}
