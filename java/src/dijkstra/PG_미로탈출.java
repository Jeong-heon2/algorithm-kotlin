package dijkstra;
import java.util.*;

public class PG_미로탈출 {

    class Solution {
        private int[][] matrix;
        public int solution(int n, int start, int end, int[][] roads, int[] traps) {
            matrix = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n ; j++) {
                    if (i == j) matrix[i][j] = 0;
                    else matrix[i][j] = Integer.MAX_VALUE;
                }
            }
            for (int i = 0 ; i < roads.length; i++) {
                matrix[roads[i][0]][roads[i][1]] = Math.min(matrix[roads[i][0]][roads[i][1]], roads[i][2]);
            }
            return dijkstra(start, end, n, traps);
        }

        private int dijkstra(int start, int end, int n, int[] traps) {
            PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
            boolean[][] visited = new boolean[n + 1][1 << 10];
            pq.offer(new int[]{0, start, 0});

            while(!pq.isEmpty()) {
                int[] curr = pq.poll();
                int w = curr[0], u = curr[1], state = curr[2];

                if (u == end) return w;

                if (visited[u][state]) continue;
                visited[u][state] = true;

                boolean currTrapped = false;
                // 함정이 발동된 노드
                HashMap<Integer, Boolean> trapped = new HashMap<>();
                for (int i = 0 ; i < traps.length ; i++) {
                    int bit = 1 << i;
                    // traps[i] 함정이 켜져 있음
                    if((state & bit) != 0) {
                        // 근데 그게 지금 도착한 노드라면
                        if (u == traps[i]) {
                            state &= ~bit;
                        } else {
                            trapped.put(traps[i], true);
                        }
                    } else {
                        if (u == traps[i]) {
                            state |= bit;
                            trapped.put(traps[i], true);
                            currTrapped = true;
                        }
                    }
                }

                for (int i = 1 ; i <= n ; i++) {
                    if (i == u) continue;
                    boolean nextTrapped = trapped.containsKey(i);
                    // 현재 노드 도 함정이 켜져있고 다음 노드도 함정이 켜져있으면
                    // 함정이 안 발생한 거나 마찬가지
                    if (currTrapped == nextTrapped) {
                        if (matrix[u][i] != Integer.MAX_VALUE) {
                            pq.offer(new int[]{w + matrix[u][i], i, state});
                        }
                    } else {
                        if (matrix[i][u] != Integer.MAX_VALUE) {
                            pq.offer(new int[]{w + matrix[i][u], i, state});
                        }
                    }
                }
            }
            return Integer.MAX_VALUE;
        }
    }
}
