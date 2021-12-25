package impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj2638 {
    static int[][] arr;
    static int N;
    static int M;
    static int[] dx = {1, -1 ,0 ,0};
    static int[] dy = {0, 0, 1, -1};
    static int cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cnt = 0;
        arr = new int[N + 1][M + 1];

        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1 ; j <= M ; j++ ){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) cnt++;
            }
        }

        int time = 0;
        while (cnt != 0) {
            //녹아야할 치즈 좌표 찾기
            List<int[]> meltedCheeseList = findMeltedCheese();
            // 치즈 녹이기
            cnt -= meltedCheeseList.size();
            for (int[] point : meltedCheeseList) {
                arr[point[0]][point[1]] = 0;
            }
            time++;
        }
        System.out.println(time);
    }

    private static List<int[]> findMeltedCheese() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N + 1][M + 1];
        int[][] cheeseVisited = new int[N + 1][M + 1];
        q.offer(new int[]{0, 0});
        visited[0][0] = true;
        ArrayList<int[]> meltedCheeseList = new ArrayList<>();
        while (q.size() != 0) {
            int[] cur = q.poll();
            for (int i = 0 ; i < 4 ; i++) {
                int nx = cur[1] + dx[i];
                int ny = cur[0] + dy[i];
                if (nx < 0 || nx > M || ny < 0 || ny > N) continue;
                if (arr[ny][nx] == 1) {
                    cheeseVisited[ny][nx]++;
                    if (cheeseVisited[ny][nx] == 2) meltedCheeseList.add(new int[]{ny, nx});
                } else if (!visited[ny][nx] && arr[ny][nx] == 0) {
                    visited[ny][nx] = true;
                    q.offer(new int[]{ny, nx});
                }
            }
        }
        return meltedCheeseList;
    }
}
