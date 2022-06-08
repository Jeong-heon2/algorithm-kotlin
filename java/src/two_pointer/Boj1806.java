package two_pointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1806 {

    public static void main(String... args) throws Exception {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] tokens = br.readLine().split(" ");
            int[] arr = new int[Integer.parseInt(tokens[0])];
            int s = Integer.parseInt(tokens[1]);
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0 ; i < arr.length ; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            System.out.println(solve(arr, s));
        }
    }

    private static int solve(int[] arr, int s) {
        if (arr.length == 0) return 0;
        if (arr[0] >= s) return 1;

        int i = 0;
        int j = 1;
        final int len = arr.length;
        int sum = arr[i];
        int ans = Integer.MAX_VALUE;
        while (i < len) {
            while (j < len && sum < s) {
                sum += arr[j++];
            }
            if (sum >= s) {
                ans = Math.min(ans, j - i);
            }

            sum -= arr[i++];
        }
        if (ans == Integer.MAX_VALUE) return 0;
        return ans;
    }
}
