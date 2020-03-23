package util.nodefloat;

import util.Node;

public interface FloatNode extends Node<Float> {

    float compare(float value);

    FloatNode getHighest();

    FloatNode getLowest();

    FloatNode getNext();

    float get();
}
