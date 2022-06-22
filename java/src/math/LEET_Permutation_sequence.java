package math;

public class LEET_Permutation_sequence {

    public static void main(String... args) {
        Solution s= new Solution();
        s.getPermutation(4, 7);
    }
    static class Solution {

        public String getPermutation(int n, int k) {
            int[] arr = new int[n];
            int fact = 1;
            for (int i = 0 ; i < n ; i++) {
                arr[i] = i+1;
                fact *= i+1;
            }
            boolean[] visited = new boolean[n];
            StringBuilder sb = new StringBuilder();
            while(sb.length() < arr.length) {
                fact = fact / n--;
                int target = (k-1) / fact ;
                k = k - (fact * target);
                int cnt = -1;
                for (int i = 0; i < arr.length; i++) {
                    if (!visited[i]) {
                        cnt++;
                        if (target == cnt) {
                            visited[i] = true;
                            sb.append(arr[i]);
                            break;
                        }
                    }
                }
            }
            return sb.toString();
        }
    }
}
