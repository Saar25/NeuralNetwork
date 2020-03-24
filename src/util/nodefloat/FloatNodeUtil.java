package util.nodefloat;

import java.util.Arrays;
import java.util.Collection;

public final class FloatNodeUtil {

    private FloatNodeUtil() {
        throw new AssertionError("Cannot create instance of " + getClass().getSimpleName());
    }

    public static FloatNode highest(FloatNode... nodes) {
        return Arrays.stream(nodes).reduce((a, b) ->
                a.get() > b.get() ? a : b).orElse(null);
    }

    public static FloatNode highest(Collection<FloatNode> nodes) {
        return nodes.stream().reduce((a, b) ->
                a.get() > b.get() ? a : b).orElse(null);
    }

    public static FloatNode lowest(FloatNode... nodes) {
        return Arrays.stream(nodes).reduce((a, b) ->
                a.get() < b.get() ? a : b).orElse(null);
    }

    public static FloatNode lowest(Collection<FloatNode> nodes) {
        return nodes.stream().reduce((a, b) ->
                a.get() < b.get() ? a : b).orElse(null);
    }
}
