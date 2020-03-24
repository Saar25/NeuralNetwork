package util.lazy;

import util.FloatSupplier;

import java.util.function.Supplier;

public class LazyFloat implements Supplier<Float> {

    private final FloatSupplier supplier;
    private boolean assigned;
    private float value;

    public LazyFloat(FloatSupplier supplier) {
        this.supplier = supplier;
        this.assigned = false;
    }

    public LazyFloat(float value) {
        this.supplier = () -> value;
        this.assigned = true;
        this.value = value;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public float reassign() {
        this.assigned = true;
        return value = supplier.get();
    }

    public void forget() {
        assigned = false;
    }

    public float getValue() {
        if (!isAssigned()) {
            reassign();
        }
        return value;
    }

    @Override
    public Float get() {
        return getValue();
    }

    @Override
    public String toString() {
        return "[LazyFloat: " + get() + "]";
    }
}
