package util.lazy;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class LazyInt implements Supplier<Integer> {

    private final IntSupplier supplier;
    private boolean assigned;
    private int value;

    public LazyInt(IntSupplier supplier) {
        this.supplier = supplier;
        this.assigned = false;
    }

    public LazyInt(int value) {
        this.supplier = () -> value;
        this.assigned = true;
        this.value = value;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public int reassign() {
        this.assigned = true;
        return value = supplier.getAsInt();
    }

    public void forget() {
        this.assigned = false;
    }

    public int getValue() {
        if (!isAssigned()) {
            reassign();
        }
        return value;
    }

    @Override
    public Integer get() {
        return getValue();
    }

    @Override
    public String toString() {
        return "[LazyFloat: " + get() + "]";
    }
}