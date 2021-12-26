package synchronization;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {
    enum State {
        THINKING, HUNGRY, EATING
    }
    public static void main(String[] args) {
        int numOfPhils = 5;
        DiningMonitor diningMonitor = new DiningMonitor(numOfPhils);
        for (int i = 0 ; i < numOfPhils; i++) {
            new Thread(new Philosophers(i, diningMonitor)).start();
        }
    }
    static class Philosophers implements Runnable{
        private final int id;
        private final DiningMonitor monitor;

        public Philosophers(int id, DiningMonitor monitor) {
            this.id = id;
            this.monitor = monitor;
        }

        @Override
        public void run() {
            while (true) {
                monitor.pickUp(id);
                monitor.putDown(id);
            }
        }

        private void think() {
            System.out.println(id + "Now I'm thinking...");
        }

        private void eat() {
            System.out.println(id + "Now I'm eating...");
        }
    }

    static class DiningMonitor {
        private final int numOfPhils;
        private final State[] states;
        private final Condition[] selfs;
        private final Lock lock;

        public DiningMonitor(int num) {
            this.numOfPhils = num;
            this.states = new State[num];
            this.selfs = new Condition[num];
            this.lock = new ReentrantLock();
            for (int i = 0 ; i < num; i++) {
                states[i] = State.THINKING;
                selfs[i] = lock.newCondition();
            }
        }

        private int leftOf(int i) {
            return (i + numOfPhils - 1) % numOfPhils;
        }

        private int rightOf(int i) {
            return (i + 1) % numOfPhils;
        }

        private void test(int i) {
            if (states[i] == State.HUNGRY) {
                if (states[leftOf(i)] != State.EATING && states[rightOf(i)] != State.EATING) {
                    states[i] = State.EATING;
                    selfs[i].signal();
                }
            }
        }

        public void pickUp(int i) {
            lock.lock();
            try {
                states[i] = State.HUNGRY;
                test(i);
                while (states[i] != State.EATING) {
                    selfs[i].await();
                    test(i);
                }
            } catch (InterruptedException e) {

            } finally {
                lock.unlock();
            }
        }

        public void putDown(int i) {
            lock.lock();
            try {
                states[i] = State.THINKING;
                test(leftOf(i));
                test(rightOf(i));
            } finally {
                lock.unlock();
            }
        }
    }

}
