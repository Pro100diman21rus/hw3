package semaphore;

import counter.Counter;

import java.util.concurrent.Semaphore;

public class SemaphoreThread implements Runnable {
    private Semaphore semaphore;
    private String threadName;
    private Counter counter;

    SemaphoreThread(String threadName, Semaphore semaphore, Counter counter) {
        this.semaphore = semaphore;
        this.threadName = threadName;
        this.counter = counter;
    }

    public void run() {
        try {
            System.out.println(this.threadName + " waiting access...");
            this.semaphore.acquire();
            for(int i = 0; i < 5; i++) {
                /**
                 * this cycle is used to show that one thread increments counter
                 * others - are waiting access
                 */
                int count = this.counter.increment();
                System.out.println(this.threadName + " Counter is equal to " + count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.semaphore.release();
        }
    }
}