package impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1051 {
    static char[][] arr;
    static int N;
    static int M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);

        arr = new char[N][M];

        for (int i = 0 ; i < N ; i++) {
            String line = br.readLine();
            for (int j = 0 ; j < M ; j++) {
                arr[i][j] = line.charAt(j);
            }
        }

        int ans = 0;
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < M ; j++) {
                ans = Math.max(ans,check(i, j));
            }
        }
        System.out.println(ans);
    }

    private static int check(int y, int x) {
        int res = 1;
        for (int i = 0; y + i < N && x + i < M; i++) {
            if (arr[y][x] == arr[y][x+i] &&
                    arr[y][x] == arr[y+i][x] &&
                        arr[y][x] == arr[y+i][x+i]) {
                res = i + 1;
            }
        }
        return res*res;
    }
}
