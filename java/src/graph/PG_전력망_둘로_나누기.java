package graph;

import java.util.ArrayList;

public class PG_전력망_둘로_나누기 {

    static class Solution {
        static ArrayList<Integer>[] arr;
        static int[] cnt;
        static int N;
        static int ans;

        public int solution(int n, int[][] wires) {
            ans = n;
            N = n;
            cnt = new int[n+1];
            arr = new ArrayList[n+1];
            for (int i = 1 ; i <= n; i++) {
                arr[i] = new ArrayList<>();
            }
            for (int[] wire : wires) {
                arr[wire[0]].add(wire[1]);
                arr[wire[1]].add(wire[0]);
            }
            count(1, 0);
            return ans;
        }

        private int count(int cur, int parent) {
            cnt[cur] = 1;
            for (int child : arr[cur]) {
                if (child != parent) {
                    cnt[cur] += count(child, cur);
                }
            }
            ans = Math.min(ans, Math.abs(N - cnt[cur] - cnt[cur]));
            return cnt[cur];
        }
    }
}
