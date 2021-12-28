package graph;
import java.util.*;

public class PG_빛의경로사이클 {

    class Solution {
        //동남서북 (방향)
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        // visited[y][x][d] = (y,x) 에 d 방향으로 진입
        boolean[][][] visited;

        public int[] solution(String[] grid) {
            visited = new boolean[grid.length][grid[0].length()][4];
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0 ; i < grid.length; i++) {
                int size = grid[i].length();
                for (int j = 0 ; j < size; j++) {
                    for (int k = 0 ; k < 4; k++) {
                        if(!visited[i][j][k]) {
                            list.add(cycle(i, j, k, grid, size));
                        }
                    }
                }
            }
            Collections.sort(list);
            int[] answer = new int[list.size()];
            for (int i = 0; i < answer.length; i++) {
                answer[i] = list.get(i);
            }
            return answer;
        }

        private int cycle(int y, int x, int d, String[] grid, int size) {
            int cnt = 1;
            do {
                visited[y][x][d] = true;
                if (grid[y].charAt(x) == 'S') {
                    x = x + dx[(d + 2) % 4];
                    y = y + dy[(d + 2) % 4];
                } else if (grid[y].charAt(x) == 'L') {
                    x = x + dx[(d + 1) % 4];
                    y = y + dy[(d + 1) % 4];
                    d = (d + 3) % 4;
                } else {
                    x = x + dx[(d + 3) % 4];
                    y = y + dy[(d + 3) % 4];
                    d = (d + 1) % 4;
                }
                if (y == -1) y = grid.length -1;
                else if(y == grid.length) y = 0;
                if (x == -1) x = size - 1;
                else if(x == size) x = 0;
                if (visited[y][x][d]) break;
                cnt++;
            } while(true);
            return cnt;
        }
    }
}
