package divide_conqure;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1780 {
    static int[][] arr;
    static int[] counts;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        counts = new int[3];
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(0, 0, N);
        for (int i = 0 ; i < 3 ; i++) {
            System.out.println(counts[i]);
        }
    }
    private static void recur(int y, int x, int size) {
        if (size == 1) countUp(arr[y][x]);
        else {
            // 하나의 수로 이루어져있는지 체크
            int target = arr[y][x];
            boolean flag = true;
            for (int i = y ; i < y+size ; i++) {
                for (int j = x ; j < x+size; j++) {
                    if (arr[i][j] != target) {
                        flag = false;
                        break;
                    }
                }
                if (!flag) break;
            }
            // 이루어져있으면 카운트 증가 후 리턴
            if (flag) {
                countUp(target);
            } else {
                // 않으면 쪼개기
                for (int i = y; i < y + size ; i += size/3) {
                    for (int j = x; j < x + size ; j += size/3) {
                        recur(i, j, size/3);
                    }
                }
            }
        }
    }
    private static void countUp(int ch) {
        if (ch == -1) counts[0]++;
        else if (ch == 0) counts[1]++;
        else counts[2]++;
    }
}
