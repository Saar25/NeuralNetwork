package games.flappybird;

public class Score {

    private int score = 0;

    public void inc() {
        this.score++;
    }

    public int get() {
        return score;
    }
}
