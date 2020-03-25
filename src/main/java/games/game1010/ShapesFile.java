package games.game1010;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class ShapesFile {

    private List<ShapeModel> shapes;

    public static ShapesFile read(String path) throws FileNotFoundException {
        return new Gson().fromJson(new FileReader(path), ShapesFile.class);
    }

    public List<Shape> parse() {
        return shapes.stream().map(ShapeModel::parse)
                .collect(Collectors.toList());
    }

    public static class ShapeModel {
        private String name;
        private int[][] cells;

        public Shape parse() {
            final boolean[][] cells = new boolean[this.cells.length][this.cells[0].length];
            for (int i = 0; i < this.cells.length; i++) {
                for (int j = 0; j < this.cells[i].length; j++) {
                    cells[i][j] = this.cells[i][j] != 0;
                }
            }
            return new Shape(name, cells);
        }
    }
}
