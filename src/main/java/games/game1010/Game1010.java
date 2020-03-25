package games.game1010;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Game1010 {

    public static void main(String[] args) throws FileNotFoundException {
        final String path = "src/main/resources/shapes.json";
        final ShapesFile shapesFile = ShapesFile.read(path);
        final List<Shape> shapes = shapesFile.parse();

        final RandomShape randomShape = new RandomShape(shapes);
        final List<EvaluatorPlayer> players = new ArrayList<>();

    }

}
