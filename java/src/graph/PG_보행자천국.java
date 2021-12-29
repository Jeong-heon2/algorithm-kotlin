package graph;

public class PG_보행자천국 {
    class Solution {
        int MOD = 20170805;
        int[][] map;
        int[] dx = {1, 0};
        int[] dy = {0, 1};
        int[][][] memo;
        public int solution(int m, int n, int[][] cityMap) {
            map = cityMap;
            memo = new int[m][n][2];
            for (int i = 0 ; i < m ; i++) {
                for (int j = 0 ; j < n ; j++) {
                    for (int k = 0 ; k <2 ; k++) {
                        memo[i][j][k] = -1;
                    }
                }
            }
            if (map[0][0] == 1) return 0;
            map[0][0] = 0;
            return dfs(m, n, new int[] {0, 0, 0});
        }

        private int dfs(int m, int n, int[] cur) {
            int y = cur[0], x = cur[1], dir = cur[2];
            if (y == m - 1 && x == n - 1) {
                return 1;
            }
            if (map[y][x] == 1) return 0;


            if (map[y][x] == 2) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if (ny < m && nx < n) {
                    if (memo[ny][nx][dir] != -1) return memo[ny][nx][dir];
                    else return dfs(m, n, new int[] {ny, nx, dir});
                } else {
                    return 0;
                }
            } else {
                int res = 0;
                for (int i = 0 ; i < dx.length; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];
                    if (ny < m && nx < n) {
                        if (memo[ny][nx][i] != -1) res = (res + memo[ny][nx][i]) % MOD;
                        else {
                            memo[ny][nx][i] = dfs(m, n, new int[] {ny, nx, i});
                            res = (res + memo[ny][nx][i]) % MOD;
                        }
                    }
                }
                return res;
            }
        }
    }
}
