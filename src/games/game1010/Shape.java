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
}
