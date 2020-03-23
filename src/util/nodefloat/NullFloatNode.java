package util.nodefloat;

public class NullFloatNode implements FloatNode {

    public static NullFloatNode instance = new NullFloatNode();

    private NullFloatNode() {

    }

    public static NullFloatNode getInstance() {
        return instance;
    }

    @Override
    public float compare(float value) {
        throw new UnsupportedOperationException("Null node does not have a value");
    }

    @Override
    public FloatNode getHighest() {
        throw new UnsupportedOperationException("Null node does not have children");
    }

    @Override
    public FloatNode getLowest() {
        throw new UnsupportedOperationException("Null node does not have children");
    }

    @Override
    public FloatNode getNext() {
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
