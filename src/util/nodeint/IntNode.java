package util.nodeint;

import util.LazyInt;
import util.Node;

public class IntNode implements Node<Integer> {

    private final LazyInt a;
    private final LazyInt b;

    public IntNode(LazyInt a, LazyInt b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Node<Integer> getNext() {
        return null;
    }

    @Override
    public Integer getValue() {
        return null;
    }

    public boolean isHigher(float value) {
        return a.get() > value || b.get() > value;
    }

    public boolean isLower(float value) {
        return a.get() < value || b.get() < value;
    }

    public float getMax() {
        return Math.max(a.get(), b.get());
    }

    public float getMin() {
        return Math.min(a.get(), b.get());
    }
}
