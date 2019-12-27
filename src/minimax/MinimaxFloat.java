package minimax;

import util.LazyFloat;
import util.nodefloat.InternalNodeFloat;
import util.nodefloat.LeafNodeFloat;
import util.nodefloat.NodeFloat;

import java.util.Arrays;

public class MinimaxFloat {

    private static float[] values = {2, -1, 3, 5, -2, 1, 5, 1};
    private static int index = 0;

    private NodeFloat head;

    public MinimaxFloat(int levels) {
        this.head = createNode(levels, true);
    }

    private NodeFloat createNode(int levels, boolean max) {
        if (levels == 0) {
            return new LeafNodeFloat(new LazyFloat(values[index++]));
        }
        final NodeFloat n1 = createNode(levels - 1, !max);
        final NodeFloat n2 = createNode(levels - 1, !max);
        return max ? InternalNodeFloat.maxOf(Arrays.asList(n1, n2))
                : InternalNodeFloat.minOf(Arrays.asList(n1, n2));
    }

    public void setMax() {
        head = head.getHighest();
    }

    public void setMin() {
        head = head.getLowest();
    }

    @Override
    public String toString() {
        return "[MinimaxFloat Head: " + head + "]";
    }
}
