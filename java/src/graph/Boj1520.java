package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//시간 초과
public class Boj1520 {
    static int N;
    static int M;
    static int[][] graph;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int cnt = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        cnt = 0;
        graph = new int[M][N];
        for (int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean[][] visited = new boolean[M][N];
        visited[0][0] = true;
        dfsRecursion(0, 0, visited);
        System.out.println(cnt);
    }

    private static void dfsRecursion(int curY, int curX, boolean[][] visited) {
        if (curX == N-1 && curY == M-1) {
            cnt++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nY = curY + dy[i];
            int nX = curX + dx[i];
            if (isRanged(nY, nX) && !visited[nY][nX] && graph[nY][nX] < graph[curY][curX]) {
                visited[nY][nX] = true;
                dfsRecursion(nY, nX, visited);
                visited[nY][nX] = false;
            }
        }
    }

    private static boolean isRanged(int y, int x) {
        return y >= 0 && y < M && x >= 0 && x < N;
    }
}