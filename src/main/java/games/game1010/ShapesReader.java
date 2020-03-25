package games.game1010;

import games.game1010.painters.BoardPainter;
import games.game1010.painters.ConsolePainter;

import java.io.FileNotFoundException;
import java.util.List;

public class ShapesReader {

    public static void main(String[] args) throws FileNotFoundException {
        final String path = "src/main/resources/shapes.json";
        final ShapesFile shapesFile = ShapesFile.read(path);
        final List<Shape> shapes = shapesFile.parse();

        final Board board = new Board(10);

        shapes.get(0).place(board, new Position(0, 0));
        shapes.get(1).place(board, new Position(1, 1));
        shapes.get(2).place(board, new Position(2, 2));
        shapes.get(4).place(board, new Position(2, 1));

        final BoardPainter painter = new ConsolePainter();
        painter.paint(board);
    }

}
