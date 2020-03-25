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
            for (int i = 0; i < getCells().length; i++) {
                for (int j = 0; j < getCells()[i].length; j++) {
                    if (board.)
                }
            }
        } else {
            return false;
        }
    }
}
