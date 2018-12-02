package atomic;

import counter.Counter;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter implements Counter {
    private int threadsCount;
    private AtomicInteger count;

    public AtomicCounter() {
        this.count = new AtomicInteger(0);
    }

    @Override
    public int increment() throws InterruptedException {
        return this.count.incrementAndGet();
    }
}
