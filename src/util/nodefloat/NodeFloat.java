package util.nodefloat;

public interface NodeFloat {

    boolean isHigher(float value);

    boolean isLower(float value);

    NodeFloat getHighest();

    NodeFloat getLowest();

    NodeFloat getNext();

    float get();

}
