package util.floatnode;

import util.Node;

import java.util.List;

public class NullFloatNode implements FloatNode {

    public static NullFloatNode instance = new NullFloatNode();

    private NullFloatNode() {

    }

    public static NullFloatNode getInstance() {
        return instance;
    }

    @Override
    public List<Node<Float>> getChildren() {
        throw new UnsupportedOperationException("Null node does not have children");
    }

    @Override
    public float get() {
        throw new UnsupportedOperationException("Null node does not have a value");
    }

    @Override
    public Float getValue() {
        throw new UnsupportedOperationException("Null node does not have a value");
    }
}
