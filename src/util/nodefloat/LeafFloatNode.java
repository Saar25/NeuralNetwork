package util.nodefloat;

import util.LazyFloat;

public class LeafFloatNode implements FloatNode {

    private final LazyFloat value;

    public LeafFloatNode(LazyFloat value) {
        this.value = value;
    }

    @Override
    public float compare(float value) {
        return get() - value;
    }

    @Override
    public FloatNode getHighest() {
        return NullFloatNode.getInstance();
    }

    @Override
    public FloatNode getLowest() {
        return NullFloatNode.getInstance();
    }

    @Override
    public FloatNode getNext() {
        return NullFloatNode.getInstance();
    }

    @Override
    public float get() {
        return value.get();
    }

    @Override
    public Float getValue() {
        return get();
    }

    @Override
    public String toString() {
        return "[LeafNodeFloat: " + get() + "]";
    }
}
