package games.game1010;

import games.game1010.painters.BoardPainter;
import games.game1010.painters.ConsolePainter;
import neural.MatrixNeuralNetwork;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Game1010 {

    public static void main(String[] args) throws FileNotFoundException {
        final String path = "src/main/resources/shapes.json";
        final ShapesFile shapesFile = ShapesFile.read(path);
        final List<Shape> shapes = shapesFile.parse();

        final RandomShape randomShape = new RandomShape(shapes);
        final List<EvaluatorPlayer> players = createPlayers(50);

        EvaluatorPlayer lastPlayer = players.get(0);
        while (players.size() > 1) {
            final Shape next = randomShape.next();
            for (EvaluatorPlayer player : players) {
                player.place(next);
            }

            lastPlayer = players.get(0);
            players.removeIf(EvaluatorPlayer::isDead);
        }

        System.out.println(lastPlayer.getBoard().getPoints());
        final BoardPainter painter = new ConsolePainter();
        painter.paint(lastPlayer.getBoard());
    }

    private static List<EvaluatorPlayer> createPlayers(int playerCount) {
        final List<EvaluatorPlayer> players = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            final Board board = new Board(10);
            final BoardEvaluator evaluator = new BoardEvaluator(
                    new MatrixNeuralNetwork(100, 50, 50, 1));
            players.add(new EvaluatorPlayer(board, evaluator));
        }
        return players;
    }

}
