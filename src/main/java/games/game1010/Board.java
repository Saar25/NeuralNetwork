package games.game1010;

import util.Maths;

public class Board {

    private final Cell[][] cells;

    public Board(int size) {
        this.cells = new Cell[size][size];
    }

    public boolean exist(Position position) {
        return Maths.isInside(position.getX(), 0, cells.length - 1) &&
                Maths.isInside(position.getY(), 0, cells[0].length - 1);
    }

    public Cell getCell(Position position) {
        return this.cells[position.getX()][position.getY()];
    }

    public boolean hasCell(Position position) {
        return getCell(position) != null;
    }

    public void setCell(Position position, Cell cell) {
        this.cells[position.getX()][position.getY()] = cell;
    }

}
