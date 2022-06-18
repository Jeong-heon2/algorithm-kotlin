package impl;

public class LEET_DIAGONAL_TRAVERSAL {

    // time : O(n*m)
    // space : O(1)  ( final result 를 위한 배열 제외 )
    class Solution {
        int n = 0;
        int m = 0;
        public int[] findDiagonalOrder(int[][] mat) {
            int d = 1;
            n = mat.length;
            m = mat[0].length;
            int r = 0;
            int c = 0;
            int[] ans = new int[n * m];
            int i = 0;
            while (r != n -1 || c != m - 1) {
                while (isRanged(r, c)) {
                    ans[i++] = mat[r][c];
                    r -= d;
                    c += d;
                }
                if (d == 1) {
                    if (isRanged(r + d, c)) {
                        r += d;
                    } else {
                        r += 2*d;
                        c -= d;
                    }
                    d = -1;
                } else {
                    if (isRanged(r, c - d)) {
                        c -= d;
                    } else {
                        r += d;
                        c -= 2*d;
                    }
                    d = 1;
                }
            }
            ans[n*m - 1] = mat[n-1][m-1];
            return ans;
        }

        private boolean isRanged(int r, int c) {
            if (r >= 0 && r < n && c >= 0 && c < m) {
                return true;
            }
            return false;
        }
    }
}
