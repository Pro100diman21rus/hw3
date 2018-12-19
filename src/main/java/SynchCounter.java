package synch;

import counter.Counter;

public class SynchCounter implements Counter {
    private int count;

    @Override
    public synchronized int increment() {

        return ++count;
    }
}
