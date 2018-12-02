package semaphore;

import counter.*;

import java.util.concurrent.Semaphore;

public class SemaphoreCounter implements Counter {
    private Semaphore semaphore;
    private int count = 0;

    public SemaphoreCounter() {
        this.semaphore = new Semaphore(1); //only one permission for atomic operation
    }

    @Override
    public int increment() {
        try {
            this.semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.count ++;
        this.semaphore.release();
        return this.count;
    }
}
