package games.game1010.painters;

import games.game1010.Board;
import games.game1010.Cell;
import games.game1010.Position;
import games.gui.Renderer;
import games.gui.Window;

public class GuiPainter implements BoardPainter {

    private final Window window;
    private final Renderer renderer;
    private final int cellSize;

    public GuiPainter(Window window, int cellSize) {
        this.window = window;
        this.cellSize = cellSize;

        this.renderer = new Renderer();
        this.window.add(renderer);
    }

    @Override
    public void paint(Board board) {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                final Position current = new Position(row, col);
                final Cell cell = board.getCell(current);
                System.out.print(cell == null ? "-" : "X");
            }
            System.out.println();
        }
    }
}
