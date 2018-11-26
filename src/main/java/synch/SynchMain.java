package synch;

import counter.Counter;
import counter.SynchCounter;
import org.openjdk.jmh.annotations.*;

import java.util.stream.IntStream;

@State(Scope.Benchmark)
public class SynchMain {
    private Counter counter;
    @Param({ "1", "2", "4" })
    private int threadsCount;

    public SynchMain() {
        this.counter = new SynchCounter();
    }

    public SynchMain(int threadsCount) {
        this.counter = new SynchCounter();
        this.threadsCount = threadsCount;
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void startCount() {
        IntStream.range(0, threadsCount)
                .forEach(i -> {
                    Runnable task = () -> {
                        System.out.println("Synch Thread #" + i);
                        int count = counter.increment();
                        System.out.println("Synch Thread #" + i + " count is equal to " + count);
                    };
                    task.run();
                });
    }
}
