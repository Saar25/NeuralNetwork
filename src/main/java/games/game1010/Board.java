package games.game1010;

import util.Maths;

public class Board {

    private final Cell[][] cells;
    private final int[] cellsRows;
    private final int[] cellsCols;

    public Board(int size) {
        this.cells = new Cell[size][size];
        this.cellsRows = new int[size];
        this.cellsCols = new int[size];
    }

    public boolean exist(Position position) {
        return Maths.isInside(position.getRow(), 0, getSize() - 1) &&
                Maths.isInside(position.getCol(), 0, getSize() - 1);
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

    public void placeCell(Position position) {
        if (!hasCell(position)) {
            setCell(position, new Cell());
            inc(position);
        } else {
            throw new IllegalStateException("There is already a cell in " + position);
        }
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public int getSize() {
        return getCells().length;
    }

    public void update() {
        for (int row = 0; row < getSize(); row++) {
            if (this.cellsRows[row] == getSize()) {
                clearRow(row);
            }
        }
        for (int col = 0; col < getSize(); col++) {
            if (this.cellsCols[col] == getSize()) {
                clearCol(col);
            }
        }
    }

    private void inc(Position position) {
        this.cellsRows[position.getRow()]++;
        this.cellsCols[position.getCol()]++;
    }

    private void clearRow(int row) {
        this.cellsRows[row] = 0;
        for (int col = 0; col < getSize(); col++) {
            getCells()[row][col] = null;
            this.cellsCols[col]--;
        }
    }

    private void clearCol(int col) {
        this.cellsCols[col] = 0;
        for (int row = 0; row < getSize(); row++) {
            getCells()[row][col] = null;
            this.cellsRows[row]--;
        }
    }
}
