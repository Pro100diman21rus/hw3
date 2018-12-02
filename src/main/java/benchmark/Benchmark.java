package benchmark;

import atomic.AtomicCounter;
import counter.Counter;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import semaphore.SemaphoreCounter;
import synch.SynchCounter;

import java.util.concurrent.TimeUnit;

public class Benchmark {
    private static final int THREAD_COUNT = 8;
    private static final int WARMUP_ITERATIONS = 10;
    private static final int MEASUREMENT_ITERATIONS = 70;

    @org.openjdk.jmh.annotations.Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("SemaphoreCounter")
    public void testSemaphoreCounter(CounterState state) throws InterruptedException {
        state.sempahoreCounter.increment();
    }

    @org.openjdk.jmh.annotations.Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("SynchCounter")
    public void testSynchCounter(CounterState state) throws InterruptedException {
        state.synchCounter.increment();
    }

    @org.openjdk.jmh.annotations.Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("AtomicCounter")
    public void testAtomicCounter(CounterState state) throws InterruptedException {
        state.atomicCounter.increment();
    }

    @State(Scope.Benchmark)
    public static class CounterState {
        Counter sempahoreCounter = new SemaphoreCounter();
        Counter synchCounter = new SynchCounter();
        Counter atomicCounter = new AtomicCounter();
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(Benchmark.class.getName())
                .warmupIterations(WARMUP_ITERATIONS)
                .measurementIterations(MEASUREMENT_ITERATIONS)
                .forks(1)
                .threads(THREAD_COUNT)
                .resultFormat(ResultFormatType.JSON)
                .result("Threads" + THREAD_COUNT + "_Warmups count=" + WARMUP_ITERATIONS + "_Measurements count=" + MEASUREMENT_ITERATIONS + "result.json")
                .build();
        new Runner(options).run();
    }
}
