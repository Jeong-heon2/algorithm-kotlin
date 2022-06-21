package impl;

import java.util.ArrayList;
import java.util.List;

public class LEET_N_Queens {
    class Solution {
        List<List<String>> ans;
        boolean[] column;
        boolean[] rd; // 오른쪽 사선
        boolean[] ld; // 왼쪽 사선
        public List<List<String>> solveNQueens(int n) {
            ans = new ArrayList<>();
            column = new boolean[n];
            rd = new boolean[2*n - 1];
            ld = new boolean[2*n - 1];

            char[][] arr = new char[n][n];
            for (int j = 0 ; j < n; j++) {
                for (int t = 0 ; t < n ; t++) {
                    arr[j][t] = '.';
                }
            }

            dfs(arr, 0, n);
            return ans;
        }

        private void dfs(char[][] arr, int r, int n) {
            if (r == n) {
                List<String> l = new ArrayList<>();
                for (int i = 0 ; i < n ; i++) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0 ; j < n ; j++) {
                        sb.append(arr[i][j]);
                    }
                    l.add(sb.toString());
                }
                ans.add(l);
                return;
            }
            for (int c = 0; c < n ; c++) {
                if (check(arr, r, c)) {
                    arr[r][c] = 'Q';
                    column[c] = true;
                    rd[r + c] = true;
                    ld[r - c + n - 1] = true;

                    dfs(arr, r + 1, n);

                    column[c] = false;
                    rd[r + c] = false;
                    ld[r - c + n - 1] = false;
                    arr[r][c] = '.';
                }
            }
        }

        private boolean check(char[][] arr, int r, int c) {
            return !column[c] && !rd[r + c] && !ld[r - c + arr.length - 1];
        }
    }
}
