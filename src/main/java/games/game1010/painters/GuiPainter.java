package games.game1010.painters;

import games.game1010.Board;
import games.game1010.Cell;
import games.game1010.Position;
import games.gui.Renderer;
import games.gui.Window;

import java.awt.*;

public class GuiPainter implements BoardPainter {

    private final Renderer renderer;
    private final int cellSize;

    public GuiPainter(Window window, int cellSize) {
        this.cellSize = cellSize;
        this.renderer = new Renderer();
        window.add(renderer);
    }

    @Override
    public void paint(Board board) {
        renderer.add(g -> {
            final Color[] colors = {Color.WHITE, Color.BLACK};

            for (int row = 0; row < board.getSize(); row++) {
                for (int col = 0; col < board.getSize(); col++) {
                    final Position current = new Position(row, col);
                    final Cell cell = board.getCell(current);
                    final Color color = colors[cell == null ? 0 : 1];

                    paintCell(g, current, color);
                }
            }
        });
        renderer.repaint();
    }

    private void paintCell(Graphics g, Position position, Color color) {
        final int x = position.getCol() * (cellSize + 2) + cellSize;
        final int y = position.getRow() * (cellSize + 2) + cellSize;

        g.setColor(color);
        g.fillRoundRect(x, y, cellSize - 1, cellSize - 1, 10, 10);
    }
}
