package semaphore;

import counter.*;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.Semaphore;

@State(Scope.Benchmark)
public class SemaphoreMain {
    private Semaphore semaphore;
    private Counter counter;
    @Param({ "1", "2", "4" })
    private int threadsCount;

    public SemaphoreMain() {
        this.semaphore = new Semaphore(1); //only one permission for atomic operation
        this.counter = new CounterImpl();
    }

    public SemaphoreMain(int threadsCount) {
        this.semaphore = new Semaphore(1); //only one permission for atomic operation
        this.counter = new CounterImpl();
        this.threadsCount = threadsCount;
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void startCount() {
        for (int i = 0; i < this.threadsCount; i++) {
            new SemaphoreThread("Semaphore Thread #" + i, this.semaphore, this.counter).run();
        }
    }
}
