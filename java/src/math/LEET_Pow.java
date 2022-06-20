package math;

public class LEET_Pow {
    class Solution {
        public double myPow(double x, int n) {
            // 10 =? 1010
            // 2*2*2...*2
            boolean sign = true;
            long nn = n;
            if (n < 0) {
                sign = false;
                nn *= -1;
            }
            double ans = 1;
            while (nn > 0) {
                if ((nn & 1) == 1) {
                    ans *= x;
                }
                x *= x;

                nn = nn >> 1;
            }
            return sign ? ans : 1/ans;
        }
    }
}
