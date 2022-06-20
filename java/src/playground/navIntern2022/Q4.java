package playground.navIntern2022;

import java.util.LinkedList;
import java.util.Queue;

public class Q4 {
    public static void main(String... args) {

    }

    int N;
    int M;
    char[][] board;
    int[] dx = {1,-1,0,0};
    int[] dy = {0,0,1,-1};
    public int[] solution(String[] B) {
        N = B.length;
        M = B[0].length();
        board = new char[N][M];
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                board[i][j] = B[i].charAt(j);
            }
        }

        int[] ans = new int[3];
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                if (board[i][j] == '#') {
                    int res = bfs(new int[] {i, j});
                    if (res == 3) ans[2]++;
                    else if (res == 2) ans[1]++;
                    else ans[0]++;
                }
            }
        }
        return ans;
    }

    private int bfs(int[] start) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        int cnt = 0;
        while (q.size() > 0) {
            int[] cur = q.poll();
            for (int i = 0 ; i < 4; i++) {
                int nx = cur[1] + dx[i];
                int ny = cur[0] + dy[i];
                if (nx >= 0 && nx < M && ny >= 0 && ny < N && board[ny][nx] == '#') {
                    board[ny][nx] = 'o';
                    cnt++;
                    q.offer(new int[]{ny, nx});
                }
            }
        }
        return cnt;
    }
}
