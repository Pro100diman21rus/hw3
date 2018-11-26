package counter;

public class SynchCounter implements Counter {
    private int count = 0;

    public synchronized int increment() {
        return ++count;
    }
}