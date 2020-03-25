package games.game1010;

public class Position {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position add(int x, int y) {
        return new Position(getX() + x, getY() + y);
    }a

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
