package synchronization;

public class CashBox {

    int[] buffer;
    private int cnt, in, out;

    public CashBox(int bufferSize) {
        buffer = new int[bufferSize];
        cnt = in = out = 0;
    }

    synchronized public void push(int money) {
        // 가득찼다면,,
        while (cnt == buffer.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                // what to do?..
            }
        }
        buffer[in] = money;
        in = (in + 1) % buffer.length;
        cnt++;
        System.out.printf("입금 : %d원\n", money);
        notify();
    }

    synchronized public int pop() {
        // 비어있다면 ..
        while (cnt == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                // what to do?..
            }
        }
        int money = buffer[out];
        out = (out + 1) % buffer.length;
        cnt--;
        System.out.printf("출금 : %d원\n", money);
        notify();
        return money;
    }
}
