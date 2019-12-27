package util.nodefloat;

import util.Lazy;

import java.util.ArrayList;
import java.util.List;

public class InternalNodeFloat implements NodeFloat {

    private final Lazy<NodeFloat> value;
    private final List<NodeFloat> nodes;

    private InternalNodeFloat(Lazy<NodeFloat> value, List<NodeFloat> nodes) {
        this.value = value;
        this.nodes = new ArrayList<>(nodes);
    }

    public static InternalNodeFloat maxOf(List<NodeFloat> nodes) {
        return new InternalNodeFloat(new Lazy<>(() -> highest(nodes)), nodes);
    }

    public static InternalNodeFloat minOf(List<NodeFloat> nodes) {
        return new InternalNodeFloat(new Lazy<>(() -> highest(nodes)), nodes);
    }

    private static NodeFloat highest(List<NodeFloat> nodes) {
        NodeFloat highest = nodes.get(0);
        for (NodeFloat node : nodes) {
            if (node.get() > highest.get()) {
                highest = node;
            }
        }
        return highest;
    }

    private static NodeFloat lowest(List<NodeFloat> nodes) {
        NodeFloat lowest = nodes.get(0);
        for (NodeFloat node : nodes) {
            if (node.get() < lowest.get()) {
                lowest = node;
            }
        }
        return lowest;
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
        return highest(nodes);
    }

    @Override
    public NodeFloat getLowest() {
        return lowest(nodes);
    }

    @Override
    public NodeFloat getNext() {
        return value.get();
    }

    @Override
    public float get() {
        return value.get().get();
    }

    @Override
    public String toString() {
        return "[InternalNodeFloat: " + get() + "]";
    }
}
