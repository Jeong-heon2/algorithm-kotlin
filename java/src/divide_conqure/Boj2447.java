package divide_conqure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj2447 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        String[][] ans = recursion(N);
        for (int i = 0; i < N; i++) {
            for (int j = 0 ; j < N; j++) {
                if (ans[i][j] == null) bw.write(" ");
                else bw.write(ans[i][j]);
            }
            bw.write("\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    private static String[][] recursion(int n) {
        if (n == 3) {
            return new String[][]{
                    new String[]{"*", "*", "*"},
                    new String[]{"*", " ", "*"},
                    new String[]{"*", "*", "*"}
            };
        }
        String[][] res = recursion(n / 3);
        String[][] arr = new String[n][n];
        for (int i = 0; i < n; i += n / 3) {
            for (int j = 0; j < n; j += n / 3) {
                if (i != n / 3 || j != n / 3) {
                    for (int y = 0; y < res.length; y++) {
                        for (int x = 0; x < res[0].length; x++) {
                            arr[i + y][j + x] = res[y][x];
                        }
                    }
                }
            }
        }
        return arr;
    }
}
