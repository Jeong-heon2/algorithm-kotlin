package synchronization;

public class BoundedBuffer {

    public static void main(String[] args) {
        CashBox cashBox = new CashBox(3);
        Thread[] pThreads = new Thread[2];
        Thread[] cThreads = new Thread[3];

        for (int i = 0 ; i < pThreads.length ; i++) {
            pThreads[i] = new Thread(new Producer(cashBox));
            pThreads[i].start();
        }

        for (int i = 0 ; i < cThreads.length ; i++) {
            cThreads[i] = new Thread(new Consumer(cashBox));
            cThreads[i].start();
        }
    }

    static class Producer implements Runnable {

        private CashBox cashBox;

        public Producer(CashBox cashBox) {
            this.cashBox = cashBox;
        }

        @Override
        public void run() {
            while (true) {
                int money = ((int)(1 + Math.random()*9))*10000;
                cashBox.push(money);
            }
        }
    }

    static class Consumer implements Runnable {

        private CashBox cashBox;

        public Consumer(CashBox cashBox) {
            this.cashBox = cashBox;
        }

        @Override
        public void run() {
            while (true) {
                cashBox.pop();
            }
        }
    }
}
