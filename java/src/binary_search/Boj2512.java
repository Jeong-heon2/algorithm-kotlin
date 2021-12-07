package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2512 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] requestedBudgets = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++) {
            requestedBudgets[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(requestedBudgets);
        int M = Integer.parseInt(br.readLine());
        System.out.println(binarySearch(requestedBudgets, M));
    }

    private static int binarySearch(int[] requestedBudgets, int total) {
        int left = 0;
        int right = requestedBudgets[requestedBudgets.length - 1];
        int answer = 0;
        while (left <= right) {
            int mid = (right + left) / 2 ;
            int calculatedTotalBudget = calculateBudget(requestedBudgets, mid);
            if (calculatedTotalBudget > total) {
                right = mid - 1;
            } else {
                answer = mid;
                left = mid + 1;
            }
        }
        return answer;
    }

    private static int calculateBudget(int[] requestedBudgets, int limit) {
        int sum = 0;
        for (int requestedBudget : requestedBudgets) {
            sum += Math.min(limit, requestedBudget);
        }
        return sum;
    }
}
