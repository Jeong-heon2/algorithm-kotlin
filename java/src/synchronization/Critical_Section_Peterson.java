package synchronization;

public class Critical_Section_Peterson {
    public static void main(String[] args) throws InterruptedException {
        run();
    }

    static int count = 0;
    static volatile boolean[] flag = new boolean[3];
    static volatile int turn = 1;
    static Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            int i = 0;
            while (i++ < 1000000) {
                // 나 진입 할 거야
                flag[1] = true;
                // 근데 너 부터 들어가
                turn = 2;
                // 너 c-s 에 있으면 기다릴게
                while (flag[2] && turn == 2);
                //critical section
                count = count + 1;
                // 나 빠져 나왔어 , 너 들어가
                flag[1] = false;
            }
        }
    });
    static Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            int i = 0;
            while (i++ < 1000000) {
                flag[2] = true;
                turn = 1;
                while (flag[1] && turn == 1);
                //critical section
                count = count + 1;
                flag[2] = false;
            }
        }
    });

    public static void run() throws InterruptedException {
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(count);
    }
}
