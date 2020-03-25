package games.game1010;

import util.Maths;

public class Board {

    private final Cell[][] cells;

    public Board(int size) {
        this.cells = new Cell[size][size];
    }

    public boolean exist(Position position) {
        return Maths.isInside(position.getRow(), 0, getCells().length - 1) &&
                Maths.isInside(position.getCol(), 0, getCells()[0].length - 1);
    }

    public Cell getCell(Position position) {
        return getCells()[position.getRow()][position.getCol()];
    }

    public boolean hasCell(Position position) {
        return getCell(position) != null;
    }

    public void setCell(Position position, Cell cell) {
        getCells()[position.getRow()][position.getCol()] = cell;
    }

    public Cell[][] getCells() {
        return this.cells;
    }
}
