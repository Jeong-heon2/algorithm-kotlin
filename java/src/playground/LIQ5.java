package playground;

import java.util.concurrent.locks.ReentrantLock;

public class LIQ5 {

    public static void main(String[] args) {
        new Solution().solution("acc1", "acc2", 10);
    }

    static class Solution {
        public boolean solution(String account1, String account2, int count) {
            java.util.List<Thread> threads = new java.util.ArrayList<>();
            for (int i=0; i<count; i++) {
                final int condition = i % 2;
                Thread thread = new Thread(() -> {
                    if (condition == 0) {
                        transfer(account1, account2, 1);
                    } else {
                        transfer(account2, account1, 1);
                    }
                });
                threads.add(thread);
                thread.start();
            }
            threads.forEach(thread -> {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            return true;
        }

        public void transfer(String fromAccount, String toAccount, int money) {
            int fromHash = fromAccount.hashCode();
            int toHash = toAccount.hashCode();
            String account1 = fromAccount;
            String account2 = toAccount;
            if (fromHash > toHash) {
                synchronized(account1) {
                    synchronized(account2) {
                        String message = String.format("Transfer %d from %s to %s", money, account1, account2);
                        System.out.println(message);
                    }
                }
            } else { synchronized(account2) { synchronized(account1) {
                String message = String.format("Transfer %d from %s to %s", money, account1, account2);
                System.out.println(message);
            } } }
        }
    }
}
