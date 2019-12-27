package util.nodeint;

import util.LazyInt;

public class NodeInt {

    private final LazyInt a;
    private final LazyInt b;

    public NodeInt(LazyInt a, LazyInt b) {
        this.a = a;
        this.b = b;
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
