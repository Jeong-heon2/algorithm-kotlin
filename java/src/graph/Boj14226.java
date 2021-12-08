package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Boj14226 {
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[2000][2000];
        q.offer(new int[]{1, 0});
        visited[1][0] = true;
        int cnt = 0;
        while (!q.isEmpty()) {
            int qSize = q.size();
            while (qSize-- > 0) {
                int[] cur = q.poll();
                int windowEmojis = cur[0];
                int clipBoardEmojis = cur[1];
                if (windowEmojis == n) return cnt;
                /*
                1. 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
                2. 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
                3. 화면에 있는 이모티콘 중 하나를 삭제한다.
                 */
                if (isRanged(windowEmojis) && !visited[windowEmojis][windowEmojis]) {
                    q.offer(new int[]{windowEmojis, windowEmojis});
                    visited[windowEmojis][windowEmojis] = true;
                }

                int sumWindowEmojis = windowEmojis + clipBoardEmojis;
                if (isRanged(sumWindowEmojis) && isRanged(clipBoardEmojis) && !visited[sumWindowEmojis][clipBoardEmojis]) {
                    q.offer(new int[]{sumWindowEmojis, clipBoardEmojis});
                    visited[sumWindowEmojis][clipBoardEmojis] = true;
                }

                int deletedWindowEmojis = windowEmojis - 1;
                if (isRanged(deletedWindowEmojis) && isRanged(clipBoardEmojis) && !visited[deletedWindowEmojis][clipBoardEmojis]) {
                    q.offer(new int[]{deletedWindowEmojis, clipBoardEmojis});
                    visited[deletedWindowEmojis][clipBoardEmojis] = true;
                }
            }
            cnt++;
        }
        return cnt;
    }

    private static boolean isRanged(int emojis) {
        return emojis >= 0 && emojis < 2000;
    }
}
