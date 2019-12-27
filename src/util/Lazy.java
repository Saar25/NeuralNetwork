package util;

import java.util.function.Supplier;

public class Lazy<T> {

    private final Supplier<T> supplier;
    private boolean assigned;
    private T value;

    public Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
        this.assigned = false;
    }

    public Lazy(T value) {
        this.supplier = () -> value;
        this.assigned = true;
        this.value = value;
    }

    public T get() {
        if (!assigned) {
            assigned = true;
            value = supplier.get();
        }
        return value;
    }

    public T reassign() {
        assigned = true;
        return value = supplier.get();
    }

    public void forget() {
        assigned = false;
    }

    @Override
    public String toString() {
        return "[Lazy: " + get() + "]";
    }
}
