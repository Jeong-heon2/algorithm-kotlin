package synchronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock_ex {

    public static void main(String[] args) throws InterruptedException {
        run();
    }
    //재진입 가능한 lock
    static Lock lock = new ReentrantLock();
    static int count = 0;
    static Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            int i = 0;
            while (i++ < 1000000) {
                countUp();
            }
        }
    });
    static Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            int i = 0;
            while (i++ < 1000000) {
                countUp();
            }
        }
    });

    private static void countUp() {
        //critical section
        lock.lock();
        count = count + 1;
        lock.unlock();
    }

    public static void run() throws InterruptedException {
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(count);
    }
}
