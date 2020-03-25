package games.game1010;

import java.util.List;
import java.util.Random;

public class RandomShape {

    private final List<Shape> shapes;
    private final Random random = new Random();

    public RandomShape(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public Shape next() {
        return shapes.get(random.nextInt(shapes.size()));
    }
}
