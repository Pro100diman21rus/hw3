package atomic;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@State(Scope.Benchmark)
public class AtomicMain {
    @Param({ "1", "2", "4" })
    private int threadsCount;
    private AtomicInteger counter;

    public AtomicMain() {
        this.counter = new AtomicInteger(0);
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void startCount() {
        IntStream.range(0, threadsCount)
                .forEach(i -> {
                    Runnable task = () -> {
                        System.out.println("Atomic Thread #" + i);
                        int count = counter.incrementAndGet();
                        System.out.println("Atomic Thread #" + i + " count is equal to " + count);
                    };
                    task.run();
                });
    }
}
