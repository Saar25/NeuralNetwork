package util;

public class Progress {

    private final long startTime = System.currentTimeMillis();

    private int current = 0;
    private LazyInt percent;

    public Progress(int bound) {
        this(0, bound);
    }

    public Progress(int start, int bound) {
        this.percent = new LazyInt(() ->
                (int) (100f * (current - start) / (bound - start)));
    }

    public void inc() {
        current++;
    }

    public void add(int progress) {
        current += progress;
    }

    public boolean changed() {
        return percent.get() != percent.reassign();
    }

    public long timeTaken() {
        return System.currentTimeMillis() - startTime;
    }

    @Override
    public String toString() {
        return "Progress: " + percent.reassign() + "%";
    }
}
