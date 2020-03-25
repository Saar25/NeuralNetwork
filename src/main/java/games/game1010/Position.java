package games.game1010;

public class Position {

    private final int row;
    private final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Position add(int x, int y) {
        return new Position(getRow() + x, getCol() + y);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
