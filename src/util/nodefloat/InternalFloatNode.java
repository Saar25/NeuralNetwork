package util.nodefloat;

import util.Lazy;

import java.util.ArrayList;
import java.util.List;

public class InternalFloatNode implements FloatNode {

    private final Lazy<FloatNode> value;
    private final List<FloatNode> nodes;

    private InternalFloatNode(Lazy<FloatNode> value, List<FloatNode> nodes) {
        this.value = value;
        this.nodes = new ArrayList<>(nodes);
    }

    public static InternalFloatNode maxOf(List<FloatNode> nodes) {
        return new InternalFloatNode(new Lazy<>(() -> highest(nodes)), nodes);
    }

    public static InternalFloatNode minOf(List<FloatNode> nodes) {
        return new InternalFloatNode(new Lazy<>(() -> highest(nodes)), nodes);
    }

    private static FloatNode highest(List<FloatNode> nodes) {
        FloatNode highest = nodes.get(0);
        for (FloatNode node : nodes) {
            if (node.get() > highest.get()) {
                highest = node;
            }
        }
        return highest;
    }

    private static FloatNode lowest(List<FloatNode> nodes) {
        FloatNode lowest = nodes.get(0);
        for (FloatNode node : nodes) {
            if (node.get() < lowest.get()) {
                lowest = node;
            }
        }
        return lowest;
    }

    @Override
    public float compare(float value) {
        return get() - value;
    }

    @Override
    public FloatNode getHighest() {
        return highest(nodes);
    }

    @Override
    public FloatNode getLowest() {
        return lowest(nodes);
    }

    @Override
    public FloatNode getNext() {
        return value.get();
    }

    @Override
    public float get() {
        return value.get().get();
    }

    @Override
    public Float getValue() {
        return value.get().getValue();
    }

    @Override
    public String toString() {
        return "[InternalNodeFloat: " + get() + "]";
    }
}
