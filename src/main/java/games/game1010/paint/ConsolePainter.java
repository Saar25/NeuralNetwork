package games.game1010.paint;

import games.game1010.Board;
import games.game1010.Cell;

public class ConsolePainter implements BoardPainter {

    @Override
    public void paint(Board board) {
        for (int row = 0; row < board.getCells().length; row++) {
            for (int col = 0; col < board.getCells()[row].length; col++) {
                final Cell cell = board.getCells()[row][col];
                System.out.print(cell == null ? "-" : "X");
            }
            System.out.println();
        }
    }
}
