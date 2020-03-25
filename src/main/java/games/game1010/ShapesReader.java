package games.game1010;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

public class ShapesReader {

    public static void main(String[] args) throws FileNotFoundException {
        final Gson gson = new Gson();

        final File file = new File("shapes.json");
        System.out.println(file.exists());
        final Shape shape = gson.fromJson(new FileReader(file), Shape.class);

        System.out.println(Arrays.deepToString(shape.getCells()));
    }

}
