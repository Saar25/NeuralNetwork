package util;

import java.util.function.IntSupplier;

public class LazyInt {

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

    public int get() {
        if (!assigned) {
            assigned = true;
            value = supplier.getAsInt();
        }
        return value;
    }

    public int reassign() {
        assigned = true;
        return value = supplier.getAsInt();
    }

    public void forget() {
        assigned = false;
    }

    @Override
    public String toString() {
        return "[LazyFloat: " + get() + "]";
    }
}