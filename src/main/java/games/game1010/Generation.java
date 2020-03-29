package games.game1010;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Generation {

    private final List<EvaluatorPlayer> players;
    private final List<EvaluatorPlayer> deadPlayers;

    public Generation(List<EvaluatorPlayer> players) {
        this.players = players;
        this.deadPlayers = new ArrayList<>();
    }

    public void makeMove(Shape shape) {
        for (EvaluatorPlayer player : players) {
            player.place(shape);
        }
        final List<EvaluatorPlayer> newDead = new ArrayList<>();
        for (EvaluatorPlayer player : this.players) {
            if (player.isDead()) {
                newDead.add(player);
            }
        }
        this.deadPlayers.addAll(newDead);
        this.players.removeAll(newDead);
    }

    public boolean isAllDead() {
        return players.size() == 0;
    }

    public EvaluatorPlayer getBest() {
        return getBests(1).get(0);
    }

    public List<EvaluatorPlayer> getBests(int amount) {
        final List<EvaluatorPlayer> allPlayers = new ArrayList<>();
        allPlayers.addAll(this.deadPlayers);
        allPlayers.addAll(this.players);
        allPlayers.sort(Comparator.comparingInt(
                o -> o.getBoard().getPoints()));
        return allPlayers.subList(0, amount);
    }

}
