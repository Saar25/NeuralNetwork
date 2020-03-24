package util.nodefloat;

import util.Lazy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class InternalFloatNode implements FloatNode {

    private final Lazy<FloatNode> value;
    private final List<FloatNode> nodes;

    private InternalFloatNode(Lazy<FloatNode> value, Collection<FloatNode> nodes) {
        this.value = value;
        this.nodes = new ArrayList<>(nodes);
    }

    private InternalFloatNode(Lazy<FloatNode> value, FloatNode... nodes) {
        this(value, Arrays.asList(nodes));
    }

    public static InternalFloatNode maxOf(List<FloatNode> nodes) {
        return new InternalFloatNode(new Lazy<>(() ->
                FloatNodeUtil.highest(nodes)), nodes);
    }

    public static InternalFloatNode minOf(List<FloatNode> nodes) {
        return new InternalFloatNode(new Lazy<>(() ->
                FloatNodeUtil.lowest(nodes)), nodes);
    }

    public static InternalFloatNode maxOf(FloatNode... nodes) {
        return new InternalFloatNode(new Lazy<>(() ->
                FloatNodeUtil.highest(nodes)), nodes);
    }

    public static InternalFloatNode minOf(FloatNode... nodes) {
        return new InternalFloatNode(new Lazy<>(() ->
                FloatNodeUtil.lowest(nodes)), nodes);
    }

    @Override
    public List<FloatNode> getChildren() {
        return nodes;
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
