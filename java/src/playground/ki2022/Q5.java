package playground.ki2022;

public class Q5 {

    public static void main(String... args) {
        Solution s = new Solution();
        s.solution(
                new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}},
                new String[]{"ShiftRow", "Rotate","ShiftRow", "Rotate"}
        );
    }
    static class Solution {
        int top;
        int N;
        int M;
        int[][] rc;
        public int[][] solution(int[][] rc, String[] operations) {
            top = 0;
            N = rc.length;
            M = rc[0].length;
            this.rc = rc;
            for (String op : operations) {
                if (op.equals("ShiftRow")) {
                    shiftRow();
                } else {
                    rotate();
                }
            }
            int[][] res = new int[N][M];
            int cur = top;
            System.out.println(top);
            for (int i = 0 ; i < N ; i++) {
                res[i] = rc[cur].clone();
                if (++cur <= N) cur = 0;
            }
            return res;
        }

        private void shiftRow() {
            if (--top < 0) {
                top = N-1;
            }
        }

        private void rotate() {
            // 맨 윗줄
            int ny = 0;
            int y = getY(ny);
            int x = 1;
            int pre = rc[y][0];
            for (; x < M ; x++) {
                int tmp = rc[y][x];
                rc[y][x] = pre;
                pre = tmp;
            }
            // 맨 오른쪽
            x = M - 1;
            ny = 1;
            for (y = getY(ny); ny < N ; y = getY(++ny)) {
                int tmp = rc[y][x];
                rc[y][x] = pre;
                pre = tmp;
            }
            // 맨 아래
            x = M - 2;
            y = getY(N-1);
            for (; x >= 0 ; x--) {
                int tmp = rc[y][x];
                rc[y][x] = pre;
                pre = tmp;
            }
            // 맨 왼쪽
            ny = N - 2;
            x = 0;
            for (y = getY(ny), x = 0; ny >= 0; y = getY(--ny)) {
                int tmp = rc[y][x];
                rc[y][x] = pre;
                pre = tmp;
            }
        }

        private int getY(int ny) {
            return (ny + top) % N;
        }
    }
}
