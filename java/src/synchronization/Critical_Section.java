package synchronization;

public class Critical_Section {

    public static void main(String[] args) throws InterruptedException {
        run();
    }

    static int count = 0;
    static Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            int i = 0;
            while (i++ < 10000) {
                countUp();
            }
        }
    });
    static Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            int i = 0;
            while (i++ < 10000) {
                countUp();
            }
        }
    });

    private static void countUp() {
        //critical section
        count = count + 1;
    }

    public static void run() throws InterruptedException {
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(count);
    }
}
