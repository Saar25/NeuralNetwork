package util.floatnode;

import util.LazyFloat;
import util.Node;

import java.util.Collections;
import java.util.List;

public class LeafFloatNode implements FloatNode {

    private final LazyFloat value;

    public LeafFloatNode(LazyFloat value) {
        this.value = value;
    }

    @Override
    public List<? extends Node<Float>> getChildren() {
        return Collections.emptyList();
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
