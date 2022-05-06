package prefix_sum;

public class PG_파괴되지_않은_건물 {
    class Solution {
        public int solution(int[][] board, int[][] skill) {
            int[][] arr = new int[board.length + 1][board[0].length + 1];
            for (int[] q : skill) {
                int n = q[0] == 1 ? -q[5] : q[5];
                arr[q[1]][q[2]] += n;
                arr[q[1]][q[4] + 1] -= n;
                arr[q[3] + 1][q[2]] -= n;
                arr[q[3] + 1][q[4] + 1] += n;
            }
            for (int i = 0 ; i < arr[0].length; i++) {
                for (int j = 0 ; j < arr.length - 1; j++) {
                    arr[j+1][i] += arr[j][i];
                }
            }
            for (int i = 0 ; i < arr.length ; i++) {
                for (int j = 0 ; j < arr[0].length - 1; j++) {
                    arr[i][j+1] += arr[i][j];
                }
            }
            for (int i = 0 ; i < arr.length - 1; i++) {
                for (int j = 0 ; j < arr[0].length - 1; j++) {
                    board[i][j] += arr[i][j];
                }
            }
            return countDurability(board);
        }

        //내구도 체크
        private int countDurability(int[][] board) {
            int cnt = 0;
            for (int i = 0 ; i < board.length; i++) {
                for (int j = 0 ; j < board[0].length; j++) {
                    if(board[i][j] > 0) cnt++;
                }
            }
            return cnt;
        }
    }
}
