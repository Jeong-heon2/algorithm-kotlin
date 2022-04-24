package string;

public class LEET_REVERSE_INTEGER {
    public static void main(String[] args) {
        Solution2 s = new Solution2();
        s.reverse(1534236469);
    }
    /*
    형변환 -> 시간이 많이 걸림
     */
    static class Solution {
        public int reverse(int x) {
            boolean isPlus = x >= 0;
            String s = String.valueOf(x);
            StringBuilder sb = new StringBuilder();
            for (int i = s.length() - 1; i >= 0 ; i--) {
                if (sb.length() == 0 && s.charAt(i) == '0') continue;
                if (s.charAt(i) != '-') sb.append(s.charAt(i));
            }
            if (sb.length() == 0) sb.append('0');
            try {
                if (isPlus) return Integer.parseInt(sb.toString());
                else return Integer.parseInt("-" + sb.toString());
            } catch (Exception e) {
                return 0;
            }
        }
    }

    // log(n)
    static class Solution2 {
        public int reverse(int x) {
            // signMultiplier = -1 for negative numbers
            // signMultiplier = 1 for positive numbers
            int signMultiplier = 1;
            if (x < 0) {
                signMultiplier = -1;
                x = signMultiplier * x;
            }

            int res = 0;
            while (x > 0) {

                // Check if the result is within MaxInt32 and MinInt32 bounds
                if (res * signMultiplier > Integer.MAX_VALUE / 10 || res * signMultiplier < Integer.MIN_VALUE / 10) {
                    return 0;
                }
                // Add the current digit into result
                res = res * 10 + x % 10;

                x = x / 10;
            }
            // Restore to original sign of number (+ or -)
            return (int)(signMultiplier * res);
        }
    }
}
