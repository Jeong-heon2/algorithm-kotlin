package brute_force;

public class PG_사라지는_발판 {

    public static void main(String... args) {
        int[][] board = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int[] aloc = {1, 0};
        int[] bloc = {1, 2};
        Solution s = new Solution();
        s.solution(board, aloc, bloc);
    }
    static class Solution {

        int N,M;
        int dy[] = {0,0,1,-1};
        int dx[] = {1,-1,0,0};

        public int solution(int[][] board, int[] aloc, int[] bloc) {
            N = board.length;
            M = board[0].length;

            return solve(board, aloc[0], aloc[1], bloc[0], bloc[1])[1];
        }


        boolean inRange(int y, int x) {
            return 0<= y && y < N && 0 <= x && x < M;
        }

        // can't move
        boolean isFinished(int[][] board, int y, int x) {
            for(int i=0; i<4; i++) {
                int ny = y+dy[i];
                int nx = x+dx[i];
                if(inRange(ny,nx) && board[ny][nx] != 0)
                    return false;
            }
            return true;
        }

        // {canWin, turn}
        int[] solve(int[][] board, int y1, int x1, int y2, int x2) {
            // base case
            if(isFinished(board, y1, x1)) return new int[]{0, 0};
            if(y1==y2 && x1==x2) return new int[]{1, 1};

            boolean canWin = false;
            int minTurn = Integer.MAX_VALUE, maxTurn = 0;

            for(int i=0; i<4; i++) {
                int ny = y1+dy[i];
                int nx = x1+dx[i];
                if(!inRange(ny,nx) || board[ny][nx] == 0) continue;

                // dfs
                board[y1][x1] = 0;
                int[] res = solve(board, y2, x2, ny, nx);
                board[y1][x1] = 1;

                if(res[0] == 0) {
                    canWin = true;
                    minTurn = Math.min(minTurn, res[1]);
                }
                else if(!canWin) {
                    maxTurn = Math.max(maxTurn, res[1]);
                }
            }

            int turn = canWin ? minTurn : maxTurn;
            return new int[]{canWin ? 1 : 0, 1 + turn};
        }
    }
}

