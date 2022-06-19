package impl;

public class LEET_ROTATE_IMAGE {
    class Solution {
        public void rotate(int[][] matrix) {
            for (int n = matrix.length, depth = 0; n > 1; n -= 2, depth++) {
                for (int i = 0 ; i < n -1; i++) {
                    int tmp = matrix[depth + i][depth + n-1];
                    matrix[depth + i][depth + n-1] = matrix[depth][depth + i];
                    matrix[depth][depth + i] = matrix[depth + n - 1 - i][depth];
                    matrix[depth + n - 1 - i][depth] = matrix[depth + n - 1][depth + n - 1 - i];
                    matrix[depth + n - 1][depth + n - 1 - i] = tmp;
                }
            }
        }
    }
}
