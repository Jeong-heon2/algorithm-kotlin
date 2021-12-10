package impl;

public class PG행렬_테두리_회전하기 {
    public static void main(String[] args) {
        int[][] queries = {{1,1,2,2},{1,2,2,3},{2,1,3,2},{2,2,3,3}};
        int[] a = solution(3,3,queries);
        for (int i : a) {
            System.out.println(i);
        }
    }

    public static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        //행렬 만들기
        int[][] matrix = new int[rows + 1][columns + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                matrix[i][j] = (i-1) * columns + j;
            }
        }
        //쿼리를 하나씩 실행
        for (int i = 0; i < queries.length; i++) {
            answer[i] = rotate(matrix, queries[i]);
        }
        return answer;
    }

    // 시계방향으로 회전하고 테두리 최소 값 리턴
    private static int rotate(int[][] matrix, int[] query) {
        int x1 = query[1];
        int y1 = query[0];
        int x2 = query[3];
        int y2 = query[2];
        int min = 10000;
        int tmp = matrix[y1][x1];
        for (int i = y1 + 1; i <= y2; i++) {
            min = Math.min(min, matrix[i][x1]);
            matrix[i-1][x1] = matrix[i][x1];
        }
        for (int i = x1 + 1; i <= x2; i++) {
            min = Math.min(min, matrix[y2][i]);
            matrix[y2][i-1] = matrix[y2][i];
        }
        for (int i = y2 - 1; i >= y1; i--) {
            min = Math.min(min, matrix[i][x2]);
            matrix[i+1][x2] = matrix[i][x2];
        }
        for (int i = x2 - 1; i >= x1 + 1; i--) {
            min = Math.min(min, matrix[y1][i]);
            matrix[y1][i+1] = matrix[y1][i];
        }
        min = Math.min(min, tmp);
        matrix[y1][x1+1] = tmp;
        return min;
    }
}
