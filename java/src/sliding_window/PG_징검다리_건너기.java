package sliding_window;

import java.util.*;

public class PG_징검다리_건너기 {
    class Solution {
        //길이 k 구간의 최대값들 중 최소값을 찾아야 함.
        public int solution(int[] stones, int k) {
            ArrayDeque<int[]> deq = new ArrayDeque<>();
            int res = Integer.MAX_VALUE;
            for (int i = 0 ; i < stones.length ; i++) {
                if (!deq.isEmpty() && i - deq.peekFirst()[0] >= k) {
                    deq.pollFirst();
                }
                while (!deq.isEmpty() && deq.peekLast()[1] <= stones[i]) {
                    deq.pollLast();
                }
                deq.offer(new int[]{i, stones[i]});
                if (i + 1 >= k) {
                    res = Math.min(res, deq.peekFirst()[1]);
                }
            }
            return res;
        }
    }
}
