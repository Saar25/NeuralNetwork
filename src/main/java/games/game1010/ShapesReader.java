package games.game1010;

import java.io.FileNotFoundException;
import java.util.List;

public class ShapesReader {

    public static void main(String[] args) throws FileNotFoundException {
        final String path = "src/main/resources/shapes.json";
        final ShapesFile shapesFile = ShapesFile.read(path);
        final List<Shape> shapes = shapesFile.parse();


    }

}
