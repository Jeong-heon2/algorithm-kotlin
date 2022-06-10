package graph;

// brute force, bitmask
public class LEET_Valid_Sudoku {

    public static void main(String... args) {
        Solution s = new Solution();
        if (s.isValidSudoku(new String[][]
                {
                        {".", ".", ".", ".", "7", ".", ".", ".", "."},
                        {".", ".", ".", ".", ".", ".", ".", ".", "."},
                        {".", ".", ".", ".", ".", ".", ".", "6", "."},
                        {".", ".", ".", ".", ".", ".", ".", ".", "."},
                        {"4", ".", ".", ".", ".", ".", ".", ".", "."},
                        {".", ".", ".", ".", ".", ".", ".", ".", "."},
                        {".", "6", ".", ".", ".", ".", ".", ".", "."},
                        {".", ".", ".", ".", ".", ".", ".", ".", "."},
                        {".", ".", ".", ".", ".", ".", ".", ".", "."}
                }
        )) {
            System.out.println("true");
        }

    }
    static class Solution {

        int[][] map = new int[3][9];
        int cnt = 0;
        int sum = 0;
        boolean[][] visited = new boolean[9][9];
        public boolean isValidSudoku(String[][] tmp) {
            char[][] board = new char[9][9];
            for (int i = 0 ; i < 9 ; i++) {
                for (int j = 0 ; j < 9; j++) {
                    board[i][j] = tmp[i][j].charAt(0);
                }
            }
            for (int i = 0 ; i < board.length; i++) {
                for (int j = 0 ; j < 9 ; j++) {
                    char ch = board[i][j];
                    if (ch != '.') {
                        int mask = 1 << (ch - '0');
                        int n = ((i/3)*3) + (j/3);
                        if ((map[0][i] & mask) != 0) return false;
                        map[0][i] |= mask;
                        if ((map[1][j] & mask) != 0) return false;
                        map[1][j] |= mask;
                        if ((map[2][n] & mask) != 0) return false;
                        map[2][n] |= mask;
                        visited[i][j] = true;
                    } else {
                        sum++;
                    }
                }
            }

            for (int i = 0 ; i < 9 ; i++) {
                for (int j = 0 ; j < 9; j++) {
                    if (board[i][j] == '.') {
                        return bruteForce(board, i, j);
                    }
                }
            }
            return true;
        }

        private boolean bruteForce(char[][] board, int y, int x) {

            int n = ((y/3)*3) + (x/3);
            visited[y][x] = true;
            for (int i = 1; i <= 9; i++) {
                if ((map[0][y] & (1 << i)) == 0 && (map[1][x] & (1 << i)) == 0 && (map[2][n] & (1 << i)) == 0) {
                    board[y][x] = (char) (i + '0');
                    int mask = 1 << i;
                    map[0][y] |= mask;
                    map[1][x] |= mask;
                    map[2][n] |= mask;
                    cnt++;

                    if (cnt == sum) {
                        return true;
                    }


                    int[] next = findNextPos(board);
                    if (next[0] == -1) return false;
                    boolean res = bruteForce(board, next[0], next[1]);
                    if (res) return true;

                    board[y][x] = '.';
                    map[0][y] &= ~mask;
                    map[1][x] &= ~mask;
                    map[2][n] &= ~mask;
                    cnt--;
                }
            }
            visited[y][x] = false;
            return false;
        }

        private int[] findNextPos(char[][] board) {
            for (int i = 0 ; i < 9 ; i++) {
                for (int j = 0 ; j < 9; j++) {
                    if (board[i][j] == '.' && !visited[i][j]) {
                        return new int[]{i,j};
                    }
                }
            }
            return new int[]{-1};
        }
    }
}
