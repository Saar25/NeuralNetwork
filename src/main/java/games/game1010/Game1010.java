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

    private static final float LEARNING_RATE = .5f;

    private static final MatrixNeuralNetworkConfig CONFIG = new MatrixNeuralNetworkConfig()
            .setLayers(100, 10, 10, 1).setLearningRate(.5f)
            .setActivationFunction(new SigmoidFunction());

    public static void main(String[] args) throws FileNotFoundException {
        final String path = "src/main/resources/shapes.json";
        final ShapesFile shapesFile = ShapesFile.read(path);
        final List<Shape> baseShapes = shapesFile.parse();
        final List<Shape> shapes = addRotatedVariations(baseShapes);

        final int cellSize = 50;
        final Window window = new Window("1010");
        final int windowSize = cellSize * BOARD_SIZE + cellSize * 5 / 2;
        window.initialized(windowSize, windowSize + 20, false);
        final BoardPainter painter = new GuiPainter(window, cellSize);

        final RandomShape randomShape = new RandomShape(shapes);

        List<EvaluatorPlayer> players = createPlayers();
        Generation generation = new Generation(players);

        while (true) {
            runGeneration(generation, randomShape);

            final EvaluatorPlayer bestPlayer = generation.getBest();
            painter.paint(bestPlayer.getBoard());
            System.out.println(bestPlayer.getBoard().getPoints());

            players = generation.getBests(PLAYERS_COUNT / 10);
            players = addMutations(players, 10);
            generation = new Generation(players);
        }
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

    private static void runGeneration(Generation generation, RandomShape randomShape) {
        while (!generation.isAllDead()) {
            final Shape next = randomShape.next();
            generation.makeMove(next);
        }
    }

    private static List<EvaluatorPlayer> addMutations(List<EvaluatorPlayer> players, int mutationsFromEach) {
        final List<EvaluatorPlayer> mutations = new ArrayList<>();
        for (EvaluatorPlayer player : players) {
            for (int i = 0; i < mutationsFromEach; i++) {
                final BoardEvaluator evaluator = player.getEvaluator().mutate(LEARNING_RATE);
                mutations.add(new EvaluatorPlayer(new Board(BOARD_SIZE), evaluator));
            }
        }
        return mutations;
    }

}
