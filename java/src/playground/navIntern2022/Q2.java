package playground.navIntern2022;

public class Q2 {
    public static void main(String... args) {
        Solution s= new Solution();
        int a = s.solution(new String[] {"...X.", ".X..X", "X...X", "..X.."});
        System.out.println(a);
    }

    static class Solution {
        int N;
        int M;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int ans = 0;
        public int solution(String[] R) {
            N = R.length;
            M = R[0].length();
            char[][] board = new char[N][M];
            for (int i = 0 ; i < N ; i++) {
                for (int j = 0 ; j < M ; j++) {
                    board[i][j] = R[i].charAt(j);
                }
            }
            dfs(board, 0,0,0);
            return ans;
        }

        private void dfs(char[][] board, int i, int j, int d) {
            if (board[i][j] == 'o') {
                // 다음 위치를 구하고 방문 하지 않은 곳이라면 진행. 아니면 리턴
                for (int t = 0 ; t < 4; t++) {
                    int nx = j + dx[d];
                    int ny = i + dy[d];
                    if (!isRanged(ny, nx) || board[ny][nx] == 'X') {
                        d = nextDirection(d);
                        continue;
                    }
                    if (isRanged(ny, nx) && board[ny][nx] == '.') {
                        dfs(board, ny, nx, d);
                    }
                }

            } else {
                board[i][j] = 'o';
                ans++;
                // 다음 위치로 . 벽이 아니어야 함. 방문 여부는 상관 없음
                for (int t = 0 ; t < 4; t++) {
                    int nx = j + dx[d];
                    int ny = i + dy[d];
                    if (!isRanged(ny, nx) || board[ny][nx] == 'X') {
                        d = nextDirection(d);
                        continue;
                    }
                    if (isRanged(ny, nx) && board[ny][nx] != 'X') {
                        dfs(board, ny, nx, d);
                    }
                }
            }
        }

        private boolean isRanged(int i, int j) {
            if (i >= 0 && i < N && j >= 0 && j < M) return true;
            return false;
        }


        private int nextDirection(int d) {
            if (d == 3) return 0;
            else return d+1;
        }
    }
}
