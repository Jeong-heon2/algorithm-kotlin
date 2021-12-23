package playground;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class KEQ3 {
    static long[] memo;
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        memo = new long[n + 1];
        System.out.println(find(0) % 1000000007);
    }
    static long find(int sum) {
        if (sum > n) return 0;
        if (sum == n) return 1;
        if (memo[sum] != 0L) return memo[sum];
        else return memo[sum] = (find(sum + 1) + find(sum + 2) + find(sum + 4) + find(sum + 6)) % 1000000007;
    }
}
