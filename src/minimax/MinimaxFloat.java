package minimax;

import util.LazyFloat;
import util.nodefloat.InternalFloatNode;
import util.nodefloat.LeafFloatNode;
import util.nodefloat.FloatNode;

import java.util.Arrays;

public class MinimaxFloat {

    private static float[] values = {2, -1, 3, 5, -2, 1, 5, 1};
    private static int index = 0;

    private FloatNode head;

    public MinimaxFloat(int levels) {
        this.head = createNode(levels, true);
    }

    private FloatNode createNode(int levels, boolean max) {
        if (levels == 0) {
            return new LeafFloatNode(new LazyFloat(values[index++]));
        }
        final FloatNode n1 = createNode(levels - 1, !max);
        final FloatNode n2 = createNode(levels - 1, !max);
        return max ? InternalFloatNode.maxOf(Arrays.asList(n1, n2))
                : InternalFloatNode.minOf(Arrays.asList(n1, n2));
    }

    @Override
    public String toString() {
        return "[MinimaxFloat Head: " + head + "]";
    }
}
