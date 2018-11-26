package counter;

import counter.Counter;

public class CounterImpl implements Counter {
    private int count = 0;

    public int increment() {
        return ++ this.count;
    }
}
