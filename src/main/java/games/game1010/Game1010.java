package games.game1010;

import games.game1010.painters.BoardPainter;
import games.game1010.painters.ConsolePainter;
import neural.matrix.MatrixNeuralNetwork;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Game1010 {

    public static void main(String[] args) throws FileNotFoundException {
        final String path = "src/main/resources/shapes.json";
        final ShapesFile shapesFile = ShapesFile.read(path);
        final List<Shape> shapes = shapesFile.parse();

        final RandomShape randomShape = new RandomShape(shapes);
        final List<EvaluatorPlayer> players = createPlayers(200);
        final List<EvaluatorPlayer> deadPlayers = new ArrayList<>();

        while (!players.isEmpty()) {
            final Shape next = randomShape.next();
            for (EvaluatorPlayer player : players) {
                player.place(next);
            }
            for (EvaluatorPlayer player : players) {
                if (player.isDead()) {
                    deadPlayers.add(player);
                }
            }
            players.removeIf(EvaluatorPlayer::isDead);
        }


        final EvaluatorPlayer bestPlayer = deadPlayers.stream().reduce((p1, p2) ->
                p1.getBoard().getPoints() > p2.getBoard().getPoints() ? p1 : p1).orElse(null);
        assert bestPlayer != null;

        final BoardPainter painter = new ConsolePainter();
        painter.paint(bestPlayer.getBoard());
        System.out.println(bestPlayer.getBoard().getPoints());
    }

    private static List<EvaluatorPlayer> createPlayers(int playerCount) {
        final List<EvaluatorPlayer> players = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            final Board board = new Board(10);
            final BoardEvaluator evaluator = new BoardEvaluator(
                    new MatrixNeuralNetwork(100, 10, 10, 1));
            players.add(new EvaluatorPlayer(board, evaluator));
        }
        return players;
    }

}
