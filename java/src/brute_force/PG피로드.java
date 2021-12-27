package brute_force;

public class PG피로드 {

    static class Solution {
        public int solution(int k, int[][] dungeons) {
            return dfs(0, dungeons, k, 0);
        }

        private int dfs(int visited, int[][] dungeons, int k, int cnt) {
            int res = cnt;
            for (int i = 0 ; i < dungeons.length; i++) {
                if ((visited & (1 << i)) == 0 && dungeons[i][0] <= k) {
                    res = Math.max(
                            res,
                            dfs(visited | (1 << i), dungeons, k - dungeons[i][1], cnt + 1)
                    );
                }
            }
            return res;
        }
    }
}
