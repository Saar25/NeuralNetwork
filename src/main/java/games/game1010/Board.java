package games.game1010;

public class Board {

    private final Cell[][] cells;

    public Board(int size) {
        this.cells = new Cell[size][size];
    }

    public boolean exist(Position position) {
        return
    }

    public void setCell(Position position, Cell cell) {
        this.cells[position.getX()][position.getY()] = cell;
    }

}
