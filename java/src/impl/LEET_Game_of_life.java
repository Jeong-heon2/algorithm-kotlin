package impl;

public class LEET_Game_of_life {
    class Solution {
        // extra space 를 절약하기 위해 상태를 추가한다
        // 2 : 원래 0 인데, 1 로 업데이트 하겠다
        // 3 : 원래 1 인데, 0 으로
        // 0 : 원래 0, 0
        // 1 : 원래 1, 1
        // O(m*n),  space : O(1)
        public void gameOfLife(int[][] board) {
            for (int r = 0; r < board.length; r++) {
                for (int c = 0; c < board[0].length; c++) {
                    // 이웃 개수
                    int cnt = getNeighborCnt(board, r + 1, c) + getNeighborCnt(board, r + 1, c + 1)
                            + getNeighborCnt(board, r, c + 1) + getNeighborCnt(board, r - 1, c)
                            + getNeighborCnt(board, r - 1, c - 1) + getNeighborCnt(board, r, c - 1)
                            + getNeighborCnt(board, r + 1, c - 1) + getNeighborCnt(board, r - 1, c + 1);

                    if (board[r][c] == 0) {
                        if (cnt == 3) {
                            board[r][c] = 2;
                        } else {
                            board[r][c] = 0;
                        }
                    } else {
                        if (cnt == 2 || cnt == 3) {
                            board[r][c] = 1;
                        } else {
                            board[r][c] = 3;
                        }
                    }
                }
            }
            for (int r = 0; r < board.length; r++) {
                for (int c = 0; c < board[0].length; c++) {
                    if (board[r][c] == 3) {
                        board[r][c] = 0;
                    } else if (board[r][c] == 2) {
                        board[r][c] = 1;
                    }
                }
            }
        }

        private int getNeighborCnt(int[][] board, int r, int c) {
            if (r < board.length && r >= 0 && c < board[0].length && c >= 0 &&
                    (board[r][c] == 3 || board[r][c] == 1)) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
