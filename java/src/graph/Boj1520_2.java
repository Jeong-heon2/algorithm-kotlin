package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1520_2 {
    static int N;
    static int M;
    static int[][] graph;
    static int[][] dp;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        graph = new int[M][N];
        dp = new int[M][N];
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(dfsRecursion(0, 0));
    }

    private static int dfsRecursion(int curY, int curX) {
        if (curX == N-1 && curY == M-1) {
            return 1;
        }

        if (dp[curY][curX] > -1) return dp[curY][curX];
        int dpTemp = 0;

        for (int i = 0; i < 4; i++) {
            int nY = curY + dy[i];
            int nX = curX + dx[i];
            if (isRanged(nY, nX) && graph[nY][nX] < graph[curY][curX]) {
                dpTemp += dfsRecursion(nY, nX);
            }
        }

        return dp[curY][curX] = dpTemp;
    }

    private static boolean isRanged(int y, int x) {
        return y >= 0 && y < M && x >= 0 && x < N;
    }
}
