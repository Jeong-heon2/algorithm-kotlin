package string;

public class LEET_String_To_Integer {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.myAtoi("2147483648"));
    }

    // log(n)
    static class Solution {
        public int myAtoi(String s) {
            s = s.trim();
            int len = s.length();
            int signMultiplier = len > 0 && s.charAt(0) == '-' ? -1 : 1;
            int i = len > 0 && (s.charAt(0) == '-' || s.charAt(0) == '+') ? 1 : 0;
            int res = 0;
            for ( ; i < len ; i++) {
                char ch = s.charAt(i);
                if (ch < '0' || ch > '9') break;
                int value = ch - '0';
                if (res * signMultiplier > Integer.MAX_VALUE / 10 ) {
                    return Integer.MAX_VALUE;
                } else if (res * signMultiplier < Integer.MIN_VALUE / 10) {
                    return Integer.MIN_VALUE;
                } else if (res * signMultiplier == Integer.MAX_VALUE / 10 && value > 7) {
                    return Integer.MAX_VALUE;
                } else if (res * signMultiplier == Integer.MIN_VALUE / 10 && value > 7) {
                    return Integer.MIN_VALUE;
                }
                res = res * 10 + value;
            }
            return signMultiplier * res;
        }
    }
}
