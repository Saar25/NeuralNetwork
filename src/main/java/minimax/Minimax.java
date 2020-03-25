package minimax;

import util.Node;

public class Minimax<T extends MinimaxGame> {

    private final Evaluator<T> evaluator;
    private final Node<T> root;

    public Minimax(Evaluator<T> evaluator, Node<T> root) {
        this.evaluator = evaluator;
        this.root = root;
    }

    public Node<T> getRoot() {
        return root;
    }
}
