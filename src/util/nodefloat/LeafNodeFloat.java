package util.nodefloat;

import util.LazyFloat;

public class LeafNodeFloat implements NodeFloat {

    private final LazyFloat value;

    public LeafNodeFloat(LazyFloat value) {
        this.value = value;
    }

    @Override
    public boolean isHigher(float value) {
        return get() > value;
    }

    @Override
    public boolean isLower(float value) {
        return get() < value;
    }

    @Override
    public NodeFloat getHighest() {
        throw new UnsupportedOperationException("Leaf Node does not have children");
    }

    @Override
    public NodeFloat getLowest() {
        throw new UnsupportedOperationException("Leaf Node does not have children");
    }

    @Override
    public NodeFloat getNext() {
        throw new UnsupportedOperationException("Leaf Node does not have children");
    }

    @Override
    public float get() {
        return value.get();
    }

    @Override
    public String toString() {
        return "[LeafNodeFloat: " + get() + "]";
    }
}
