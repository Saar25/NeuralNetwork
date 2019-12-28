package util;

public class StopWatch {

    private long start;
    private long time;

    public StopWatch() {
        restart();
    }

    public long stop() {
        return time = time();
    }

    public void restart() {
        this.start = System.currentTimeMillis();
        this.time = -1;
    }

    public long time() {
        return time != -1 ? time : System.currentTimeMillis() - start;
    }

    public float timeInSeconds() {
        return time() / 1000f;
    }

}
