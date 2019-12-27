package util;

public class LazyFloat {

    private final Supplier supplier;
    private boolean assigned;
    private float value;

    public LazyFloat(Supplier supplier) {
        this.supplier = supplier;
        this.assigned = false;
    }

    public LazyFloat(float value) {
        this.supplier = () -> value;
        this.assigned = true;
        this.value = value;
    }

    public float get() {
        if (!assigned) {
            assigned = true;
            value = supplier.get();
        }
        return value;
    }

    public float reassign() {
        assigned = true;
        return value = supplier.get();
    }

    public void forget() {
        assigned = false;
    }

    @Override
    public String toString() {
        return "[LazyFloat: " + get() + "]";
    }

    public interface Supplier {
        float get();
    }
}
