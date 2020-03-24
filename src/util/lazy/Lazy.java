package util.lazy;

import java.util.function.Supplier;

public class Lazy<T> implements Supplier<T> {

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

    public boolean isAssigned() {
        return assigned;
    }

    public T reassign() {
        this.assigned = true;
        return value = supplier.get();
    }

    public void forget() {
        this.assigned = false;
    }

    @Override
    public T get() {
        if (!isAssigned()) {
            reassign();
        }
        return value;
    }

    @Override
    public String toString() {
        return "[Lazy: " + get() + "]";
    }
}
