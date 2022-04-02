package synchronization;

public class Critical_Section_Sol1 {
    public static void main(String[] args) throws InterruptedException {
        run();
    }

    static volatile int turn = 1;
    static int count = 0;

    static Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            int i = 0;
            while (i++ < 100000000) {
                countUp(1);
            }
        }
    });
    static Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            int i = 0;
            while (i++ < 100000000) {
                countUp(2);
            }
        }
    });

    static void countUp(int i) {
        while (turn != i) // 여기서 { } 이렇게 중괄호 넣는 거랑 안 넣는 거 차이
        //critical section
        count = count + 1;
        if (i == 1) turn = 2;
        else turn = 1;
    }

    public static void run() throws InterruptedException {
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(count);
    }
}
