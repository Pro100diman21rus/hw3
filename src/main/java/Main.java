import atomic.AtomicMain;
import semaphore.SemaphoreMain;
import synch.SynchMain;

public class Main {
    public static void main(String[] args) {
        SemaphoreMain semaphoreMain = new SemaphoreMain(4);
        AtomicMain atomicMain = new AtomicMain();
        SynchMain synchMain = new SynchMain(4);
        synchMain.startCount();
    }
}
