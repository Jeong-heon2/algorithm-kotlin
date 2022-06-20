package playground.navIntern2022;

public class Q3 {
    public static void main(String... args) {
        Solution s = new Solution();
        int a = s.solution(10, 10);
        System.out.println(a);
    }

    static class Solution {
        public int solution(int M, int N) {
            return (int)Math.sqrt((double)4*N + M);
        }
    }
}
