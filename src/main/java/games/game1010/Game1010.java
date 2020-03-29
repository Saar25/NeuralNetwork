package games.game1010;

import games.game1010.painters.BoardPainter;
import games.game1010.painters.GuiPainter;
import games.gui.Window;
import neural.NeuralNetwork;
import neural.function.SigmoidFunction;
import neural.matrix.MatrixNeuralNetwork;
import neural.matrix.MatrixNeuralNetworkConfig;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Game1010 {

    private static final int BOARD_SIZE = 10;

    private static final int PLAYERS_COUNT = 100;

    private static final MatrixNeuralNetworkConfig CONFIG = new MatrixNeuralNetworkConfig()
            .setLayers(100, 10, 10, 1).setLearningRate(.5f)
            .setActivationFunction(new SigmoidFunction());

    public static void main(String[] args) throws FileNotFoundException {
        final String path = "src/main/resources/shapes.json";
        final ShapesFile shapesFile = ShapesFile.read(path);
        final List<Shape> baseShapes = shapesFile.parse();
        final List<Shape> shapes = addRotatedVariations(baseShapes);

        final RandomShape randomShape = new RandomShape(shapes);
        final List<EvaluatorPlayer> players = createPlayers();
        final Generation generation = new Generation(players);

        while (!generation.isAllDead()) {
            final Shape next = randomShape.next();
            generation.makeMove(next);
        }

        final int cellSize = 50;
        final Window window = new Window("1010");
        final int windowSize = cellSize * BOARD_SIZE + cellSize * 5 / 2;
        window.initialized(windowSize, windowSize + 20, false);

        final BoardPainter painter = new GuiPainter(window, cellSize);

        final EvaluatorPlayer bestPlayer = generation.getBest();
        painter.paint(bestPlayer.getBoard());
        System.out.println(bestPlayer.getBoard().getPoints());
    }

    private static List<Shape> addRotatedVariations(List<Shape> baseShapes) {
        final List<Shape> shapes = new ArrayList<>();
        for (Shape baseShape : baseShapes) {
            shapes.addAll(baseShape.getRotatedVariations());
        }
        return shapes;
    }

    private static List<EvaluatorPlayer> createPlayers() {
        final List<EvaluatorPlayer> players = new ArrayList<>();
        for (int i = 0; i < PLAYERS_COUNT; i++) {
            final Board board = new Board(BOARD_SIZE);
            final NeuralNetwork neuralNetwork = new MatrixNeuralNetwork(CONFIG);
            final BoardEvaluator evaluator = new BoardEvaluator(neuralNetwork);
            players.add(new EvaluatorPlayer(board, evaluator));
        }
        return players;
    }

}
