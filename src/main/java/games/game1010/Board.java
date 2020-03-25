package games.game1010;

import util.Maths;

public class Board {

    private final Cell[][] cells;
    private final int[] cellsRows;
    private final int[] cellsCols;

    private int points = 0;

    public Board(int size) {
        this.cells = new Cell[size][size];
        this.cellsRows = new int[size];
        this.cellsCols = new int[size];
    }

    public Board copy() {
        final Board board = new Board(getSize());
        for (int row = 0; row < getSize(); row++) {
            System.arraycopy(getCells()[row], 0, board.getCells()[row], 0, getSize());
        }
        System.arraycopy(this.cellsCols, 0, board.cellsCols, 0, getSize());
        System.arraycopy(this.cellsRows, 0, board.cellsRows, 0, getSize());
        return board;
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

    public void placeCell(Position position) {
        if (!hasCell(position)) {
            setCell(position, new Cell());
            inc(position);
            points++;
        } else {
            throw new IllegalStateException("There is already a cell in " + position);
        }
    }

    public int getSize() {
        return getCells().length;
    }

    public int getPoints() {
        return points;
    }

    private Cell[][] getCells() {
        return this.cells;
    }

    private void setCell(Position position, Cell cell) {
        getCells()[position.getRow()][position.getCol()] = cell;
    }

    public void update() {
        for (int row = 0; row < getSize(); row++) {
            if (this.cellsRows[row] == getSize()) {
                clearRow(row);
                points += getSize();
            }
        }
        for (int col = 0; col < getSize(); col++) {
            if (this.cellsCols[col] == getSize()) {
                clearCol(col);
                points += getSize();
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
