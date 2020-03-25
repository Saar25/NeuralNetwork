package games.game1010;

import games.game1010.painters.BoardPainter;
import games.game1010.painters.ConsolePainter;

import java.io.FileNotFoundException;
import java.util.List;

public class Game1010 {

    public static void main(String[] args) throws FileNotFoundException {
        final String path = "src/main/resources/shapes.json";
        final ShapesFile shapesFile = ShapesFile.read(path);
        final List<Shape> shapes = shapesFile.parse();

        final RandomShape randomShape = new RandomShape(shapes);
        final Board board = new Board(10);

        shapes.get(5).place(board, new Position(0, 0));
        shapes.get(5).place(board, new Position(3, 0));
        shapes.get(5).place(board, new Position(6, 0));
        shapes.get(2).place(board, new Position(9, 0));

        final BoardPainter painter = new ConsolePainter();

        painter.paint(board);
    }

}
