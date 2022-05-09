package playground.ki2022;

import java.util.*;
public class Q2 {
    class Solution {
        public int solution(int[] queue1, int[] queue2) {
            Queue<Integer> q1 = new LinkedList<>();
            Queue<Integer> q2 = new LinkedList<>();

            long q1Sum = 0l;
            for (int i : queue1) {
                q1Sum += i;
                q1.offer(i);
            }

            long q2Sum = 0l;
            for (int j : queue2) {
                q2Sum += j;
                q2.offer(j);
            }

            int cnt = 0;
            while(q1Sum != q2Sum) {
                if (cnt > 300000) return -1;
                if (q1Sum > q2Sum) {
                    int poll = q1.poll();
                    q1Sum -= poll;
                    q2Sum += poll;
                    q2.offer(poll);
                } else {
                    int poll = q2.poll();
                    q2Sum -= poll;
                    q1Sum += poll;
                    q1.offer(poll);
                }
                cnt++;
            }


            return cnt;
        }
    }
}
