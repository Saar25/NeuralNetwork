package util;

public class Interval {

    private long last;

    public void restart() {
        this.last = System.currentTimeMillis();
    }

    public long millis() {
        return System.currentTimeMillis() - last;
    }

    public float seconds() {
        return millis() / 1000f;
    }

}
